/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPatternContext;

/**
 * 
 * @author simonpai
 */
public class KeywordSegmentPattern extends AbstractSegmentPattern {
	
	// TODO
	
	public KeywordSegmentPattern(int type, String name) {
		super(type, name);
	}
	
	@Override
	public SegmentPatternContext match(String str, int index) {
		return null;
	}
	
}
