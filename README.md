Generic Registry of type safe key-value pairs
=============================================

[![Build Status](https://drone.io/github.com/luolong/Registry/status.png)](https://drone.io/github.com/luolong/Registry/latest)

Main premise of this exercise is to devise a type-safe api for keeping and accessing generic registry of key-value pairs.

Usually such registries are implemented using some variation of `Object -> Object` or `String -> Object` mappings.

The trouble with this approach is that key-value pairs in this type of registry are essentially untyped. 
There is no indication in the API as to which key maps to a value of what type.

The `Registry` interface aims to give this type of type-safety back by binding a type of a key to the type of the value.

**An example:**

    Registry reg = ...
    Registry.Key<String> stringKey = Keys.of(String.class, "string");
    reg.put(stringKey, "A string value");
    
    Person customer = ...
    Registry.Key<Person> personKey = Keys.of(Person.class, "customer");
    reg.put(personKey, customer);
    
    ...
    
    System.out.println(reg.get(stringKey)); // will print "A string value" to stdout
    System.out.println(reg.get(personKey).getName()); // will print customer name to stdout

This library provides an interface for a Registry, base interface and implementation of a Key type and basic implementation 
of a registry that wraps a Map.

Main interface of the registry is quite similar to that of a Map with the following main differences:

* get/put methods are type bound to the type of the key.
* Additional methods to return set of the keys, values or entries by type.
* Methods for returning sub-registries of given type.
