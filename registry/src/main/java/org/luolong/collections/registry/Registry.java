package org.luolong.collections.registry;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Typed registry of key-value pairs.
 * <p/>
 * <code>Registry</code> is a map like interface for storing type safe mappings of keys to values.
 * <p/>
 * <p>This interface differs from the usual {@link Map} interface in that the type safety is maintained
 * on the individual key-value pair level instead of declaring it on the class level.</p>
 * <p/>
 * <p>This can be useful in the scenarios that have traditionally used map instances that allow
 * values of arbitrary type to be stored, like various service or other type of registries.</p>
 * <p/>
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
     * <p/>
     * The <tt>Registry.entrySet</tt> method returns a collection-view
     * of the registry, whose elements are of this class.
     * The <i>only</i> way to obtain a reference to a registry entry is
     * from the iterator of this collection-view.  These <tt>Registry.Entry</tt>
     * objects are valid <i>only</i> for the duration of the iteration; more formally,
     * the behavior of a registry entry is undefined if the backing registry has been
     * modified after the entry was returned by the iterator, except through
     * the <tt>setValue</tt> operation on the registry entry.
     *
     * @param <T> type of the value of this registry entry.
     * @author Roland Tepp
     * @see Registry#entrySet()
     */
    public static interface Entry<T> {
        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         * @throws IllegalStateException implementations may, but are not
         *                               required to, throw this exception if the entry has been
         *                               removed from the backing registry.
         */
        Key<T> getKey();

        /**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing registry (by the iterator's
         * <tt>remove</tt> operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         * @throws IllegalStateException implementations may, but are not
         *                               required to, throw this exception if the entry has been
         *                               removed from the backing registry.
         */
        T getValue();

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the registry.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the registry (by the iterator's <tt>remove</tt> operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws UnsupportedOperationException if the <tt>put</tt> operation
         *                                       is not supported by the backing registry
         * @throws ClassCastException            if the class of the specified value
         *                                       prevents it from being stored in the backing registry
         * @throws NullPointerException          if the backing registry does not permit
         *                                       null values, and the specified value is null
         * @throws IllegalArgumentException      if some property of this value
         *                                       prevents it from being stored in the backing registry
         * @throws IllegalStateException         implementations may, but are not
         *                                       required to, throw this exception if the entry has been
         *                                       removed from the backing registry.
         */
        T setValue(T value);
    }

    /**
     * Typed key of a registry entry.
     * <p/>
     * Value that corresponds to this key in a ragistry is typechecked
     * to be of the prescribed type of this key.
     *
     * @param <T> type of the value that can be mapped to this key
     * @author Roland Tepp
     */
    public static interface Key<T> {
        Class<T> getType();
    }


    /**
     * Returns the value to which the specified key is mapped, or <code>null</code>
     * if this map contains no mapping for the key.
     * <p/>
     * <p>More formally, if this map contains a mapping from a key <code>k</code>
     * to a value <code>v</code> such that <code>(key==null ? k==null : key.equals(k))</code>,
     * then this method returns <code>v</code>; otherwise it returns <code>null</code>.
     * (There can be at most one such mapping.)</p>
     * <p/>
     * <p>If this map permits <code>null</code> values, then a return value of <code>null</code>
     * does not necessarily indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to <code>null</code>.
     * The <code>containsKey</code> operation may be used to distinguish these two cases.</p>
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or <code>null</code> if this map
     *         contains no mapping for the key
     * @throws ClassCastException   if the value is of an inappropriate type for this key
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
     * Returns <tt>true</tt> if this registry contains no key-value mappings.
     *
     * @return <tt>true</tt> if this registry contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Returns the number of key-value mappings in this registry.  If the
     * registry contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this registry
     * @see java.util.Map#size()
     */
    int size();

    /**
     * Returns a {@link Set} view of the mappings contained in this registry.
     * The set is backed by the registry, so changes to the registry are
     * reflected in the set, and vice-versa.  If the registry is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a set entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the registry, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this registry
     * @see java.util.Map#entrySet()
     */
    Set<Entry<?>> entrySet();

    /**
     * Returns a {@link Set} view of the keys contained in this registry.
     * The set is backed by the registry, so changes to the registry are
     * reflected in the set, and vice-versa.  If the registry is modified
     * while an iteration over the registry is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the registry, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this registry
     */
    Set<Key<?>> keySet();

    /**
     * Returns a {@link java.util.Collection} view of the values contained in this registry.
     * The collection is backed by the registry, so changes to the registry are
     * reflected in the collection, and vice-versa.  If the registry is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the registry, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map
     */
    Collection<?> values();

}
