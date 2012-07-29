/*
 * 
 */
package org.scanna.segment;

import java.util.ArrayList;
import java.util.List;

import org.scanna.model.Document;
import org.scanna.segment.impl.CollectivePattern;

import static org.scanna.segment.SegmentPattern.END_OF_LINE;
import static org.scanna.segment.SegmentPattern.NOT_FOUND;

/**
 * 
 * @author simonpai
 */
public class SegmentEngine {
	
	protected final SegmentPattern _pptns;
	
	public SegmentEngine(SegmentPattern ... patterns) {
		_pptns = new CollectivePattern(patterns);
	}
	
	public List<Line> run(Document document) {
		List<String> rawContent = document.getContent();
		List<Line> res = new ArrayList<Line>(rawContent.size());
		SegmentationContext ctx = new SegmentationContext();
		int row = 1; // 1-based
		for (String str : rawContent) {
			List<Segment> segs = segment(document, row, str, ctx);
			res.add(createLine(document, row, segs, ctx));
			row++;
		}
		return res;
	}
	
	protected List<Segment> segment(Document document, final int row, 
			String str, SegmentationContext sctx) {
		List<Segment> res = new ArrayList<Segment>();
		
		// continue from previous line
		int currType = sctx.getType();
		SegmentPatternContext currCtx = sctx.getPattern();
		
		final int strlen = str.length();
		boolean first = true;
		
		for (int i = 0; i < strlen; ) {
			int j = NOT_FOUND;
			SegmentPatternContext newCtx = null;
			int newType = Segment.RAW;
			
			if (currCtx == null) { // raw
				newCtx = _pptns.match(str, i);
				j = newCtx == null ? NOT_FOUND : newCtx.start();
			} else {
				int[] ends = currCtx.end(str, first ? -1 : i);
				j = ends[0];
				newType = ends[1];
				if (j >= 0)
					newCtx = null;
			}
			first = false;
			
			// TODO: organize
			if (i != j)
				res.add(createSegment(str, row, i, j, newType));
			
			if (j == END_OF_LINE) {
				currCtx = null;
				currType = Segment.RAW;
				break;
			} else if (j == NOT_FOUND)
				break;
			
			i = j;
			currCtx = newCtx;
			currType = newType;
			
			// if a non-RAW blocked closed at line end, we need to insert an empty
			// RAW block to separate the first block of potentially the same type 
			if (j == strlen)
				res.add(createSegment(str, row, j, j, Segment.RAW));
			
		}
		
		sctx.update(currCtx, currType);
		return res;
	}
	
	protected Segment createSegment(String str, int row, int start, int end, 
			int type) {
		return new Segment(row, start, end < 0 ? 
				str.substring(start) : str.substring(start, end), type);
	}
	
	protected Line createLine(Document document, int row, 
			List<Segment> segments, SegmentationContext ctx) {
		return new Line(document, row, segments);
	}
	
	protected static class SegmentationContext {
		protected SegmentPatternContext _ctx = null;
		protected int _type = Segment.RAW;
		
		public SegmentPatternContext getPattern() { return _ctx; }
		public int getType() { return _type; }
		
		public void update(SegmentPatternContext ctx, int type) {
			_ctx = ctx;
			_type = type;
		}
		
	}
	
}
