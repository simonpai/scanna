/*
 * 
 */
package org.scanna.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scanna.Line;
import org.scanna.document.Document;
import org.scanna.event.Event;
import org.scanna.segment.Segment;
import org.scanna.segment.SegmentMask;

/**
 * A segmentation of a line of string in a {@link Document}.
 * @author simonpai
 */
public class LineImpl implements Line {
	
	protected final Document _document;
	protected final int _row;
	protected final List<Segment> _segments;
	
	protected final Map<SegmentMask, String> _contentCache = 
			new HashMap<SegmentMask, String>();
	
	public LineImpl(Document document, int row, List<Segment> segments) {
		_document = document;
		_row  = row;
		_segments = segments;
	}
	
	@Override
	public Document getDocument() {
		return _document;
	}
	
	@Override
	public int getRow() {
		return _row;
	}
	
	@Override
	public String getContent() {
		return getContent(null);
	}
	
	@Override
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
	
	@Override
	public List<Segment> getSegments() {
		return Collections.unmodifiableList(_segments);
	}
	
	@Override
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
	public Iterable<Event> getEvents() {
		return Collections.emptyList();
	}
	
	@Override
	public String toString() {
		return getContent();
	}
	
}