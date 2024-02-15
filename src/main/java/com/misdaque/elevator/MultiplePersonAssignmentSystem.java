package com.misdaque.elevator;

import com.misdaque.elevator.models.Direction;
import com.misdaque.elevator.models.Elevator;
import com.misdaque.elevator.models.ElevatorRequest;
import com.misdaque.elevator.models.State;

public class MultiplePersonAssignmentSystem extends ElevatorSystem {

    public MultiplePersonAssignmentSystem(int elevatorCount) {
        super(elevatorCount);
    }

    @Override
    protected Elevator assignElevator(ElevatorRequest elevatorRequest) {
        updateElevators(elevatorRequest);  // O(max(elevators, requests))

        Direction directionToGo = elevatorRequest.getToFloor() > elevatorRequest.getFromFloor() ? Direction.UP : Direction.DOWN;

        int timeToReachRequester = Integer.MAX_VALUE;
        Elevator selectedElevator = elevators.get(0);

        //O(elevators)
        for(Elevator elevator : elevators){

            if(elevator.getState() == State.NOT_WORKING)
                continue;

            int tDiff = elevator.timeToReachRequester(elevatorRequest);

            if(tDiff < timeToReachRequester){
                selectedElevator = elevator;
                timeToReachRequester = tDiff;
            }
        }

        selectedElevator.setDirection(directionToGo); // Todo: Set the direction at the instance the request is being made for all the elevators
        selectedElevator.setState(State.MOVING);
        selectedElevator.getElevatorRequests().add(elevatorRequest);
        return selectedElevator;
    }

    private void updateElevators(ElevatorRequest currentRequest) {

        for (Elevator elevator : elevators) {
            updateCurrentPositionOfElevatorAfterEveryRequest(elevator, currentRequest);
        }
    }

    private void updateCurrentPositionOfElevatorAfterEveryRequest(Elevator elevator, ElevatorRequest currentRequest) {

        int floor = elevator.getCurrentPosition();

        if (elevator.getDirection() == Direction.UP) {
            for (ElevatorRequest oldRequest : elevator.getElevatorRequests()) {

                floor = Math.abs(oldRequest.getTimeOfRequest() - currentRequest.getTimeOfRequest()) + floor;

                if (floor >= oldRequest.getToFloor()) {
                    floor = oldRequest.getToFloor();

                }
            }
        } else if (elevator.getDirection() == Direction.DOWN) {
            for (ElevatorRequest oldRequest : elevator.getElevatorRequests()) {

                floor = floor - Math.abs(currentRequest.getTimeOfRequest() - oldRequest.getTimeOfRequest());

                if (floor <= oldRequest.getToFloor()) {
                    floor = oldRequest.getToFloor();
                }
            }
        }

        if (elevator.getElevatorRequests().size() == 0) {
            elevator.setState(State.IDLE);
            elevator.setDirection(Direction.NEUTRAL);
        }

        elevator.setCurrentPosition(floor);
    }
}
