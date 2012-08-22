/*
 * 
 */
package org.scanna.event;

import org.scanna.Line;

/**
 * TODO
 * @author simonpai
 */
public class Event {
	
	protected final Line _line;
	protected final int _col;
	
	public Event(Line line, int col) {
		_line = line;
		_col = col;
	}
	
	public Line getLine() {
		return _line;
	}
	
	public int getColumn() {
		return _col;
	}
	
}
