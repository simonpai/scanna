/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPatternContext;

/**
 * TODO: remove
 * @author simonpai
 */
public abstract class AbstractPatternContext implements SegmentPatternContext {
	
	protected final int _start, _type;
	
	protected AbstractPatternContext(int start, int type) {
		_start = start;
		_type = type;
	}
	
	@Override
	public int start() {
		return _start;
	}
	
	public abstract int end0(String str, int start);
	
	@Override
	public int[] end(String str, int start) {
		return new int[] { end0(str, start), _type };
	}
	
}
