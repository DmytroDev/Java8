package com.dataart.monads.optional;

import java.util.HashMap;
import java.util.Optional;

public class MapNpeProtection<T, U> extends HashMap<T, U> {
    public Optional<U> find(T key) {
        return Optional.ofNullable(super.get(key));
    }
}
