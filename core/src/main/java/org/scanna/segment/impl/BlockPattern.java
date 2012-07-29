/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern;

/**
 * 
 * @author simonpai
 */
public class BlockPattern extends StaticPattern {
	
	protected final String _open, _close;
	protected final int _openlen, _closelen;
	
	public BlockPattern(int type, String open, String close, String name) {
		super(type, name);
		_open = open;
		_close = close;
		_openlen = open.length();
		_closelen = close.length();
	}
	
	@Override
	protected int match0(String str, int index) {
		int i = str.indexOf(_open, index);
		return i < 0 ? SegmentPattern.NOT_FOUND : i;
	}
	
	@Override
	protected int end0(String str, int start) {
		int j = str.indexOf(_close, start < 0 ? 0 : start + _openlen);
		return j < 0 ? SegmentPattern.NOT_FOUND : j + _closelen;
	}
	
}
