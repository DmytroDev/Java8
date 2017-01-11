package com.dataart.function.interfaces;

@FunctionalInterface
public interface Transform<F> {
    String transform(F from);
}
