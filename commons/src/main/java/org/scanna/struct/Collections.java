/*
 * 
 */
package org.scanna.struct;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Extended {@link java.util.Collections} utilities.
 * @author simonpai
 */
public class Collections {
	
	public static <T, S> List<S> convert(final List<T> list, 
			final Converter<T, S> readConverter) {
		return new AbstractList<S>() {
			private S rconv(T item) {
				return readConverter.convert(item);
			}
			@Override
			public S get(int index) {
				return rconv(list.get(index));
			}
			@Override
			public int size() {
				return list.size();
			}
		};
	}
	
	public static <T, S> List<S> convert(final List<T> list, 
			final Converter<T, S> readConverter, final Converter<S, T> writeConverter) {
		return new AbstractList<S>() {
			private S rconv(T item) {
				return readConverter.convert(item);
			}
			private T wconv(S item) {
				return writeConverter.convert(item);
			}
			@Override
			public S get(int index) {
				return rconv(list.get(index));
			}
			@Override
			public int size() {
				return list.size();
			}
			@Override
			public S set(int index, S element) {
				return rconv(list.set(index, wconv(element)));
			}
			@Override
			public void add(int index, S element) {
				list.add(index, wconv(element));
			}
			@Override
			public S remove(int index) {
				return rconv(list.remove(index));
			}
		};
	}
	
	// TODO
	// Java 1.7 API //
	// emptyEnumeration()
	// emptyIterator()
	// emptyListIterator()
	
	// delegate //
	/** @see java.util.Collections#EMPTY_SET */
	@SuppressWarnings("rawtypes")
	public static final Set EMPTY_SET = java.util.Collections.EMPTY_SET;
	/** @see java.util.Collections#EMPTY_LIST */
	@SuppressWarnings("rawtypes")
	public static final List EMPTY_LIST = java.util.Collections.EMPTY_LIST;
	/** @see java.util.Collections#EMPTY_MAP */
	@SuppressWarnings("rawtypes")
	public static final Map EMPTY_MAP = java.util.Collections.EMPTY_MAP;
	
