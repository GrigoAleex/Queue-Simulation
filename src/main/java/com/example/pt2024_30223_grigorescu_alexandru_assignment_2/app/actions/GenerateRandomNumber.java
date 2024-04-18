package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.actions;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesRandomNumber;

public class GenerateRandomNumber implements GeneratesRandomNumber {
    @Override
    public Integer handle(Integer upperBound, Integer lowerBound) {
        return (int) ((Math.random() * (upperBound - lowerBound)) + lowerBound);
    }
}
