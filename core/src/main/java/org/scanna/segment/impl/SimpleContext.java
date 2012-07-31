/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern.Context;

/**
 * 
 * @author simonpai
 */
public abstract class SimpleContext implements Context {
	
	protected final int _start, _type;
	
	protected SimpleContext(int start, int type) {
		_start = start;
		_type = type;
	}
	
	@Override
	public int start() {
		return _start;
	}
	
	/** 
	 */
	public abstract int end0(String str, int start);
	
	@Override
	public int[] end(String str, int start) {
		return new int[] { end0(str, start), _type };
	}
	
}
