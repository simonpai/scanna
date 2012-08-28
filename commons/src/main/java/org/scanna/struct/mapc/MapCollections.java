/* MathCollections.java

{{IS_NOTE
 Purpose:
  
 Description:
  
 History:
  Dec 27, 2011 12:05:44 PM , Created by simonpai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.scanna.struct.mapc;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * {@link MapCollection} related utilities.
 * @author simonpai
 */
public class MapCollections {
	
	/** Return an unmodifiable {@link MapSet}.
	 */
	public static <K, V> MapSet<K, V> unmodMapSet(final MapSet<K, V> mset) {
		return new ReadOnlyMapSet<K, V>(mset);
	}
	
	/** Return an unmodifiable {@link MapList}.
	 */
	public static <K, V> MapList<K, V> unmodMapList(final MapList<K, V> mlist) {
		return new ReadOnlyMapList<K, V>(mlist);
	}
	
	/** Return an unmodifiable {@link MapCollection}.
	 */
	public static <K, V, C extends Collection<V>> MapCollection<K, V, C> 
			unmodMapCollection(final MapCollection<K, V, C> mcoll) {
		return new ReadOnlyMapCollection<K, V, C>(mcoll);
	}
	
	/** Skeleton of a proxy {@link MapCollection}.
	 */
	public static class ProxyMapCollection<K, V, C extends Collection<V>> 
			implements MapCollection<K, V, C> {
		
		protected final MapCollection<K, V, C> _m;
		
		public ProxyMapCollection(MapCollection<K, V, C> m) { _m = m; }
		
		// delegate //
		public void add(K key, V value) { _m.add(key, value); }
		public void clear() { _m.clear(); }
		public boolean contains(K key, V value) { return _m.contains(key, value); }
		public boolean containsKey(Object key) { return _m.containsKey(key); }
		public boolean containsValue(Object value) { return _m.containsValue(value); }
		public Set<Entry<K, C>> entrySet() { return _m.entrySet(); }
		public C get(K key, boolean autoCreate) { return _m.get(key, autoCreate); }
		public C get(Object key) { return _m.get(key); }
		public boolean isEmpty() { return _m.isEmpty(); }
		public Set<K> keySet() { return _m.keySet(); }
		public C put(K key, C value) { return _m.put(key, value); }
		public void putAll(Map<? extends K, ? extends C> m) { _m.putAll(m); }
		public boolean remove(K key, V value) { return _m.remove(key, value); }
		public C remove(Object key) { return _m.remove(key); }
		public int size() { return _m.size(); }
		public Collection<C> values() { return _m.values(); }
		public int hashCode() { return _m.hashCode(); }
		
	}
	
	/** Read only skeleton of a proxy {@link MapCollection}.
	 */
	public static class ReadOnlyMapCollection<K, V, C extends Collection<V>> 
			extends ProxyMapCollection<K, V, C> {
		
		public ReadOnlyMapCollection(MapCollection<K, V, C> m) { super(m); }
		
		public void add(K key, V value) {
			throw new UnsupportedOperationException("Read only");
		}
		public void clear() {
			throw new UnsupportedOperationException("Read only");
		}
		public C put(K key, C value) {
			throw new UnsupportedOperationException("Read only");
		}
		public void putAll(Map<? extends K, ? extends C> m) {
			throw new UnsupportedOperationException("Read only");
		}
		public boolean remove(K key, V value) {
			throw new UnsupportedOperationException("Read only");
		}
		public C remove(Object key) {
			throw new UnsupportedOperationException("Read only");
		}
		
	}
	
	/** Read only skeleton of a proxy {@link MapSet}.
	 */
	public static class ReadOnlyMapSet<K, V> extends ReadOnlyMapCollection<K, V, Set<V>> 
			implements MapSet<K, V> {
		public ReadOnlyMapSet(MapCollection<K, V, Set<V>> m) { super(m); }
	}
	
	/** Read only skeleton of a proxy {@link MapList}.
	 */
	public static class ReadOnlyMapList<K, V> extends ReadOnlyMapCollection<K, V, List<V>> 
			implements MapList<K, V> {
		public ReadOnlyMapList(MapCollection<K, V, List<V>> m) { super(m); }
	}
	
}
