/*
 * 
 */
package org.scanna.event;

import org.scanna.Line;

/**
 * The scanner unit for {@link EventEngine}.
 * @author simonpai
 */
public interface Scanner<C> {
	
	/** The callback invoked for each Line in scanning, before the registered 
	 * EventHandler callbacks.
	 */
	public void onStart(Line line, EventEngine.Console<C> console);
	
	/** The callback invoked for each Line in scanning, after the registered 
	 * EventHandler callbacks.
	 */
	public void onEnd(Line line, EventEngine.Console<C> console);
	
	/** Register {@link EventHandler} in this callback.
	 */
	public void doRegistration(EventEngine.Handlers<C> handlers);
	
	/** Return the prerequisite Scanners. The listed Scanners will be included
	 * in the EventEngine and precede this Scanner.
	 * @return null is considered as empty array.
	 */
	public Scanner<?>[] getPrerequisites();
	
	/** Create the context object for the scanner to keep any state information
	 * during the scanning. 
	 * As the Scanner itself is considered static, a context object is 
	 * necessary if the scanning is stateful across Lines. The object will
	 * be created at the beginning of the scan, and can be retrieved from
	 * {@link EventEngine.Console}.
	 * 
	 * @return null is an acceptable return value
	 */
	public C newContext();
	
	// TODO: context clean up callback
	
}
