/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern;

/**
 * 
 * @author simonpai
 */
public class CollectivePattern implements SegmentPattern {
	
	protected final SegmentPattern[] _ptns;
	
	public CollectivePattern(SegmentPattern[] patterns) {
		_ptns = patterns.clone();
	}
	
	@Override
	public Context match(String str, int index) {
		Context ctx = null;
		int i = -1;
		for (SegmentPattern ptn : _ptns) {
			Context ctxd = ptn.match(str, index);
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
