/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPatternContext;

/**
 * 
 * @author simonpai
 */
public class KeywordPattern extends AbstractPattern {
	
	protected final String[] _keywords;
	
	public KeywordPattern(String[] keywords, int type, String name) {
		super(type, name);
		_keywords = keywords.clone();
	}
	
	@Override
	public SegmentPatternContext match(String str, int index) {
		// TODO: may depend on _keywords size to determine the implementation
		return null;
	}
	
}
