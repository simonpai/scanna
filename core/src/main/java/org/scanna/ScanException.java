/*
 * 
 */
package org.scanna;

/**
 * 
 * @author simonpai
 */
public class ScanException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	protected final Line _line;
	protected final String _txt;
	protected final int _column;
	
	// TODO: String reason
	
	/**
	 * 
	 * @param line
	 * @param column
	 */
	public ScanException(Line line, int column) {
		this(line, line.getContent(), column);
	}
	
	/**
	 * 
	 * @param txt
	 * @param column
	 */
	public ScanException(String txt, int column) {
		this(null, txt, column);
	}
	
	/**
	 * 
	 * @param line
	 * @param txt
	 * @param column
	 */
	public ScanException(Line line, String txt, int column) {
		super();
		_line = line;
		_txt = txt;
		_column = column;
	}
	
	@Override
	public String toString() {
		return "ScanException: " + 
				(_column < 0 ? "" : "col " + _column + " @ ") + 
				(_line != null ? _line : _txt);
	}
	
}
