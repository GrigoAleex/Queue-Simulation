package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models;

public class Client {
    private static Integer lastGivenId = 1;
    private final Integer id;
    private final Integer arrivalTime;
    private Integer serviceTime;
    private final Integer initialServiceTime;
    private CashRegister register = null;
    private Integer waitingTimeUntilService = 0;

    public Client(Integer arrivalTime, Integer serviceTime) {
        this.id = lastGivenId++;
        this.arrivalTime = arrivalTime;
        this.initialServiceTime = serviceTime;
        this.serviceTime = serviceTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setWaitingTimeUntilService(Integer time) {
        waitingTimeUntilService = time;
    }

    public int decreaseServiceTime() {
        return serviceTime -= 1;
    }

    public void setRegister(CashRegister register) {
        this.register = register;
    }

    public CashRegister getRegister() {
        return register;
    }

    public Integer getInitialServiceTime() {
        return initialServiceTime;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + arrivalTime + ", " + initialServiceTime + ")";
    }

    public Integer getWaitingTimeUntilService() {
        return waitingTimeUntilService;
    }

    public static void resetId() {
        lastGivenId = 1;
    }
}
