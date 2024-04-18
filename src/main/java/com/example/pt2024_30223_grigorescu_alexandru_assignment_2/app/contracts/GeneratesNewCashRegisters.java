package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.CashRegister;

import java.util.ArrayList;

public interface GeneratesNewCashRegisters {
    ArrayList<CashRegister> handle(Integer count);
}
