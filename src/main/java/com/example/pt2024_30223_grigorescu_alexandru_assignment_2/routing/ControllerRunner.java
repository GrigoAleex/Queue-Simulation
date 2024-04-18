package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IControllerRunner;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IRequest;

import java.lang.reflect.Method;

public class ControllerRunner implements IControllerRunner {
    private final String action;
    private final Class<? extends IController> controller;

    public ControllerRunner(Class<? extends IController> controller, String action) {
        this.controller = controller;
        this.action = action;
    }

    public void run(IRequest request) {
        try {
            if (request == null) {
                Method method = controller.getMethod(this.action);
                method.invoke(null);
            } else {
                Method method = controller.getMethod(this.action, request.getClass());
                method.invoke(null, request);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
