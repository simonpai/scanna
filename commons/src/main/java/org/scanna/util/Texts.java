/*
 * 
 */
package org.scanna.util;

/**
 * Text related utilities.
 * @author simonpai
 */
public class Texts {
	
	/**
	 * 
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	/**
	 * 
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	/** Return string with given prefix deleted, if any
	 */
	public static String removePrefix(String str, String prefix) {
		return str != null && str.startsWith(prefix) ? str.substring(prefix.length()) : str;
	}
	
	/** Return string with given suffix deleted, if any
	 */
	public static String removeSuffix(String str, String suffix) {
		return str != null && str.endsWith(suffix) ? str.substring(0, str.length() - suffix.length()) : str;
	}
	
	// conventions //
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String setterize(String str) {
		return "set" + capitalize(str);
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String getterize(String str) {
		return "get" + capitalize(str); // TODO: isXXX
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String toAttrName(String str) {
		int off = str.startsWith("is") ? 2 : 3;
		return Character.toLowerCase(str.charAt(off)) + str.substring(off + 1);
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		if (isBlank(str))
			throw new IllegalArgumentException();
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}
	
	/**
	 * 
	 */
	public static String splitByUppercase(String cn) { // TODO: move to hypothesis impl
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (int j = 1; j < cn.length(); j++) {
			char c = cn.charAt(j);
			if (Character.isUpperCase(c)) { // TODO: generalize
				sb.append(cn.substring(i, j)).append(" "); // TODO: generalize
				i = j;
			}
		}
		sb.append(cn.substring(i));
		return sb.toString();
	}
	
	
	
	// string manipulation //
	/**
	 * 
	 * @param objs
	 * @param separator
	 * @return
	 */
	public static String join(String[] objs, String separator) {
		if (objs == null || objs.length == 0)
			return "";
		StringBuilder sb = new StringBuilder(objs[0]);
		for (int i = 1; i < objs.length; i++) 
			sb.append(separator).append(objs[i]);
		return sb.toString();
	}
	
	/**
	 * 
	 */
	public static String repeat(char c, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
			sb.append(c);
		return sb.toString();
	}
	
	/**
	 * 
	 * @param str
	 * @param start
	 * @param end
	 * @param leftSymbol
	 * @param rightSymbol
	 * @return
	 */
	public static int countSymbol(String str, int start, int end, 
			char leftSymbol, char rightSymbol) {
		int sum = 0;
		for (int i = start; i < (end < 0 ? str.length() : end); i++) {
			char c = str.charAt(i);
			if (c == leftSymbol)
				sum++;
			else if (c == rightSymbol)
				sum--;
		}
		return sum;
	}
	
	
}
