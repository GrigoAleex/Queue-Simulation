package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.exceptions;

public class WindowUninitializedException extends Exception {
    public WindowUninitializedException() {
        super("No window initialized!");
    }
}
