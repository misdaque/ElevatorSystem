import com.misdaque.elevator.IndividualElevatorAssignmentSystem;
import com.misdaque.elevator.MultiplePersonAssignmentSystem;
import com.misdaque.elevator.models.Building;
import com.misdaque.elevator.models.ElevatorRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of Floors: ");
        int floors = sc.nextInt();

        System.out.println("Enter the number of Elevators: ");
        int elevatorNumber = sc.nextInt();

        System.out.println("Enter the number of People: ");

        int people = sc.nextInt();

        int i = 1;

        List<ElevatorRequest> elevatorRequests = new ArrayList<>();

        while (people > 0) {

            System.out.println("Enter person_name, arriving_time, from_floor, to_floor " + i++ + ") ");
            String[] input = sc.next().trim().split(",");

            ElevatorRequest elevatorRequest = new ElevatorRequest(input[0].trim(), Integer.parseInt(input[1].trim()), Integer.parseInt(input[2].trim()), Integer.parseInt(input[3].trim()));
            elevatorRequests.add(elevatorRequest);
            people--;
        }

        Building building = new Building(floors, new MultiplePersonAssignmentSystem(elevatorNumber));
        building.getElevatorSystem().assignElevator(elevatorRequests);
    }
}
