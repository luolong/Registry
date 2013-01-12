package org.luolong.collections.registry

import spock.lang.Shared
import spock.lang.Specification

public class RegistryImplementations{
    public static List<Registry> create() {
        return [new HashRegistry()]
    }
}

public class EmptyRegistry extends Specification {

    @Shared
    static List<Registry> implementations = RegistryImplementations.create();

    def "it shoud be empty"(Registry registry) {
        expect:
        registry.isEmpty();

        where:
        registry << implementations;
    }

    def "it has zero size"(Registry registry) {
        expect:
        registry.size() == 0;

        where:
        registry << implementations;
    }

    def "it has no entries"(Registry registry) {
        expect:
        registry.entrySet().isEmpty();

        where:
        registry << implementations;
    }

    def "it has no keys"(Registry registry) {
        expect:
        registry.keySet().isEmpty();

        where:
        registry << implementations;
    }

    def "it has no values"(Registry registry) {
        expect:
        registry.values().isEmpty();

        where:
        registry << implementations;
    }
}

public class RegistryUsage extends Specification {

    @Shared
    static List<Registry> implementations = RegistryImplementations.create();

    def "put should add an entry to the registry"(Registry registry){
        def key = IdentityKey.of(String.class)

        when:
        registry.put(key, "String");

        then:
        registry.get(key) == "String"

        where:
        registry << implementations;
    }
}