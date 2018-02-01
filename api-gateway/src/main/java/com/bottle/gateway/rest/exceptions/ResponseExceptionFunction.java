package com.bottle.gateway.rest.exceptions;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created by Sergey on 26.01.2017.
 */
public interface ResponseExceptionFunction <T, R> {

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    static <T> Function<T, T> identity() {
        return t -> t;
    }

    R apply(T t) throws ResponseException;

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>    the type of input to the {@code before} function, and to the composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before} function and then applies
     * this function
     * @throws NullPointerException if before is null
     * @see #andThen(Function)
     */
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> {
            try {
                return apply(before.apply(v));
            } catch (ResponseException e) {
                return null;
            }
        };
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>   the type of output of the {@code after} function, and of the composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then applies the {@code after}
     * function
     * @throws NullPointerException if after is null
     * @see #compose(Function)
     */
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            try {
                return after.apply(apply(t));
            } catch (ResponseException e) {

                e.printStackTrace();
                return null;
            }
        };
    }
}
