/*
 * 
 */
package org.scanna.segment;

/**
 * The definition of {@link Segment} pattern.
 * @author simonpai
 */
public interface SegmentPattern {
	
	/** 
	 */
	public static final int NOT_FOUND = -1;
	
	/** 
	 */
	public static final int END_OF_LINE = -2;
	
	/** 
	 */
	public static final int CONTINUED = -3;
	
	/** Return a {@link SegmentPatternContext} if the pattern finds a match.
	 * @param str the target string
	 * @param index the starting index of searching, always non-negative and 
	 * less than string length
	 * @return either a {@link SegmentPatternContext} or null if not found.
	 */
	public SegmentPatternContext match(String str, int index);
	
}
