/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern;
import org.scanna.util.Texts;

/**
 * 
 * @author simonpai
 */
public class QuotedPattern extends StaticPattern {
	
	protected final char _quote, _escape;
	protected final boolean _escapable;
	
	public QuotedPattern(int type, char quote, String name) {
		this(type, quote, '\\', true, name);
	}
	
	public QuotedPattern(int type, char quote, char escape, 
			boolean escapable, String name) {
		super(type, name);
		_quote = quote;
		_escape = escape;
		_escapable = escapable;
	}
	
	@Override
	protected int match0(String str, int index) {
		int i = str.indexOf(_quote, index);
		return i < 0 ? SegmentPattern.NOT_FOUND : i;
	}
	
	@Override
	protected int end0(String str, int start) {
		int j = !_escapable ? str.indexOf(_quote, start) :
			Texts.indexOfUnescaped(str, _quote, '\\', start < 0 ? 0 : start + 1);
		return j < 0 ? SegmentPattern.NOT_FOUND : j + 1;
	}
	
}
