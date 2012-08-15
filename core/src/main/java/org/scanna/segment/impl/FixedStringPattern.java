/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern;

/**
 * 
 * @author simonpai
 */
public class FixedStringPattern extends StaticPattern {
	
	protected final int _len;
	protected final String _target;
	
	public FixedStringPattern(int type, String target, String name) {
		super(type, name);
		_target = target;
		_len = target.length();
	}
	
	@Override
	protected int match0(String str, int index) {
		int i = str.indexOf(_target, index);
		return i < 0 ? SegmentPattern.NOT_FOUND : i;
	}
	
	@Override
	protected int end0(String str, int start) {
		return start + _len;
	}
	
}
