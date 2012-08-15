/*
 * 
 */
package org.scanna.document.impl;

import org.scanna.document.Document;

/** Skeleton implementation of {@link Document}.
 * @author simonpai
 */
public abstract class AbstractDocument implements Document {
	
	protected String _name;
	
	protected AbstractDocument(String name) {
		_name = name;
	}
	
	@Override
	public String getName() {
		return _name;
	}
	
	@Override
	public String toString() {
		return _name;
	}
	
}
