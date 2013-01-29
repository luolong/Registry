package org.luolong.collections.registry;

import java.util.*;

/**
 * Basic implementation of Registry on top of HashMap.
 *
 * @author Roland Tepp
 */
public class HashRegistry implements Registry {

    private final Map<Key<?>, Object> map;

    public HashRegistry() {
        this.map = new HashMap<Key<?>, Object>();
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

    @Override
    public Set<Key<?>> keySet() {
        final Set<Key<?>> keys = map.keySet();
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
        final Set<Map.Entry<Key<?>, Object>> entries = map.entrySet();
        return new AbstractSet<Entry<?>>() {
            @Override
            public Iterator<Entry<?>> iterator() {
                final Iterator<Map.Entry<Key<?>, Object>> iterator = entries.iterator();
                return new Iterator<Entry<?>>() {
                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public Entry<?> next() {
                        final Map.Entry<Key<?>, Object> entry = iterator.next();
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
