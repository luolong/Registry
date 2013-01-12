/**
 * 
 */
package org.luolong.collections.registry;

import org.luolong.collections.registry.Registry.Key;

/**
 * Basic implementation of a typed registry key.
 * 
 * The equality and hash code of this key implementation are both based on the
 * identity of the instance of this key.
 * 
 * @author Roland Tepp
 */
public class IdentityKey<T> implements Key<T> {

	private final Class<T> type;

	public IdentityKey(Class<T> type) {
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

    public String toString() {
        return "Key<" + type.getSimpleName() + ">@" + hashCode();
    }

	public static <T> IdentityKey<T> of(Class<T> type) {
		return new IdentityKey<T>(type);
	}
}
