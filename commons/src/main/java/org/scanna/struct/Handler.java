/*
 * 
 */
package org.scanna.struct;

/** A general interface of handler of type {@link T}.
 * @author simonpai
 */
public interface Handler<T> {
	
	/** Dealing with item of type {@link T}.
	 */
	public void run(T item);
	
}
