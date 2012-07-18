/*
 * 
 */
package org.scanna.struct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author simonpai
 */
public class JointIterator<T> implements Iterator<T> {
	
	private final Iterator<? extends Iterable<T>> _outer;
	private Iterator<T> _inner, _prev;
	
	public JointIterator(Iterator<? extends Iterable<T>> iterator) {
		_outer = iterator;
	}
	
	@Override
	public boolean hasNext() {
		while ((_inner == null || !_inner.hasNext()) && _outer.hasNext())
			_inner = _outer.next().iterator();
		return _inner != null && _inner.hasNext();
	}
	
	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();
		_prev = _inner;
		return _inner.next();
	}
	
	@Override
	public void remove() {
		if (_prev != null)
			_prev.remove();
	}
	
}
