package pirpleproject_1;

import java.util.Scanner;

public class PirpleProject_1 {

    public static void main(String[] args) {
        // Selecting current floor
        System.out.println("Enter the current floor");
        Scanner input = new Scanner(System.in);
        int floorNumber = input.nextInt();

        // I use this line as a consumer because of the problem when you 
        // parse an int and after a String with the Scanner class
        input.nextLine();

        char elevator = '0';
        // Choosing the Elevator A or B and handling invalid inputs
        while (elevator != '3') {
            System.out.println("Choose elevator: \n "
                    + "1: Elevator A \n 2: Elevator B \n 3: Exit");

            // Parsing the input as a String first to check its length
            String el = input.next();
            if (el.length() != 1) {
                System.out.println("Invalid input!");
                continue;
            }

            elevator = el.charAt(0);
            switch (elevator) {
                case '1':
                    if (floorNumber == 10) {
                        System.out.println("This elevator does not go to that floor!");
                        System.out.println("Please choose Elevator B");
                        System.out.println("");
                        break;
                    }
                    ElevatorA a = new ElevatorA(floorNumber);
                    break;
                case '2':
                    if (floorNumber == -1) {
                        System.out.println("This elevator does not go to that floor!");
                        System.out.println("Please choose Elevator A");
                        System.out.println("");
                        break;
                    }
                    ElevatorB b = new ElevatorB(floorNumber);
                    break;
                case '3':
                    System.out.println("Exit!");
                    break;
                default:
                    System.out.println("Invalid input!");
            }

        } // end of while

        input.close();
    }

}
