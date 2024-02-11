package com.misdaque.elevator;

import com.misdaque.elevator.models.Elevator;
import com.misdaque.elevator.models.ElevatorRequest;

import java.util.ArrayList;
import java.util.List;


public abstract class ElevatorSystem {

    final protected List<Elevator> elevators;

    public ElevatorSystem(int elevatorCount){

        elevators = new ArrayList<>();

        for(int i = 1; i <= elevatorCount; i++){
            elevators.add(new Elevator("Lift" + i));
        }
    }

    protected abstract Elevator assignElevator(ElevatorRequest elevatorRequest);

    public void assignElevator(List<ElevatorRequest> elevatorRequests){

        for(ElevatorRequest elevatorRequest : elevatorRequests) {
            Elevator elevator = assignElevator(elevatorRequest);

            System.out.println("Drop " + elevatorRequest.getPersonName() + " from floor " + elevatorRequest.getFromFloor() + " to " + elevatorRequest.getToFloor() + " floor using " + elevator.getName());
        }

    }
}
