package com.github.developframework.toolkit.base;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 语法糖
 * @author qiuzhenhao
 */
public final class Sugar {

    private Sugar() {
        throw new AssertionError("No com.github.developframework.toolkit.base.Sugar instances for you!");
    }

    /**
     * 判定存在
     * @param value
     * @param <T>
     * @return true为存在
     */
    public static <T> boolean exist(T value) {
        return value != null;
    }

    public static <T> void assistExist(T value, RuntimeException e) {
        if(value == null) {
            throw e;
        }
    }

    public static <T> T use(T value, RuntimeException e) {
        if(exist(value)) {
            return value;
        } else {
            throw e;
        }
    }

    public static <T> void use(T value, RuntimeException e, Consumer<T> consumer) {
        if(exist(value)) {
            consumer.accept(value);
        } else {
            throw e;
        }
    }

    public static <T, R> R use(T value, RuntimeException e, Function<T, R> function) {
        if(exist(value)) {
            return function.apply(value);
        } else {
            throw e;
        }
    }

    public static <T> T useElseDefault(T value, T defaultValue) {
        if(exist(value)) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public static <T> void useElseDefault(T value, T defaultValue, Consumer<T> consumer) {
        if(exist(value)) {
            consumer.accept(value);
        } else {
            consumer.accept(defaultValue);
        }
    }

    public static <T, R> R useElseDefault(T value, T defaultValue, Function<T, R> function) {
        if(exist(value)) {
            return function.apply(value);
        } else {
            return function.apply(defaultValue);
        }
    }

    public static <T> T useElseSupplier(T value, Supplier<T> supplier) {
        if(exist(value)) {
            return value;
        } else {
            return supplier.get();
        }
    }

    public static <T> void useElseSupplier(T value, Supplier<T> supplier, Consumer<T> consumer) {
        if(exist(value)) {
            consumer.accept(value);
        } else {
            consumer.accept(supplier.get());
        }
    }

    public static <T, R> R useElseSupplier(T value, Supplier<T> supplier, Function<T, R> function) {
        if(exist(value)) {
            return function.apply(value);
        } else {
            return function.apply(supplier.get());
        }
    }
}
