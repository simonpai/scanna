/*
 * 
 */
package org.scanna.struct;

/** The interface that defines a conversion from type {@link T} to type {@link S}.
 * @author simonpai
 */
public interface Converter<T, S> {
	
	/** Convert from type {@link T} to {@link S}. */
	public S convert(T item);
	
}
