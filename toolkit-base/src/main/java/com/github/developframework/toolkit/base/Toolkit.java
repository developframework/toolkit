package com.github.developframework.toolkit.base;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 工具箱
 * @author qiuzhenhao
 */
public final class Toolkit {

    private Toolkit() {
        throw new AssertionError("No com.github.developframework.toolkit.base.Toolkit instances for you!");
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

    /**
     * 断言存在，不存在将会抛出异常e
     * @param value
     * @param e
     * @param <T>
     */
    public static <T> void assistExist(T value, RuntimeException e) {
        if(value == null) {
            throw e;
        }
    }

    /**
     * 使用value，不存在将会抛出异常e
     * @param value
     * @param e
     * @param <T>
     * @return
     */
    public static <T> T use(T value, RuntimeException e) {
        if(exist(value)) {
            return value;
        } else {
            throw e;
        }
    }

    /**
     * 使用value，用于无返回值的consumer，不存在将会抛出异常e
     * @param value
     * @param e
     * @param consumer
     * @param <T>
     */
    public static <T> void use(T value, RuntimeException e, Consumer<T> consumer) {
        if(exist(value)) {
            consumer.accept(value);
        } else {
            throw e;
        }
    }

    /**
     * 使用value，用于有返回值的function，不存在将会抛出异常e
     * @param value
     * @param e
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R use(T value, RuntimeException e, Function<T, R> function) {
        if(exist(value)) {
            return function.apply(value);
        } else {
            throw e;
        }
    }

    /**
     * 使用value，不存在将会使用defaultValue
     * @param value
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T useElseDefault(T value, T defaultValue) {
        if(exist(value)) {
            return value;
        } else {
            return defaultValue;
        }
    }

    /**
     * 使用value，不存在将会使用defaultValue，用于无返回值的consumer
     * @param value
     * @param defaultValue
     * @param consumer
     * @param <T>
     */
    public static <T> void useElseDefault(T value, T defaultValue, Consumer<T> consumer) {
        if(exist(value)) {
            consumer.accept(value);
        } else {
            consumer.accept(defaultValue);
        }
    }

    /**
     * 使用value，不存在将会使用defaultValue，用于有返回值的function
     * @param value
     * @param defaultValue
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R useElseDefault(T value, T defaultValue, Function<T, R> function) {
        if(exist(value)) {
            return function.apply(value);
        } else {
            return function.apply(defaultValue);
        }
    }

    /**
     * 使用value，不存在将会使用supplier提供值
     * @param value
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> T useElseSupplier(T value, Supplier<T> supplier) {
        if(exist(value)) {
            return value;
        } else {
            return supplier.get();
        }
    }

    /**
     * 使用value，不存在将会使用supplier提供值，用于无返回值的consumer
     * @param value
     * @param supplier
     * @param consumer
     * @param <T>
     */
    public static <T> void useElseSupplier(T value, Supplier<T> supplier, Consumer<T> consumer) {
        if(exist(value)) {
            consumer.accept(value);
        } else {
            consumer.accept(supplier.get());
        }
    }

    /**
     * 使用value，不存在将会使用supplier提供值，用于有返回值的function
     * @param value
     * @param supplier
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R useElseSupplier(T value, Supplier<T> supplier, Function<T, R> function) {
        if(exist(value)) {
            return function.apply(value);
        } else {
            return function.apply(supplier.get());
        }
    }
}
