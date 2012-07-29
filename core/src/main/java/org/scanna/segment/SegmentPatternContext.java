/*
 * 
 */
package org.scanna.segment;

/**
 * 
 * @author simonpai
 */
public interface SegmentPatternContext {
	
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
