/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern;

/**
 * 
 * @author simonpai
 */
public class LinePattern extends StaticPattern {
	
	protected final String _target;
	
	public LinePattern(String target, int type, String name) {
		super(type, name);
		_target = target;
	}
	
	@Override
	protected int match0(String str, int index) {
		int i = str.indexOf(_target, index);
		return i < 0 ? SegmentPattern.NOT_FOUND : i;
	}
	
	@Override
	protected int end0(String str, int start) {
		return SegmentPattern.END_OF_LINE;
	}
	
}
