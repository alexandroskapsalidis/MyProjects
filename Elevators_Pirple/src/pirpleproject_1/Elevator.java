package pirpleproject_1;

import java.util.Arrays;
import java.util.Scanner;

public class Elevator {

    // The current floor
    protected int floor;
    // The destination floor
    protected int floorDestination;
    protected boolean doorOpened;
    protected boolean emergency = false;

    // Moving UP from current floor, passing the array with 
    // floors and current floor from the subclass
    void up(int[] floorsArray, int floor) {
        openDoor();

        // Creating an array with the available floors to show to the user
        int lastFloor = floorsArray[floorsArray.length - 1];
        int index = lastFloor - floor;
        int[] availableFloors = new int[index];
        // Filling the array with available floor numbers
        for (int i = 0; i < index; i++) {
            availableFloors[i] = ++floor;
        }
        System.out.println("Please select floor:");

        // Showing available floors 
        System.out.println(Arrays.toString(availableFloors));
        System.out.println("");

        // Choosing the floor destination and passing it to the moving() function
        Scanner sc = new Scanner(System.in);
        floorDestination = sc.nextInt();
        System.out.println("");
        moving(floorDestination);
    }

    // Opening the door
    void openDoor() {
        doorOpened = true;
        System.out.println("Door Opened");
        System.out.println("");
    }

    // Closing the door and moving
    void moving(int floorDestination) {
        this.floorDestination = floorDestination;
        doorOpened = false;
        System.out.println("Door Closed");
        System.out.println("Elevator going to floor: " + this.floorDestination);
        System.out.println("");
        // During the ride the passenger can press the emergency stop
        // or continue the ride
        System.out.println("Select:");
        System.out.println("1. STOP");
        System.out.println("2. CONTINUE");
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();
        
        // Selecting again in case of wrong input
        if ((selection != 1) && (selection != 2)) {
            System.out.println("Invalid input!");
            System.out.println("Select:");
            System.out.println("1. STOP");
            System.out.println("2. CONTINUE");
            sc = new Scanner(System.in);
            selection = sc.nextInt();
        }
        // Calling the emergency() stop method
        if (selection == 1) {
            emergencyStop();
        // Reaching the destination    
        } else {
            System.out.println("You are at the floor: " + this.floorDestination);
            System.out.println("");
            System.out.println("Do you want to choose a new destination?");
            // The passenger from here can choose a new destination to go
            // if he wants. 
            System.out.println("1. YES");
            System.out.println("2. NO");
            selection = sc.nextInt();
            if ((selection != 1) && (selection != 2)) {
                System.out.println("Invalid input!");
                System.out.println("Select:");
                System.out.println("1. YES");
                System.out.println("2. NO");
                selection = sc.nextInt();
            }
            if (selection == 1) {
                reset();
            // Otherwise the ride ends
            } else {
                System.out.println("");
                System.out.println("You reached your destination");
                System.out.println("Select \"3\" for Exit");
            }
            System.out.println(" ");
        }
    }

    // Stopping the elevator
    void emergencyStop() {
        emergency = true;
        System.out.println("");
        System.out.println("Elevator stopped");
        System.out.println("");
        System.out.println("Select:");
        // The passenger can press reset to continue
        System.out.println("1. RESET");
        System.out.println("2. STOP");
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();
        if ((selection != 1) && (selection != 2)) {
            System.out.println("Invalid input!");
            System.out.println("Select:");
            System.out.println("1. RESET");
            System.out.println("2. STOP");
            sc = new Scanner(System.in);
            selection = sc.nextInt();
        }
        if (selection == 1) {
            reset();
        } else {
            System.out.println("Elevator stopped");
            System.out.println("Waiting for the experts to fix the problem");
            System.out.println(" ");
        }
    }

    // Overriden method
    void reset() {

    }
}
