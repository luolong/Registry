package org.luolong.collections.treg;

import java.util.HashMap;
import java.util.Map;

import org.luolong.collections.treg.Registry.Key;

/**
 * An implementation of a {@link Registry} backed by an {@link HashMap}.
 * 
 * @author Roland Tepp
 */
public class HashRegistry implements Registry {

	private HashMap<Object, Object> map;

	public HashRegistry() {
		map = new HashMap<Object, Object>();
	}
	
	public HashRegistry(int initialCapacity) {
		map = new HashMap<Object, Object>(initialCapacity);
	}
	

	public <T> T get(Key<T> key) {
		return key.getType().cast(map.get(key));
	}

	public <T> void put(Key<T> key, T value) {
		map.put(key, value);
	}

	public Map<? extends Key<?>, ?> asMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
