/* HashMapSet.java

{{IS_NOTE
 Purpose:
  
 Description:
  
 History:
  Dec 1, 2011 4:48:09 PM , Created by simonpai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.scanna.struct.mapc.impl;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.scanna.struct.mapc.MapSet;

/**
 * An implementation of {@link MapSet}, with {@link LinkedHashSet} as its default
 * underlying Set, and {@link TreeMap} as the underlying Map.
 * @author simonpai
 */
public class TreeMapSet<K, V> extends AbstractMapCollection<K, V, Set<V>> 
		implements MapSet<K, V> {
	
	@Override
	protected Map<K, Set<V>> newMapInstance() {
		return new TreeMap<K, Set<V>>();
	}
	
	@Override
	protected Set<V> newCollectionInstance() {
		return new LinkedHashSet<V>();
	}
	
}
