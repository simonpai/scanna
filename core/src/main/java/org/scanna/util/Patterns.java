/*
 * 
 */
package org.scanna.util;

import java.util.regex.Pattern;

/**
 * 
 * @author simonpai
 */
public class Patterns {
	
	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public static Pattern keywordPattern(String keyword) {
		return keywordPattern(keyword, "\\b");
	}
	
	/**
	 * 
	 * @param keyword
	 * @param boundary
	 * @return
	 */
	public static Pattern keywordPattern(String keyword, String boundary) {
		return Pattern.compile(boundary + "(" + keyword + ")" + boundary);
	}
	
}
