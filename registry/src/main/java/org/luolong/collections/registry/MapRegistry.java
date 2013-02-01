package org.luolong.collections.registry;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Basic implementation of Registry using {@code java.util.Map} for
 * internal storage of key-value pairs.
 *
 * @author Roland Tepp
 */
public class MapRegistry implements Registry {

    // Internal map storage of registry entries
    private final Map<Object, Object> map;

    public MapRegistry() {
        this.map = new HashMap<>();
    }

    public MapRegistry(@NotNull Map<Object, Object> impl) {
        if (Objects.requireNonNull( impl, "Map implementation can not be null").isEmpty() ) {
            throw new IllegalArgumentException("Initial map implementation must be empty!");
        }
        this.map = impl;
    }

    public <T> T get(@NotNull Key<T> key) {
        Object value = map.get(key);
        return key.getType().cast(value);
    }

    public <T> void put(@NotNull Key<T> key, @Nullable T value) {
        map.put(key, value);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public int size() {
        return map.size();
    }

    @Override
    public Set<Key<?>> keySet() {
        final Set<Object> keys = map.keySet();
        return new AbstractSet<Key<?>>() {
            @Override
            public Iterator<Key<?>> iterator() {
                final Iterator<Key<?>> iterator = iterator();
                return new Iterator<Key<?>>() {
                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public Key<?> next() {
                        return iterator.next();
                    }

                    @Override
                    public void remove() {
                        iterator.remove();
                    }
                };
            }

            @Override
            public int size() {
                return keys.size();
            }
        };  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<?> values() {
        return map.values();
    }

    @Override
    public Set<Entry<?>> entrySet() {
        final Set<Map.Entry<Object, Object>> entries = map.entrySet();
        return new AbstractSet<Entry<?>>() {
            @Override
            public Iterator<Entry<?>> iterator() {
                final Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
                return new Iterator<Entry<?>>() {
                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public Entry<?> next() {
                        final Map.Entry<Object, Object> entry = iterator.next();
                        return new Entry<Object>() {
                            @Override
                            public Key<Object> getKey() {
                                return (Key<Object>) entry.getKey();
                            }

                            @Override
                            public Object getValue() {
                                return entry.getValue();
                            }

                            @Override
                            public Object setValue(Object value) {
                                return entry.setValue(value);
                            }
                        };
                    }

                    @Override
                    public void remove() {
                        iterator.remove();
                    }
                };
            }

            @Override
            public int size() {
                return entries.size();
            }
        };

    }
}
