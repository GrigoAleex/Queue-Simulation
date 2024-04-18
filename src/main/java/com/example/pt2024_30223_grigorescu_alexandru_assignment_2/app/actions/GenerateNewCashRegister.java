package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.actions;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.CashRegister;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesNewCashRegisters;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.LoaderManager;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Session;

import java.util.ArrayList;

public class GenerateNewCashRegister implements GeneratesNewCashRegisters {
    @Override
    public ArrayList<CashRegister> handle(Integer count) {
        Session.set("loadingState", "0.0");
        ArrayList<CashRegister> registers = new ArrayList<>();
        LoaderManager loader = AppProvider.get(LoaderManager.class);

        for (int i = 1; i <= count; i++) {
            registers.add(new CashRegister(i));
            loader.incrementLoading();
        }

        return registers;
    }

}
