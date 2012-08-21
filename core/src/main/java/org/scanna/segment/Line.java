/*
 * 
 */
package org.scanna.segment;

import java.util.List;

import org.scanna.document.Document;

/**
 * A segmentation of a line of string in a {@link Document}.
 * @author simonpai
 */
public interface Line {
	
	/** Return the {@link Document} which this line belongs to.
	 */
	public Document getDocument();
	
	/** Return the 1-based row number of this line in the document.
	 */
	public int getRow();
	
	/** Return the raw content string of this line.
	 */
	public String getContent();
	
	/** Return a masked string depending the mask option.
	 * @param mask // TODO
	 */
	public String getContent(SegmentMask mask);
	
	/** Return a read only list of {@link Segment}.
	 */
	public List<Segment> getSegments();
	
	/** Return true if this line contains a {@link Segment} of given type.
	 * @param type a segment type. See {@link Segment#type()}.
	 */
	public boolean hasType(int type);
	
}
