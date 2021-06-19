package pirpleproject_1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ElevatorA extends Elevator {

    // Array containing the floors elevatorA can go to
    private int[] floors = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    // The Constructor assigns the current floor and calls the
    // callElevator method to begin the programm.
    ElevatorA(int currentFloor) {
        floor = currentFloor;
        System.out.println("");
        System.out.println("Elevator A available floors: ");
        System.out.println(Arrays.toString(floors));
        System.out.println("");
        callElevator();
    }

    // calling the elevator
    void callElevator() {
        // If it isn't the first or last floor the elevator can go both up
        // or down
        if (floor != floors[0] && floor != floors[10]) {
            // Asking the user if he goes up or down 
            System.out.println("Please select \"UP\" or \"DOWN?\"");
            Scanner sc = new Scanner(System.in);
            String destination = sc.next();
            destination = destination.toUpperCase();

            // In case of invalid input prompting the user to input again
            if (!(destination.equals("UP")) && !(destination.equals("DOWN"))) {
                System.out.println("Invalid Input!");
                System.out.println("Please select \"UP\" or \"DOWN?\"");
                destination = sc.next();
                destination = destination.toUpperCase();
            }
            if (destination.equals("UP")) {
                super.up(this.floors, this.floor);
            } else {
                down();
            }

            // If the current floor is -1 there is only an "UP" button.
        } else if (floor == floors[0]) {
            System.out.println("You can only go UP");
            super.up(this.floors, this.floor);
            // Else if it is the last floor there is only a "DOWN" button.
        } else {
            System.out.println("You can only go DOWN");
            down();
        }
    }
    
    // Moving DOWN from current floor
    void down() {
        openDoor();

        // Creating an array with the available floors
        int index = floor + 1;
        int[] availableFloors = new int[index];
        // Filling the array through a loop
        for (int i = index - 1; i >= 0; i--) {
            availableFloors[i] = --floor;
        }
        System.out.println("Please select floor:");

        // Showing available floors 
        System.out.println(Arrays.toString(availableFloors));
        System.out.println("");

        // Choosing the floor destination
        Scanner sc = new Scanner(System.in);
        floorDestination = sc.nextInt();
        moving(floorDestination);
    }
    
    // Returns elevator to its normal activity
    @Override
    void reset() {
        emergency = false;
        System.out.println("");
        System.out.println("Enter the current floor");
        Scanner input = new Scanner(System.in);
        int carrentFloor = input.nextInt();
        this.floor = carrentFloor;
        callElevator();
    }
}
