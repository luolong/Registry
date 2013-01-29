package org.luolong.collections.registry;

import org.luolong.collections.registry.Registry.Key;

import java.util.Objects;

/**
 * Basic implementation of a typed registry key with a name.
 *
 * @author Roland Tepp
 */
public class NamedKey<T> implements Key<T> {

    private final Class<T> type;
    private final String name;

    public NamedKey(Class<T> type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NamedKey) {
            NamedKey other = (NamedKey) obj;
            if (Objects.equals(type, other.type)) {
                return Objects.equals(name, other.name);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "NamedKey<" + type.getSimpleName() + ", " + name + ">";
    }
}
