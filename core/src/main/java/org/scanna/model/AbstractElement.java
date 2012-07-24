/* CodeElement.java

{{IS_NOTE
 Purpose:
  
 Description:
  
 History:
  Oct 28, 2011 5:56:23 PM , Created by simonpai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.scanna.model;

/**
 * 
 * @author simonpai
 */
public abstract class AbstractElement {
	
	protected final int _row0, _col0;
	
	protected AbstractElement(int row0, int col0) {
		_row0 = row0;
		_col0 = col0;
	}
	
	public int getRow() {
		return _row0;
	}
	
	public int getCol() {
		return _col0;
	}
	
	@Override
	public String toString() {
		return "[" + _row0 + ", " + _col0 + "]";
	}
	
}
