package org.luolong.collections.treg;

import java.util.Map;

/**
 * Typed registry of key-value pairs.
 * 
 * <code>Registry</code> is a map like interface for storing type safe mappings of keys to values.
 * 
 * <p>This interface differs from the usual {@link Map} interface in that the type safety is maintained 
 * on the individual key-value pair level instead of declaring it on the class level.</p>
 * 
 * <p>This can be useful in the scenarios that have traditionally used map instances that allow 
 * values of arbitrary type to be stored, like various service or other type of registries.</p>
 * 
 * <p>Due to the specifics of the type safety that are to be enforced on the individual entry leve,
 * keys usd in the mappings can not be <code>null</code> and attempting to insert retrieve values 
 * using <code>null</code> as a key can be considered illegal operation and should throw 
 * <code>NullPointerException</code>. 
 * 
 * @author Roland Tepp
 */
public interface Registry {

	/**
	 * Typed entry.
	 * 
	 * @author Roland Tepp
	 *
	 * @param <T> type of the value contained in this entry.
	 */
	public static interface Entry<T> {
		Key<T> getKey();
		T getValue();
	}
	
	/**
	 * Typed key
	 * 
	 * @author Roland Tepp
	 *
	 * @param <T> type of the value that can be mapped to this key
	 */
	public static interface Key<T> {
		Class<T> getType();
	}
	
	
	/**
	 * Returns the value to which the specified key is mapped, or <code>null</code>
	 * if this map contains no mapping for the key.
	 * 
	 * <p>More formally, if this map contains a mapping from a key <code>k</code> 
	 * to a value <code>v</code> such that <code>(key==null ? k==null : key.equals(k))</code>,
	 * then this method returns <code>v</code>; otherwise it returns <code>null</code>. 
	 * (There can be at most one such mapping.)</p>
	 * 
	 * <p>If this map permits <code>null</code> values, then a return value of <code>null</code>
	 * does not necessarily indicate that the map contains no mapping for the key; it's also 
	 * possible that the map explicitly maps the key to <code>null</code>. 
	 * The <code>containsKey</code> operation may be used to distinguish these two cases.</p>
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or <code>null</code> if this map
	 *         contains no mapping for the key
     * 
     * @throws ClassCastException if the value is of an inappropriate type for this key
     * @throws NullPointerException if the provided key is <code>null</code>
	 */
	<T> T get(Key<T> key);
	
	/**
	 * Associates the specified value with the specified key in this registry (optional operation). 
	 * If the registry previously contained a mapping for the key, the old value is replaced by the 
	 * specified value. (A registry m is said to contain a mapping for a key <code>k</code> if and 
	 * only if <code>r.containsKey(k)</code> would return <code>true</code>.)
	 */
	<T> void put(Key<T> key, T value);

	/**
	 * Returns contents of this registry as a {@link Map map}.
	 * 
	 * @return content of the registry as a 
	 */
	Map<? extends Key<?>, ?> asMap();
}
