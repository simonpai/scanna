/*
 * 
 */
package org.scanna.segment;

import org.scanna.util.Texts;

/**
 * A segment of line in document.
 * @author simonpai
 */
public class Segment {
	
	/** The raw type. */
	public static final int RAW = 0xFFFF;
	/** comment */
	public static final int COMMENT = 0xFF00;
	/** documentation */
	public static final int DOCUMENTATION = 0xFF01;
	/** single quoted string */
	public static final int SINGLE_QUOTED = 0xFF02;
	/** double quoted string */
	public static final int DOUBLE_QUOTED = 0xFF03;
	
	
	
	private final String _content;
	private final int _type, _col, _row;
	private final Object _data;
	
	/** Constructor
	 */
	public Segment(int row, int col, String content, int type) {
		this(row, col, content, type, null);
	}
	
	/** Constructor
	 * @param data Custom data object to store any additional information.
	 */
	public Segment(int row, int col, String content, int type, Object data) {
		if (content == null)
			throw new IllegalArgumentException("Content cannot be null.");
		_content = content;
		_type = type;
		_col = col;
		_row = row;
		_data = data;
	}
	
	/** Return the content of this segment.
	 */
	public String getContent() {
		return _content;
	}
	
	/** Return the type of this segment.
	 */
	public int getType() {
		return _type;
	}
	
	/** Return the row number of this segment.
	 */
	public int getRow() {
		return _row;
	}
	
	/** Return the column number of this segment.
	 */
	public int getCol() {
		return _col;
	}
	
	/** Retrieve custom data object, if any.
	 */
	public Object getData() {
		return _data;
	}
	
	/** Return true if the content string is blank.
	 */
	public boolean isBlank() {
		return Texts.isBlank(_content);
	}
	
	@Override
	public String toString() {
		return _content;
	}
	
}
