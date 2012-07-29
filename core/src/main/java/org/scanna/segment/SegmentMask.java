/*
 * 
 */
package org.scanna.segment;

/**
 * Interface for {@link Segment} masking strategy.
 * @author simonpai
 */
public interface SegmentMask {
	
	/** Return a string depending on the masking strategy.
	 */
	public String mask(Segment segment);
	
}
