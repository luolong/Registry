package org.luolong.collections.treg;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic implementation of Registry on top of HashMap.
 * 
 * @author Roland Tepp
 */
public class HashRegistry implements Registry {

	private final Map<Object, Object> map;

	public HashRegistry() {
		this.map = new HashMap<Object, Object>();
	}

	public <T> T get(Key<T> key) {
		Object value = map.get(key);
		return key.getType().cast(value);
	}

	public <T> void put(Key<T> key, T value) {
		map.put(key, value);
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public int size() {
		return map.size();
	}
}
