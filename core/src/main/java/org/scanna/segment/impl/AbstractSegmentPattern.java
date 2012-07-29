package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern;

/**
 * The skeleton implementation of {@link SegmentPattern}.
 * @author simonpai
 */
public abstract class AbstractSegmentPattern implements SegmentPattern {
	
	protected final String _name;
	protected final int _type;
	
	public AbstractSegmentPattern(int type, String name) {
		_type = type;
		_name = name;
	}
	
	@Override
	public String toString() {
		return _name != null ? _name : super.toString();
	}
	
	// TODO: exception handling?
	
}
