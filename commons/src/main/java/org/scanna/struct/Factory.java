/*
 * 
 */
package org.scanna.struct;

/** Interface for item generation.
 * @author simonpai
 */
public interface Factory<T> {
	
	/** Create the item. */
	public T supply();
	
}
