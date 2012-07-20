/*
 * 
 */
package org.scanna.commons.task;

import org.scanna.util.ClassExtensions;
import org.scanna.util.Handlers;

/** Generate code for delegating static members to {@link java.util.Collections}.
 * @author simonpai
 */
public class CodeGenCollectionsClass {
	
	public static void main(String[] args) {
		final Class<?> clazz = java.util.Collections.class;
		ClassExtensions.exportStaticMembers(clazz, Handlers.PRINTLN);
	}
	
}
