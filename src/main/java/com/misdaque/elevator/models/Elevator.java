package com.misdaque.elevator.models;

import lombok.Data;

@Data
public class Elevator {

    final String name;
    ElevatorRequest elevatorRequest;
    State state = State.IDLE;

    private int getPosition(int time) {

        if (elevatorRequest == null)
            return 0;

        int tdiff = time - this.elevatorRequest.getTimeOfRequest();

        int floorDiff = this.elevatorRequest.getToFloor() - this.elevatorRequest.getFromFloor();

        int floorNext = Math.min(tdiff, Math.abs(floorDiff));
        int direction = floorDiff >= 0 ? 1 : -1;
        return this.elevatorRequest.getFromFloor() + direction * floorNext;
    }

    public int timeToReachRequester(ElevatorRequest elevatorRequest) {

        if(this.elevatorRequest == null){
            return elevatorRequest.getFromFloor();
        }

        return Math.abs(this.elevatorRequest.getToFloor() - getPosition(elevatorRequest.getTimeOfRequest()))
                    + Math.abs(elevatorRequest.getFromFloor() - this.elevatorRequest.getToFloor());
    }
}
