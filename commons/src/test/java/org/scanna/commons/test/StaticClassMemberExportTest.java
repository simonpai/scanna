/*
 * 
 */
package org.scanna.commons.test;

import java.io.IOException;

import org.scanna.util.ClassExtensions;
import org.scanna.util.Handlers;

/** Code-gen test.
 * @author simonpai
 */
public class StaticClassMemberExportTest {
	
	public static final int MY_INT = 0;
	public static final java.util.List<String> STR_LIST = null;
	public static final String STR = null;
	public static final String[] STR_ARRAY = null;
	
	public static void m1() {}
	public static int m2(boolean b) { return 0; }
	public static String m3(Object obj1, Object obj2) { return null; }
	public static String[] m4(Object[] obj1, Object ... obj2) { return null; }
	public static <T> java.util.Map<T, String> m5(java.util.List<String> list) throws IOException { return null; }
	
	public static void main(String[] args) {
		final Class<?> clazz = StaticClassMemberExportTest.class;
		ClassExtensions.exportStaticMembers(clazz, Handlers.PRINTLN);
	}
	
}
