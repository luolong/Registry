package org.luolong.collections.registry;

/**
 * Common registry key generation.
 */
public class Keys {
    private Keys() {/* No instantiation allowed */}

    public static <T> Registry.Key<T> of(Class<T> type) {
        return new TypeKey<T>(type);
    }

    public static <T> IdentityKey<T> identity(Class type){
        return new IdentityKey<T>(type);
    }

    public static <T> NamedKey<T> of(Class<T> type, String name) {
        return new NamedKey<T>(type, name);
    }
}
