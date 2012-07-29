/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern;
import org.scanna.segment.SegmentPatternContext;

/**
 * 
 * @author simonpai
 */
public class UnionSegmentPattern implements SegmentPattern {
	
	protected final SegmentPattern[] _ptns;
	
	public UnionSegmentPattern(SegmentPattern[] patterns) {
		_ptns = patterns.clone();
	}
	
	@Override
	public SegmentPatternContext match(String str, int index) {
		SegmentPatternContext ctx = null;
		int i = -1;
		for (SegmentPattern ptn : _ptns) {
			SegmentPatternContext ctxd = ptn.match(str, index);
			if (ctxd != null) {
				int j = ctxd.start();
				if (i < 0 || j < i) {
					i = j;
					ctx = ctxd;
				}
			}
		}
		return ctx;
	}
	
}
