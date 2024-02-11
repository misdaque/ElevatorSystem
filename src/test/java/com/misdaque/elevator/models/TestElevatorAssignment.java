package com.misdaque.elevator.models;


import com.misdaque.elevator.IndividualElevatorAssignmentSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestElevatorAssignment {
    @ParameterizedTest
    @ValueSource(strings = {"/tc1.txt", "/tc2.txt", "/tc3.txt"})
    public void testElevatorAssignment(String file) throws IOException {

        String fileName = this.getClass().getResource(file).getFile();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        int floors = Integer.parseInt(reader.readLine());
        int elevators = Integer.parseInt(reader.readLine());
        int people = Integer.parseInt(reader.readLine());

        List<ElevatorRequest> elevatorRequests = new ArrayList<>();

        while (people > 0) {

            String[] input = reader.readLine().trim().split(",");

            ElevatorRequest elevatorRequest = new ElevatorRequest(input[0].trim(), Integer.parseInt(input[1].trim()), Integer.parseInt(input[2].trim()), Integer.parseInt(input[3].trim()));
            elevatorRequests.add(elevatorRequest);
            people--;
        }

        Building building = new Building(floors, new IndividualElevatorAssignmentSystem(elevators));
        building.getElevatorSystem().assignElevator(elevatorRequests);

    }

    @AfterEach
    public void insertDottedLines() {

        System.out.println("------------------------------------------------------------");

    }
}
