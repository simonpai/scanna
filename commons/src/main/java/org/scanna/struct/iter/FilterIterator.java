/*
 * 
 */
package org.scanna.struct.iter;

import java.util.Iterator;

import org.scanna.struct.Filter;

/**
 * 
 * @author simonpai
 */
public class FilterIterator<T> extends CachedIterator<T> {
	
	protected final Iterator<T> _iter;
	protected final Filter<T> _filter;
	
	public FilterIterator(Iterator<T> iterator, Filter<T> filter) {
		_iter = iterator;
		_filter = filter;
	}
	
	/** TODO
	 * @return
	 */
	protected boolean keep(T item) {
		return _filter.keep(item);
	}
	
	@Override
	protected T seekNext() {
		if (!_iter.hasNext())
			return null;
		T candidate = _iter.next();
		while (!keep(candidate)) {
			if (!_iter.hasNext())
				return null;
			candidate = _iter.next();
		}
		return candidate;
	}
	
}
