/*
 * 
 */
package org.scanna.event;

import org.scanna.Line;

/**
 * TODO
 * @author simonpai
 */
public class Event implements Comparable<Event> {
	
	protected final Line _line;
	protected final int _col, _row;
	
	public Event(Line line, int col) {
		_line = line;
		_col = col;
		_row = line.getRow();
	}
	
	public Line getLine() {
		return _line;
	}
	
	public int getRow() {
		return _row;
	}
	
	public int getColumn() {
		return _col;
	}
	
	@Override
	public int compareTo(Event evt) {
		int rdiff = _row - evt._row;
		return rdiff != 0 ? rdiff : _col - evt._col;
	}
	
}
