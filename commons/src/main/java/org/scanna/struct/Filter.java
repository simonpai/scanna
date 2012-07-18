/*
 * 
 */
package org.scanna.struct;

/** Interface of filter on type {@link T}.
 * @author simonpai
 */
public interface Filter<T> {
	
	/** Filter the items.
	 * @return true to keep the item, false to drop.
	 */
	public boolean keep(T item);
	
}
