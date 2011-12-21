/**
 * 
 */
package org.luolong.collections.treg;

import org.luolong.collections.treg.Registry.Key;

/**
 * Basic implementation of a typed registry key.
 * 
 * The equality and hash code of this key implementation are both based on the
 * identity of the instance of this key.
 * 
 * @author Roland Tepp
 */
public class TypedKey<T> implements Key<T> {

	protected final Class<T> type;

	public TypedKey(Class<T> type) {
		this.type = type;
	}

	public Class<T> getType() {
		return type;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}

	@Override
	public int hashCode() {
		return System.identityHashCode(this);
	}

	public static <T> TypedKey<T> of(Class<T> type) {
		return new TypedKey<T>(type);
	}
}
