/* AdaptedCachedIterator.java

{{IS_NOTE
 Purpose:
  
 Description:
  
 History:
  Sep 5, 2011 11:17:35 AM , Created by simonpai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.scanna.struct;

import java.util.Iterator;

/** An iterator of type {@link S} converted from an iterator of type {@link T} 
 * according to a given {@link Converter}. 
 * @author simonpai
 */
public class ConversionIterator<T, S> implements Iterator<S> {
	
	protected final Converter<T, S> _conv;
	protected final Iterator<T> _iter;
	
	public ConversionIterator(Iterator<T> iter, Converter<T, S> converter) {
		_iter = iter;
		_conv = converter;
	}
	
	/** Convert the item by simply calling {@link Converter#convert(Object)}.
	 * Override the change the behavior.
	 */
	protected S convert(T item) {
		return _conv.convert(item);
	}
	
	@Override
	public boolean hasNext() {
		return _iter.hasNext();
	}
	
	@Override
	public S next() {
		return convert(_iter.next());
	}
	
	@Override
	public void remove() {
		_iter.remove();
	}
	
}
