/*
 * 
 */
package org.scanna.util;

import org.scanna.struct.Handler;

/** Common;y used {@link Handler} collection.
 * @author simonpai
 */
public class Handlers {
	
	/** String {@link Handler} that System.out.println() the item.
	 */
	public static final Handler<String> PRINTLN = new Handler<String>() {
		public void handle(String item) { System.out.println(item); }
	};
	
}
