/*
 * 
 */
package org.scanna.event;

import org.scanna.Line;

/**
 * An abstraction to represent the occurrence of some pattern in document. For
 * instance, variable declaration, value assignment, function call, etc.
 * @author simonpai
 */
public class Event<T> {
	
	/** The event name. */
	public final String name;
	
	/** The Line on which Event occurs. */
	public final Line line;
	
	/** Column number at which Event occurs. */
	public final int column;
	
	/** Row number at which Event occurs. */
	public final int row;
	
	/** Event data. */
	public final T data;
	
	/** Construct an Event.
	 */
	public Event(String name, T data, Line line, int column) {
		this.name = name;
		this.line = line;
		this.column = column;
		this.row = line.getRow();
		this.data = data;
	}
	
}
