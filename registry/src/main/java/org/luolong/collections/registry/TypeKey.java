package org.luolong.collections.registry;

class TypeKey<T> implements Registry.Key<T> {

    private final Class<T> type;

    public TypeKey(Class<T> type) {
        this.type = type;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TypeKey) {
            return type.equals(((TypeKey) other).type);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public String toString() {
        return "Key<" + type.getSimpleName() + ">";
    }
}
