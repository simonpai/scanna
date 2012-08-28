/* TreeMapList.java

{{IS_NOTE
 Purpose:
  
 Description:
  
 History:
  Sep 13, 2011 6:46:52 PM , Created by simonpai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.scanna.struct.mapc.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.scanna.struct.mapc.MapList;

/**
 * An implementation of {@link MapList}, with {@link LinkedList} as its default
 * underlying List, and {@link TreeMap} as the underlying Map. 
 * @author simonpai
 */
public class TreeMapList<K, V> extends AbstractMapCollection<K, V, List<V>> 
		implements MapList<K, V> {
	
	@Override
	protected Map<K, List<V>> newMapInstance() {
		return new TreeMap<K, List<V>>();
	}
	
	@Override
	protected List<V> newCollectionInstance() {
		return new LinkedList<V>();
	}
	
}
