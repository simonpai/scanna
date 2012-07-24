/*
 * 
 */
package org.scanna.segment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.scanna.model.Document;
import static org.scanna.segment.SegmentPattern.NOT_FOUND;
import static org.scanna.segment.SegmentPattern.END_OF_LINE;;

/**
 * 
 * @author simonpai
 */
public class SegmentEngine {
	
	protected final Document _document;
	protected final List<SegmentPattern> _ptns = new LinkedList<SegmentPattern>();
	
	protected SegmentPattern _ptn = null;
	
	public SegmentEngine(Document document) {
		_document = document;
	}
	
	// pattern //
	public void add(SegmentPattern pattern) {
		_ptns.add(pattern);
	}
	
	public void remove(SegmentPattern pattern) {
		_ptns.remove(pattern);
	}
	
	public List<Line> run() {
		List<String> rawContent = _document.getContent();
		List<Line> res = new ArrayList<Line>(rawContent.size());
		int row = 1; // 1-based
		for (String str : rawContent) {
			List<Segment> segs = segment(_document, row, str);
			res.add(createLine(_document, row, segs));
			row++;
		}
		return res;
	}
	
	protected List<Segment> segment(Document document, final int row, String str) {
		List<Segment> res = new ArrayList<Segment>();
		SegmentPattern currPtn = _ptn; // continue from previous line
		final int strlen = str.length();
		boolean first = true;
		
		for (int i = 0; i < strlen; ) {
			int j = NOT_FOUND;
			SegmentPattern newPtn = null;
			
			if (currPtn == null) // raw
				for (SegmentPattern ptn : _ptns) {
					int jc = ptn.findOpenIndex(str, i);
					if (jc != NOT_FOUND && (j == NOT_FOUND || jc < j)) {
						j = jc;
						newPtn = ptn;
					}
				}
			else {
				j = currPtn.findCloseIndex(str, i, first);
				if (j >= 0)
					newPtn = null;
			}
			first = false;
			
			// TODO: organize
			if (i != j)
				res.add(createSegment(str, row, i, j, currPtn));
			
			if (j == END_OF_LINE) {
				currPtn = null;
				break;
			} else if (j == NOT_FOUND)
				break;
			
			i = j;
			currPtn = newPtn;
			
			// if a non-RAW blocked closed at line end, we need to insert an empty
			// RAW block to separate the first block of potentially the same type 
			if (j == strlen)
				res.add(createSegment(str, row, j, j, null));
			
		}
		
		_ptn = currPtn;
		return res;
	}
	
	protected Segment createSegment(String str, int row, int start, int end, 
			SegmentPattern ptn) {
		return new Segment(row, start, end < 0 ? 
				str.substring(start) : str.substring(start, end), 
				ptn == null ? Segment.RAW : ptn.getType(str, start, end));
	}
	
	protected Line createLine(Document document, int row, List<Segment> segments) {
		return new Line(document, row, segments);
	}
	
}
