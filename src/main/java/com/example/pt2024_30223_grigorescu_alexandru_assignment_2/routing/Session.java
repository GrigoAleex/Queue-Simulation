package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing;

import java.util.HashMap;

public class Session {
    private static final Session instance = new Session();
    private final HashMap<String, String> data = new HashMap<>();

    public static void set(String key, String value) { instance.data.put(key, value); }
    public static String get(String key) { return instance.data.get(key); }
    // --Commented out by Inspection (13.04.2024, 19:27):public static Boolean containsKey(String key) { return instance.data.containsKey(key); }
}
