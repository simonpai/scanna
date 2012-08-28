/* HashMapList.java

{{IS_NOTE
 Purpose:
  
 Description:
  
 History:
  Sep 6, 2011 12:16:16 PM , Created by simonpai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.scanna.struct.mapc.impl;

import java.util.LinkedList;
import java.util.List;

import org.scanna.struct.mapc.MapList;

/**
 * An implementation of {@link MapList}, with {@link LinkedList} as its default
 * underlying List.
 * @author simonpai
 */
public class HashMapList<K, V> extends AbstractMapCollection<K, V, List<V>> 
		implements MapList<K, V> {
	
	@Override
	protected List<V> newCollectionInstance() {
		return new LinkedList<V>();
	}
	
}
