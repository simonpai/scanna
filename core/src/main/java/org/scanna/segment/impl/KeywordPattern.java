/*
 * 
 */
package org.scanna.segment.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.scanna.util.Patterns;
import org.scanna.util.Texts;

/**
 * 
 * @author simonpai
 */
public class KeywordPattern extends AbstractPattern {
	
	protected final String _boundary;
	protected final Pattern _regexPtn;
	
	public KeywordPattern(String[] keywords, int type, String name) {
		this(keywords, null, type, name);
	}
	
	public KeywordPattern(String[] keywords, String boundary, int type, String name) {
		super(type, name);
		// TODO: check keyword content: non empty \w word
		_boundary = boundary;
		String kws = Texts.join(keywords, "|");
		_regexPtn = boundary == null ? Patterns.keywordPattern(kws) : 
			Patterns.keywordPattern(kws, boundary);
	}
	
	protected int getType(String word) {
		return _type;
	}
	
	@Override
	public Context match(String str, int index) {
		Matcher m = _regexPtn.matcher(str);
		if (!m.find(index))
			return null;
		int i = m.start(1);
		final String w = m.group(1);
		return new SimpleContext(i, getType(w)) {
			public int end0(String str, int start) {
				return start + w.length();
			}
		};
	}
	
}
