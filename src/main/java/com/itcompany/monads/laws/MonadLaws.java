package com.itcompany.monads.laws;

import java.util.Optional;
import java.util.function.Function;

public class MonadLaws {

    private static final Integer value = 42;
    private static Optional<Integer> monadicValue = Optional.of(value);
    private static Function<Integer, Optional<Integer>> function = a -> Optional.of(a * 2);
    private static Function<Integer, Optional<Integer>> optionalOf = Optional::of; // or just Optional::of

    private static Function<Integer, Optional<Integer>> g = a -> Optional.of(a * 3);
    private static Function<Integer, Optional<Integer>> function_flatMap_g = a -> function.apply(a).flatMap(g);
    // function_flatMap_g should use both - #function and #g in order to satisfy 3rd monad law;
    // if #function or #g is changed, the low won't work. Use the field name as a hint.

    /**
     * Law 1, Left Identity
     * <p/>
     * The first monad law:
     * if we take a value, put it in a default context with return and then feed it to a function by using ".",
     * it’s the same as just taking the value and applying the function to it.
     */
    public static Boolean leftIdentityLaw() {
        final Optional res1 = Optional.of(value).flatMap(function);
        final Optional res2 = function.apply(value);
        return (res1.get().equals(res2.get())) && (res1.get().equals(84));
    }

    /**
     * Law 2, Right Identity
     * <p/>
     * The second monad law:
     * if we have a monadic value and we use "." to feed
     * it to return, the result is our original monadic value.
     */
    public static Boolean rightIdentityLaw() {
        return monadicValue.flatMap(optionalOf).equals(monadicValue);
    }

    /**
     * Law 3, Associativity
     * <p/>
     * The third monad law:
     * When we have a chain of monadic function
     * applications with ".", it shouldn’t matter how they’re nested.
     */
    public static Boolean associativityLaw() {
        return monadicValue.flatMap(function).flatMap(g).equals(monadicValue.flatMap(function_flatMap_g));
    }

}
