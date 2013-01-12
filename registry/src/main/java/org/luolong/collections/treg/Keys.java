package org.luolong.collections.treg;

/**
 * Common registry key generation.
 */
public class Keys {
    private Keys() {/* No instantiation allowed */}

    public static <T> Registry.Key<T> of(Class<T> type) {
        return new TypeKey<>(type);
    }

    public static <T> Registry.Key<T> identity(Class type){
        return new IdentityKey<T>(type);
    }

    private static class TypeKey<T> implements Registry.Key<T>{
        private final Class<T> type;
        TypeKey(Class<T> type){
            this.type = type;
        }
        @Override
        public Class<T> getType() {
            return type;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof TypeKey){
                return this.type.equals(((TypeKey) other).getType());
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
}
