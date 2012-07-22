/*
 * 
 */
package org.scanna.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.scanna.struct.Handler;

/**
 * 
 * @author simonpai
 */
public class ClassExtensions {
	
	/** Scan through static members of a class and generate code for delegation.
	 * @param clazz
	 * @param exporter
	 */
	public static void exportStaticMembers(Class<?> clazz, Handler<String> exporter) {
		exportStaticFields(clazz, exporter);
		exportStaticMethods(clazz, exporter);
	}
	
	/** Scan through static fields of a class and generate code for delegation.
	 * @param clazz
	 * @param exporter
	 */
	public static void exportStaticFields(Class<?> clazz, Handler<String> exporter) {
		final String cn = clazz.getName();
		for (Field f : clazz.getDeclaredFields()) {
			int mod = f.getModifiers();
			if (!Modifier.isPublic(mod) || !Modifier.isFinal(mod) || !Modifier.isStatic(mod))
				continue;
			Type ft = f.getGenericType();
			String fn = f.getName();
			
			// doc
			StringBuilder sb = new StringBuilder();
			sb.append("\t/** @see ").append(cn).append("#").append(fn).append(" */\n\t");
			
			// declaration
			if (mod > 0)
				sb.append(Modifier.toString(mod)).append(" ");
			if (ft instanceof Class)
				sb.append(getTypeName((Class<?>) ft)); // TODO: type param, type bound, unprefix
			else
				sb.append(ft.toString());
			sb.append(" ").append(fn).append(" = ").append(cn).append(".").append(fn).append(";");
			
			exporter.handle(sb.toString());
		}
	}
	
	/** Scan through static methods of a class and generate code for delegation.
	 * @param clazz
	 * @param exporter
	 */
	public static void exportStaticMethods(Class<?> clazz, Handler<String> exporter) {
		final String cn = clazz.getName();
		for (Method m : clazz.getDeclaredMethods()) {
			int mod = m.getModifiers();
			if (!Modifier.isPublic(mod) || !Modifier.isStatic(mod))
				continue;
			final String mn = m.getName();
			final Type[] params = m.getGenericParameterTypes();
			final Type genRetType = m.getGenericReturnType();
			final int paramLen = params.length;
			final boolean retVoid = (genRetType instanceof Class<?>) && 
					"void".equals(((Class<?>) genRetType).getName());
			
			// ignore main method
			if ("main".equals(mn) && retVoid && paramLen == 1) // TODO: String[]
				continue;
			// from JDK 1.6
			try {
				// doc
				StringBuilder sb = new StringBuilder();
				sb.append("\t/** @see ").append(cn).append("#").append(mn).append("(");
				for (int j = 0; j < paramLen; j++) {
					Type pt = params[j];
					if (pt instanceof Class)
						sb.append(getTypeName((Class<?>) pt));
					else if (pt instanceof ParameterizedType)
						sb.append(((Class<?>) ((ParameterizedType) pt).getRawType()).getName());
					else
						sb.append(pt.toString());
					sb.append(" arg").append(j+1);
					if (j < (paramLen - 1))
						sb.append(", ");
				}
				sb.append(") */\n");
				
				// modifier
				sb.append("\tpublic static ");
				
				// type parameter
				Type[] typeparms = m.getTypeParameters();
				if (typeparms.length > 0) {
					boolean first = true;
					sb.append("<");
					for (Type typeparm : typeparms) {
						if (!first)
							sb.append(", ");
						
						/*
						if (typeparm instanceof TypeVariable) {
							TypeVariable<?> tv = (TypeVariable<?>) typeparm;
							Type[] bounds = tv.getBounds(); // TODO: type bound
						}
						*/
						if (typeparm instanceof Class)
							sb.append(((Class<?>) typeparm).getName());
						else
							sb.append(typeparm.toString());
						
						first = false;
					}
					sb.append("> ");
				}
				
				// return type
				sb.append(((genRetType instanceof Class) ? 
					getTypeName((Class<?>) genRetType) : 
						unprev(genRetType.toString())) + " ");
				
				// method name, parameter
				sb.append(mn + "(");
				for (int j = 0; j < paramLen; j++) {
					sb.append((params[j] instanceof Class) ? 
						getTypeName((Class<?>) params[j]) : 
						(params[j].toString())); // TODO: remove "java.lang." prefix
					sb.append(" arg").append(j+1);
					if (j < (paramLen - 1))
						sb.append(", ");
				}
				sb.append(")");
				
				// exception
				Type[] exceptions = m.getGenericExceptionTypes();
				if (exceptions.length > 0) {
					sb.append(" throws ");
					for (int k = 0; k < exceptions.length; k++) {
						sb.append((exceptions[k] instanceof Class) ? 
							((Class<?>) exceptions[k]).getName() : exceptions[k].toString());
						if (k < (exceptions.length - 1))
							sb.append(", ");
					}
				}
				
				// delegate
				sb.append(" {\n\t\t");
				if (!retVoid)
					sb.append("return ");
				sb.append(cn + "." + mn + "(");
				for (int j = 0; j < paramLen; j++) {
					sb.append("arg").append(j+1);
					if (j < (paramLen - 1))
						sb.append(", ");
				}
				sb.append(");\n\t}");
				
				exporter.handle(sb.toString());
			} catch (Exception e) {} // ignore for now
		}
	}
	
	// from JDK 1.6
	private static String getTypeName(Class<?> type) {
		if (type.isArray()) {
			try {
				Class<?> c = type;
				int dimensions = 0;
				while (c.isArray()) {
					dimensions++;
					c = c.getComponentType();
				}
				StringBuffer sb = new StringBuffer();
				sb.append(unprev(c.getName()));
				for (int i = 0; i < dimensions; i++)
					sb.append("[]");
				return sb.toString();
			} catch (Throwable e) {} /* FALLTHRU */
		}
		return unprev(type.getName());
	}
	
	private static String unprev(String str) {
		return Texts.unprefix(str, "java.lang.");
	}
	
}
