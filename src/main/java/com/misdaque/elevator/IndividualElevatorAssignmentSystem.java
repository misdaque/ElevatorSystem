package com.misdaque.elevator;

import com.misdaque.elevator.models.Elevator;
import com.misdaque.elevator.models.ElevatorRequest;
import com.misdaque.elevator.models.State;

public class IndividualElevatorAssignmentSystem extends ElevatorSystem {

    public IndividualElevatorAssignmentSystem(int elevatorCount) {
        super(elevatorCount);
    }

    @Override
    public Elevator assignElevator(ElevatorRequest elevatorRequest) {
        Elevator selectedElevator = super.elevators.get(0);
        int minTimeTakenToReachRequester = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int timeTakenToReachRequester = elevator.timeToReachRequester(elevatorRequest);

            if (timeTakenToReachRequester < minTimeTakenToReachRequester
                    && elevator.getState() != State.NOT_WORKING) {
                selectedElevator = elevator;
                minTimeTakenToReachRequester = timeTakenToReachRequester;
            }
        }

        selectedElevator.setElevatorRequest(elevatorRequest);
        selectedElevator.setState(State.RUNNING);

        return selectedElevator;
    }
}
