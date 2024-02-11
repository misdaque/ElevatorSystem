package com.misdaque.elevator.models;

import com.misdaque.elevator.ElevatorSystem;
import lombok.Data;

@Data
public class Building {

    final int floorCount;
    final ElevatorSystem elevatorSystem;

}
