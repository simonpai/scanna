/*
 * 
 */
package org.scanna.struct.iter;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.scanna.struct.Factory;

/**
 * 
 * @author simonpai
 */
public class SingletonIterator<T> implements Iterator<T> {
	
	protected final Factory<T> _fac;
	protected boolean _done = false;
	
	public SingletonIterator(final T item) {
		this(new Factory<T>() {
			public T supply() { return item; }
		});
	}
	
	public SingletonIterator(Factory<T> factory) { _fac = factory; }
	
	/** Supply the single item in this iterator. */
	public T supply() { return _fac.supply(); }
	
	@Override
	public boolean hasNext() {
		return !_done;
	}
	
	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();
		_done = true;
		return supply();
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
}
