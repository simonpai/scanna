/*
 * 
 */
package org.scanna.event;

import org.scanna.event.EventEngine.Console;

/**
 * The handler interface of {@link Event}, invoked by {@link EventEngine}.
 * @see Scanner
 * @author simonpai
 */
public interface EventHandler<T, C> {
	
	/** Handle the event.
	 */
	public void run(Event<T> event, Console<C> console);
	
}
