package com.github.zerkseez.reflection;

import java.util.function.Supplier;

public class Cache<T> {
    private final Object lock;
    private final Supplier<T> supplier;
    private volatile boolean cached = false;
    private T value = null;

    public Cache(final Object lock, final Supplier<T> supplier) {
        this.lock = lock;
        this.supplier = supplier;
    }

    public T get() {
        if (!cached) {
            synchronized (lock) {
                if (!cached) {
                    value = supplier.get();
                    cached = true;
                }
            }
        }
        return value;
    }
}