package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IRequest;

import java.util.ArrayList;

public class Router {
    private final ArrayList<Route> routes = new ArrayList<>();
    private static final Router instance =  new Router();

    public static void add(Route route) { instance.routes.add(route); }
    public static void go(String routeName) { go(routeName, null); }
    public static void go(String routeName, IRequest request) {
        for (Route route : instance.routes)
            if (route.getName().equals(routeName))
                route.execute(request);
    }
}
