package com.misdaque.elevator.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Elevator {
    final String name;
    private List<ElevatorRequest> elevatorRequests = new ArrayList<>();
    int currentPosition = 0;
    State state = State.IDLE;
    Direction direction = Direction.NEUTRAL;

    public int timeToReachRequester(ElevatorRequest request) {

        if (this.direction == Direction.NEUTRAL) {
            return Math.abs(request.getFromFloor() - this.getCurrentPosition());
        } else if (this.direction == Direction.UP) {
            return upDirectionCalculation(request);
        } else {
            return downDirectionCalculation(request);
        }

    }

    //Passenger has requested to go somewhere and the elevator is going up
    private int upDirectionCalculation(ElevatorRequest request) {
        if (request.getFromFloor() > this.getCurrentPosition())
            return Math.abs(request.getFromFloor() - this.getCurrentPosition()); // if the elevator is going up and the request is made from up
        else
            return Math.abs(request.getFromFloor() - this.getElevatorRequests().get(this.elevatorRequests.size() - 1).getToFloor()); // if the elevator is going up and the request is made from down then the last requests destination will be used to calculate
    }

    private int downDirectionCalculation(ElevatorRequest request) {

        if (request.getFromFloor() < this.getCurrentPosition()) { // Elevator is going down and the request is made from down
            return Math.abs(request.getFromFloor() - this.getCurrentPosition());
        } else { // Elevator is going down and the request is made from up somewhere then the last requests final destination will give me the calculation
            return Math.abs(request.getFromFloor() - this.getElevatorRequests().get(this.getElevatorRequests().size() - 1).getToFloor());
        }
    }

    /*
    * ToDo: Once the request completes call this method to remove requests that has been processed
    * */
    public void requestCleanup(){}
}
