/*
 * 
 */
package org.scanna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scanna.document.Document;
import org.scanna.segment.Segment;
import org.scanna.segment.SegmentMask;

/**
 * A segmentation of a line of string in a {@link Document}.
 * @author simonpai
 */
public class Line {
	
	protected final Document _document;
	protected final int _row;
	protected int _len = -1;
	
	public Line(Document document, int row, List<Segment> segments) {
		_document = document;
		_row  = row;
		_segments = segments;
	}
	
	/** Return the {@link Document} which this line belongs to.
	 */
	public Document getDocument() {
		return _document;
	}
	
	/** Return the 1-based row number of this line in the document.
	 */
	public int getRow() {
		return _row;
	}
	
	/** Return the length of raw content string.
	 * @see #getContent()
	 */
	public int length() {
		if (_len < 0)
			_len = getContent().length();
		return _len;
	}
	
	
	
	// segment //
	protected final List<Segment> _segments;
	protected final Map<SegmentMask, String> _contentCache = 
			new HashMap<SegmentMask, String>();
	
	/** Return the raw content string of this line.
	 */
	public String getContent() {
		return getContent(null);
	}
	
	/** Return a masked string depending the mask option.
	 * @param mask // TODO
	 */
	public String getContent(SegmentMask mask) {
		String cached = _contentCache.get(mask);
		if (cached == null) {
			StringBuilder sb = new StringBuilder();
			for (Segment seg : _segments)
				sb.append(mask != null ? mask.mask(seg) : seg.content());
			_contentCache.put(mask, cached = sb.toString());
		}
		return cached;
	}
	
	/** Return a read only list of {@link Segment}.
	 */
	public List<Segment> getSegments() {
		return Collections.unmodifiableList(_segments);
	}
	
	/** Return true if this line contains a {@link Segment} of given type.
	 * @param type a segment type. See {@link Segment#type()}.
	 */
	public boolean hasType(int type) {
		for (Segment seg : _segments)
			if (seg.type() == type)
				return true;
		return false;
	}
	
	/** 
	 */
	public int[] indicesOf(int type) {
		List<Integer> list = new ArrayList<Integer>();
		int i = 0;
		for (Segment seg : _segments) {
			if (seg.type() == type)
				list.add(i);
			i++;
		}
		int[] res = new int[list.size()];
		for (int j = 0; j < list.size(); j++)
			res[j] = list.get(j);
		return res;
	}
	
	
	
	@Override
	public String toString() {
		return getContent();
	}
	
}
