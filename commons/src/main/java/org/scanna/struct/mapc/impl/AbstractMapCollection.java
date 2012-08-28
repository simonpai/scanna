/* HashMapCollection.java

{{IS_NOTE
 Purpose:
  
 Description:
  
 History:
  Dec 27, 2011 6:11:18 PM , Created by simonpai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.scanna.struct.mapc.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.scanna.struct.mapc.MapCollection;

/**
 * The skeleton implementation of {@link MapCollection}. The derived subclass
 * only needs to implement {@link #newCollectionInstance()} to complete the
 * implementation. The default type of the underlying Map is {@link LinkedHashMap},
 * which can be replaced by overriding {@link #newMapInstance()}.
 * @author simonpai
 */
public abstract class AbstractMapCollection<K, V, C extends Collection<V>> 
		implements MapCollection<K, V, C> {
	
	protected final Map<K, C> _m = newMapInstance();
	
	/** Create the Map instance. Default type is {@link LinkedHashMap}. 
	 */
	protected Map<K, C> newMapInstance() {
		return new LinkedHashMap<K, C>();
	}
	
	/** Create the Collection instance when necessary.
	 */
	protected abstract C newCollectionInstance();
	
	
	
	// map collection //
	@Override
	public void add(K key, V value) {
		touch(key).add(value);
	}
	@Override
	public boolean remove(K key, V value) {
		C list = get(key);
		return list != null && list.remove(value);
	}
	@Override
	public C get(K key, boolean autoCreate) {
		return autoCreate ? touch(key) : get(key);
	}
	@Override
	public boolean contains(K key, V value) {
		C list = get(key);
		return list != null && list.contains(value);
	}
	protected final C touch(K key) {
		C list = get(key);
		if (list == null)
			put(key, list = newCollectionInstance());
		return list;
	}
	
	
	
	// delegation //
	public void clear() { _m.clear(); }
	public boolean containsKey(Object key) { return _m.containsKey(key); }
	public boolean containsValue(Object value) { return _m.containsValue(value); }
	public Set<Entry<K, C>> entrySet() { return _m.entrySet(); }
	public C get(Object key) { return _m.get(key); }
	public boolean isEmpty() { return _m.isEmpty(); }
	public Set<K> keySet() { return _m.keySet(); }
	public C put(K key, C value) { return _m.put(key, value); }
	public void putAll(Map<? extends K, ? extends C> m) { _m.putAll(m); }
	public C remove(Object key) { return _m.remove(key); }
	public int size() { return _m.size(); }
	public Collection<C> values() { return _m.values(); }
	
	// object //
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_m == null) ? 0 : _m.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractMapCollection<?, ?, ?> other = (AbstractMapCollection<?, ?, ?>) obj;
		if (_m == null) {
			if (other._m != null)
				return false;
		} else if (!_m.equals(other._m))
			return false;
		return true;
	}
	
}
