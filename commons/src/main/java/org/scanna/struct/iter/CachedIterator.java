/**
 * 
 */
package org.scanna.struct.iter;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** A skeleton implementation of @{link Iterator}.
 * @author simonpai
 */
public abstract class CachedIterator<T> implements Iterator<T> {
	
	protected boolean _ready = false;
	protected T _next;
	
	@Override
	public boolean hasNext() {
		loadNext();
		return _next != null;
	}
	
	@Override
	public T next() {
		if(!hasNext()) throw new NoSuchElementException();
		_ready = false;
		return _next;
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	public T peek() {
		if(!hasNext()) throw new NoSuchElementException();
		return _next;
	}
	
	protected void loadNext() {
		if (_ready)
			return;
		_next = seekNext();
		_ready = true;
	}
	
	/** Returns next element for iterator, null if not available. */
	protected abstract T seekNext();
	
}
