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
	
	/** Return a {@link Context} if the pattern finds a match.
	 * @param str the target string
	 * @param index the starting index of searching, always non-negative and 
	 * less than string length
	 * @return either a {@link Context} or null if not found.
	 */
	public Context match(String str, int index);
	
	/**
	 * 
	 * @author simonpai
	 */
	public interface Context {
		
		/** Return the start index of the pattern.
		 */
		public int start();
		
		/** Return the index where the pattern ends with pattern type.
		 * @param str the target string
		 * @param start the start index of this pattern. 
		 * {@link SegmentPattern#CONTINUED} means the pattern starts in one of 
		 * previous lines in the document. The value is either 
		 * {@link SegmentPattern#CONTINUED}, or a non-negative number less than the 
		 * string length.
		 * @return an integer array of length 2, with the first integer being the 
		 * end index, and the second integer being pattern type. The end index 
		 * should be either number greater than or equal to the start index which 
		 * is less than the string length, {@link NOT_FOUND} if not found, or 
		 * {@link END_OF_LINE} if the pattern extends exactly to the end of line
		 * (like line comment, for example).
		 */
		public int[] end(String str, int start);
		
	}
	
}