	/** @see java.util.Collections#min(java.util.Collection arg1, Comparator arg2) */
	public static <T> T min(java.util.Collection<? extends T> arg1, Comparator<? super T> arg2) {
		return java.util.Collections.min(arg1, arg2);
	}
	/** @see java.util.Collections#min(java.util.Collection arg1) */
	public static <T extends Object & Comparable<? super T>> T min(java.util.Collection<? extends T> arg1) {
		return java.util.Collections.min(arg1);
	}
	/** @see java.util.Collections#max(java.util.Collection arg1, Comparator arg2) */
	public static <T> T max(java.util.Collection<? extends T> arg1, Comparator<? super T> arg2) {
		return java.util.Collections.max(arg1, arg2);
	}
	/** @see java.util.Collections#max(java.util.Collection arg1) */
	public static <T extends Object & Comparable<? super T>> T max(java.util.Collection<? extends T> arg1) {
		return java.util.Collections.max(arg1);
	}
	/** @see java.util.Collections#replaceAll(List arg1, T arg2, T arg3) */
	public static <T> boolean replaceAll(List<T> arg1, T arg2, T arg3) {
		return java.util.Collections.replaceAll(arg1, arg2, arg3);
	}
	/** @see java.util.Collections#addAll(java.util.Collection arg1, T[] arg2) */
	public static <T> boolean addAll(java.util.Collection<? super T> arg1, T[] arg2) {
		return java.util.Collections.addAll(arg1, arg2);
	}
	/** @see java.util.Collections#list(java.util.Enumeration arg1) */
	public static <T> java.util.ArrayList<T> list(java.util.Enumeration<T> arg1) {
		return java.util.Collections.list(arg1);
	}
	/** @see java.util.Collections#synchronizedCollection(java.util.Collection arg1) */
	public static <T> java.util.Collection<T> synchronizedCollection(java.util.Collection<T> arg1) {
		return java.util.Collections.synchronizedCollection(arg1);
	}
	/** @see java.util.Collections#synchronizedSet(Set arg1) */
	public static <T> Set<T> synchronizedSet(Set<T> arg1) {
		return java.util.Collections.synchronizedSet(arg1);
	}
	/** @see java.util.Collections#copy(List arg1, List arg2) */
	public static <T> void copy(List<? super T> arg1, List<? extends T> arg2) {
		java.util.Collections.copy(arg1, arg2);
	}
	/** @see java.util.Collections#reverse(List arg1) */
	public static void reverse(List<?> arg1) {
		java.util.Collections.reverse(arg1);
	}
	/** @see java.util.Collections#fill(List arg1, T arg2) */
	public static <T> void fill(List<? super T> arg1, T arg2) {
		java.util.Collections.fill(arg1, arg2);
	}
	/** @see java.util.Collections#synchronizedList(List arg1) */
	public static <T> List<T> synchronizedList(List<T> arg1) {
		return java.util.Collections.synchronizedList(arg1);
	}
	/** @see java.util.Collections#unmodifiableSet(Set arg1) */
	public static <T> Set<T> unmodifiableSet(Set<? extends T> arg1) {
		return java.util.Collections.unmodifiableSet(arg1);
	}
	/** @see java.util.Collections#synchronizedMap(Map arg1) */
	public static <K, V> Map<K, V> synchronizedMap(Map<K, V> arg1) {
		return java.util.Collections.synchronizedMap(arg1);
	}
	/** @see java.util.Collections#asLifoQueue(java.util.Deque arg1) */
	public static <T> java.util.Queue<T> asLifoQueue(java.util.Deque<T> arg1) {
		return java.util.Collections.asLifoQueue(arg1);
	}
	/** @see java.util.Collections#binarySearch(List arg1, T arg2, Comparator arg3) */
	public static <T> int binarySearch(List<? extends T> arg1, T arg2, Comparator<? super T> arg3) {
		return java.util.Collections.binarySearch(arg1, arg2, arg3);
	}
	/** @see java.util.Collections#binarySearch(List arg1, T arg2) */
	public static <T> int binarySearch(List<? extends java.lang.Comparable<? super T>> arg1, T arg2) {
		return java.util.Collections.binarySearch(arg1, arg2);
	}
	/** @see java.util.Collections#checkedCollection(java.util.Collection arg1, java.lang.Class arg2) */
	public static <E> java.util.Collection<E> checkedCollection(java.util.Collection<E> arg1, java.lang.Class<E> arg2) {
		return java.util.Collections.checkedCollection(arg1, arg2);
	}
	/** @see java.util.Collections#checkedList(List arg1, java.lang.Class arg2) */
	public static <E> List<E> checkedList(List<E> arg1, java.lang.Class<E> arg2) {
		return java.util.Collections.checkedList(arg1, arg2);
	}
	/** @see java.util.Collections#checkedMap(Map arg1, java.lang.Class arg2, java.lang.Class arg3) */
	public static <K, V> Map<K, V> checkedMap(Map<K, V> arg1, java.lang.Class<K> arg2, java.lang.Class<V> arg3) {
		return java.util.Collections.checkedMap(arg1, arg2, arg3);
	}
	/** @see java.util.Collections#checkedSet(Set arg1, java.lang.Class arg2) */
	public static <E> Set<E> checkedSet(Set<E> arg1, java.lang.Class<E> arg2) {
		return java.util.Collections.checkedSet(arg1, arg2);
	}
	/** @see java.util.Collections#checkedSortedMap(java.util.SortedMap arg1, java.lang.Class arg2, java.lang.Class arg3) */
	public static <K, V> java.util.SortedMap<K, V> checkedSortedMap(java.util.SortedMap<K, V> arg1, java.lang.Class<K> arg2, java.lang.Class<V> arg3) {
		return java.util.Collections.checkedSortedMap(arg1, arg2, arg3);
	}
	/** @see java.util.Collections#checkedSortedSet(java.util.SortedSet arg1, java.lang.Class arg2) */
	public static <E> java.util.SortedSet<E> checkedSortedSet(java.util.SortedSet<E> arg1, java.lang.Class<E> arg2) {
		return java.util.Collections.checkedSortedSet(arg1, arg2);
	}
	/** @see java.util.Collections#disjoint(java.util.Collection arg1, java.util.Collection arg2) */
	public static boolean disjoint(java.util.Collection<?> arg1, java.util.Collection<?> arg2) {
		return java.util.Collections.disjoint(arg1, arg2);
	}
	/** @see java.util.Collections#emptyList() */
	public static <T> List<T> emptyList() {
		return java.util.Collections.emptyList();
	}
	/** @see java.util.Collections#emptyMap() */
	public static <K, V> Map<K, V> emptyMap() {
		return java.util.Collections.emptyMap();
	}
	/** @see java.util.Collections#emptySet() */
	public static <T> Set<T> emptySet() {
		return java.util.Collections.emptySet();
	}
	/** @see java.util.Collections#enumeration(java.util.Collection arg1) */
	public static <T> java.util.Enumeration<T> enumeration(java.util.Collection<T> arg1) {
		return java.util.Collections.enumeration(arg1);
	}
	/** @see java.util.Collections#frequency(java.util.Collection arg1, Object arg2) */
	public static int frequency(java.util.Collection<?> arg1, Object arg2) {
		return java.util.Collections.frequency(arg1, arg2);
	}
	/** @see java.util.Collections#indexOfSubList(List arg1, List arg2) */
	public static int indexOfSubList(List<?> arg1, List<?> arg2) {
		return java.util.Collections.indexOfSubList(arg1, arg2);
	}
	/** @see java.util.Collections#lastIndexOfSubList(List arg1, List arg2) */
	public static int lastIndexOfSubList(List<?> arg1, List<?> arg2) {
		return java.util.Collections.lastIndexOfSubList(arg1, arg2);
	}
	/** @see java.util.Collections#nCopies(int arg1, T arg2) */
	public static <T> List<T> nCopies(int arg1, T arg2) {
		return java.util.Collections.nCopies(arg1, arg2);
	}
	/** @see java.util.Collections#newSetFromMap(Map arg1) */
	public static <E> Set<E> newSetFromMap(Map<E, java.lang.Boolean> arg1) {
		return java.util.Collections.newSetFromMap(arg1);
	}
	/** @see java.util.Collections#reverseOrder() */
	public static <T> Comparator<T> reverseOrder() {
		return java.util.Collections.reverseOrder();
	}
	/** @see java.util.Collections#reverseOrder(Comparator arg1) */
	public static <T> Comparator<T> reverseOrder(Comparator<T> arg1) {
		return java.util.Collections.reverseOrder(arg1);
	}
	/** @see java.util.Collections#rotate(List arg1, int arg2) */
	public static void rotate(List<?> arg1, int arg2) {
		java.util.Collections.rotate(arg1, arg2);
	}
	/** @see java.util.Collections#shuffle(List arg1, java.util.Random arg2) */
	public static void shuffle(List<?> arg1, java.util.Random arg2) {
		java.util.Collections.shuffle(arg1, arg2);
	}
	/** @see java.util.Collections#shuffle(List arg1) */
	public static void shuffle(List<?> arg1) {
		java.util.Collections.shuffle(arg1);
	}
	/** @see java.util.Collections#singleton(T arg1) */
	public static <T> Set<T> singleton(T arg1) {
		return java.util.Collections.singleton(arg1);
	}
	/** @see java.util.Collections#singletonList(T arg1) */
	public static <T> List<T> singletonList(T arg1) {
		return java.util.Collections.singletonList(arg1);
	}
	/** @see java.util.Collections#singletonMap(K arg1, V arg2) */
	public static <K, V> Map<K, V> singletonMap(K arg1, V arg2) {
		return java.util.Collections.singletonMap(arg1, arg2);
	}
	/** @see java.util.Collections#sort(List arg1, Comparator arg2) */
	public static <T> void sort(List<T> arg1, Comparator<? super T> arg2) {
		java.util.Collections.sort(arg1, arg2);
	}
	/** @see java.util.Collections#sort(List arg1) */
	public static <T extends Comparable<? super T>> void sort(List<T> arg1) {
		java.util.Collections.sort(arg1);
	}
	/** @see java.util.Collections#swap(List arg1, int arg2, int arg3) */
	public static void swap(List<?> arg1, int arg2, int arg3) {
		java.util.Collections.swap(arg1, arg2, arg3);
	}
	/** @see java.util.Collections#synchronizedSortedMap(java.util.SortedMap arg1) */
	public static <K, V> java.util.SortedMap<K, V> synchronizedSortedMap(java.util.SortedMap<K, V> arg1) {
		return java.util.Collections.synchronizedSortedMap(arg1);
	}
	/** @see java.util.Collections#synchronizedSortedSet(java.util.SortedSet arg1) */
	public static <T> java.util.SortedSet<T> synchronizedSortedSet(java.util.SortedSet<T> arg1) {
		return java.util.Collections.synchronizedSortedSet(arg1);
	}
	/** @see java.util.Collections#unmodifiableCollection(java.util.Collection arg1) */
	public static <T> java.util.Collection<T> unmodifiableCollection(java.util.Collection<? extends T> arg1) {
		return java.util.Collections.unmodifiableCollection(arg1);
	}
	/** @see java.util.Collections#unmodifiableList(List arg1) */
	public static <T> List<T> unmodifiableList(List<? extends T> arg1) {
		return java.util.Collections.unmodifiableList(arg1);
	}
	/** @see java.util.Collections#unmodifiableMap(Map arg1) */
	public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> arg1) {
		return java.util.Collections.unmodifiableMap(arg1);
	}
	/** @see java.util.Collections#unmodifiableSortedMap(java.util.SortedMap arg1) */
	public static <K, V> java.util.SortedMap<K, V> unmodifiableSortedMap(java.util.SortedMap<K, ? extends V> arg1) {
		return java.util.Collections.unmodifiableSortedMap(arg1);
	}
	/** @see java.util.Collections#unmodifiableSortedSet(java.util.SortedSet arg1) */
	public static <T> java.util.SortedSet<T> unmodifiableSortedSet(java.util.SortedSet<T> arg1) {
		return java.util.Collections.unmodifiableSortedSet(arg1);
	}
	
}
