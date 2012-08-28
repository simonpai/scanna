/*
 * 
 */
package org.scanna.struct.mapc;

import java.util.Collection;
import java.util.Map;

/**
 * A {@link Map} of {@link Collection} values.
 * @author simonpai
 */
public interface MapCollection<K, V, C extends Collection<V>> extends Map<K, C> {
	
	/** Add an object into the collection retrieved by given key. If the map
	 * does not contains the key, a collection will be automatically created.
	 */
	public void add(K key, V value);
	
	/** Remove the object from the collection retrieved by given key. If the map
	 * does not contains the key, nothing is changed.
	 */
	public boolean remove(K key, V value);
	
	/** Retrieve the collection by given key, with an option to create collection
	 * instance automatically if not found.
	 */
	public C get(K key, boolean autoCreate);
	
	/** Return true if the collection retrieved by given key contains the given
	 * value.
	 */
	public boolean contains(K key, V value);
	
}
