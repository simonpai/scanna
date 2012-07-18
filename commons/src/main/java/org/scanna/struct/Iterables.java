/*
 * 
 */
package org.scanna.struct;

import java.util.Iterator;

/** Collection of utilities for {@link Iterable}.
 * @author simonpai
 */
public class Iterables {
	
	/** Converts an {@link Iterable} of type {@link T} to an {@link Iterable} 
	 * of type {@link S}, by a {@link Converter}.
	 */
	public static <T, S> Iterable<S> convert(final Iterable<T> iterable, 
			final Converter<T, S> converter) {
		return new Iterable<S>() {
			public Iterator<S> iterator() {
				return new ConversionIterator<T, S>(iterable.iterator(), converter);
			}
		};
	}
	
	/** Create an {@link Iterable} from an {@link Iterable} of {@link Iterable}s. */
	public static <T> Iterable<T> join(final Iterable<? extends Iterable<T>> iterable) {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new JointIterator<T>(iterable.iterator());
			}
		};
	}
	
	/** TODO
	 */
	public static <T> Iterable<T> filter(final Iterable<T> iterable, final Filter<T> filter) {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new FilterIterator<T>(iterable.iterator(), filter);
			}
		};
	}
	
	/** TODO
	 */
	public static <T> Iterable<T> singleton(final Factory<T> factory) {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new SingletonIterator<T>(factory);
			}
		};
	}
	
	/** TODO
	 */
	public static <T> Iterable<T> singleton(final T item) {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new SingletonIterator<T>(item);
			}
		};
	}
	
}
