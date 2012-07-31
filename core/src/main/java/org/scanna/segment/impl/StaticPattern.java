/*
 * 
 */
package org.scanna.segment.impl;

import org.scanna.segment.SegmentPattern;

/**
 * Skeleton implementation for {@link SegmentPattern} that does not require state.
 * @author simonpai
 */
public abstract class StaticPattern extends AbstractPattern {
	
	public StaticPattern(int type, String name) {
		super(type, name);
	}
	
	/** Return the index at which the pattern starts.
	 * @return the start index, or {@link SegmentPattern#NOT_FOUND} if not found.
	 * @see Context#start()
	 */
	protected abstract int match0(String str, int index);
	
	/** Return the index at which the pattern ends.
	 * @param start the start index of this pattern. 
	 * {@link SegmentPattern#CONTINUED} means the pattern starts in one of 
	 * previous lines in the document. The value is either 
	 * {@link SegmentPattern#CONTINUED}, or a non-negative number less than the 
	 * string length.
	 * @return either number greater than or equal to the start index which 
	 * is less than the string length, {@link SegmentPattern#NOT_FOUND} if not 
	 * found, or {@link SegmentPattern#END_OF_LINE} if the pattern extends 
	 * exactly to the end of line (like line comment, for example).
	 * @see Context#end(String, int)
	 */
	protected abstract int end0(String str, int start);
	
	@Override
	public Context match(String str, int index) {
		final int i = match0(str, index);
		return i < 0 ? null : new Context() {
			public int start() {
				return i;
			}
			public int[] end(String str, int start) {
				return new int[] { end0(str, start), _type };
			}
		};
	}
	
}
