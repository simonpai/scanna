/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern;
import org.scanna.segment.SegmentPatternContext;
import org.scanna.util.Texts;

/**
 * 
 * @author simonpai
 */
public class QuotedSegmentPattern extends AbstractSegmentPattern {
	
	protected final char _quote, _escape;
	protected final boolean _escapable;
	
	public QuotedSegmentPattern(int type, char quote, String name) {
		this(type, quote, '\\', true, name);
	}
	
	public QuotedSegmentPattern(int type, char quote, char escape, 
			boolean escapable, String name) {
		super(type, name);
		_quote = quote;
		_escape = escape;
		_escapable = escapable;
	}
	
	@Override
	public SegmentPatternContext match(String str, int index) {
		int i = str.indexOf(_quote, index);
		return i < 0 ? null : new AbstractSegmentPatternContext(i, _type) {
			public int end0(String str, int start) {
				int j = !_escapable ? str.indexOf(_quote, start) :
					Texts.indexOfUnescaped(str, _quote, '\\', start < 0 ? 0 : start + 1);
				return j < 0 ? SegmentPattern.NOT_FOUND : j + 1;
			}
		};
	}
	
}
