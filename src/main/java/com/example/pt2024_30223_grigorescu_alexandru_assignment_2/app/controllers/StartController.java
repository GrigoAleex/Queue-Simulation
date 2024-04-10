package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.Window;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.exceptions.ViewNotFoundException;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.exceptions.WindowUninitializedException;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IController;

import java.io.IOException;

public class StartController implements IController {
    public static void index() {
        try {
            Window.setView("start");
        } catch (WindowUninitializedException e) {
            System.err.println(e.getMessage());
            System.exit(11);
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(121);
        }
    }

}
