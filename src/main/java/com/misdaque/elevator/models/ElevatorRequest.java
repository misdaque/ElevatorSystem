package com.misdaque.elevator.models;

import lombok.Data;

@Data
public class ElevatorRequest {
    private final String personName;
    private final int timeOfRequest;
    private final int fromFloor;
    private final int toFloor;

}
