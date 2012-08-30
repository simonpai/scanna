/*
 * 
 */
package org.scanna.util;

import org.scanna.struct.Handler;

/**
 * Common logging helpers.
 * @author simonpai
 */
public class Logs {
	
	private static long _timestamp = -1;
	
	/**
	 * 
	 * @return
	 */
	public static long time() {
		return time(Handlers.PRINTLN, "Time elapsed: %1$d ms");
	}
	
	/**
	 * 
	 * @param logHandler
	 * @param format
	 * @return
	 */
	public static long time(Handler<String> logHandler, String format) {
		long t = new java.util.Date().getTime();
		if (_timestamp < 0) {
			_timestamp = t;
			return -1;
		}
		long diff = t - _timestamp;
		if (logHandler != null && format != null)
			logHandler.handle(String.format(format, diff));
		_timestamp = t;
		return diff;
	}
	
}
