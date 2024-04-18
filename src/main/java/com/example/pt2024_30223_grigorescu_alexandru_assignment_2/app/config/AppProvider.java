package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config;

import java.util.HashMap;
import java.util.Map;

public class AppProvider {
    private final Map<Class<?>, Object> bindings = new HashMap<>();
    private static final AppProvider instance = new AppProvider();

    public static <T> void set(Class<T> clazz, T target) {
        instance.bindings.put(clazz, target);
    }

    public static <T> T get(Class<T> clazz) {
        return clazz.cast(instance.bindings.get(clazz));
    }
}
