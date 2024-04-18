package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.requests;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IRequest;

public record SimulationCreateRequest(Integer clients, Integer queues, Integer simulationTime, Integer arrivalTimeMin,
                                      Integer arrivalTimeMax, Integer serviceTimeMin,
                                      Integer serviceTimeMax) implements IRequest {
}
