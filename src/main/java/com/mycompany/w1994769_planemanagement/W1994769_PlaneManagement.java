/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.w1994769_planemanagement;

/**
 *
 * @author bev
 */
import java.util.ArrayList;
import java.util.Scanner;
import com.mycompany.w1994769_planemanagement.Ticket;
import com.mycompany.w1994769_planemanagement.Person;

public class W1994769_PlaneManagement {

    // Define constants for seat prices
    private static final int FRONT_SEAT_PRICE = 200;
    private static final int MIDDLE_SEAT_PRICE = 150;
    private static final int BACK_SEAT_PRICE = 180;

    // Define constants for seat capacities
    private static final int[] ROW_CAPACITY = {14, 12, 12, 14};

    // Define 2D array to represent seats
    private static final int[][] seats = new int[4][];
    private static final ArrayList<Ticket> soldTickets = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSeats();

        System.out.println("Welcome to the Plane Management application");
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    buySeat();
                    break;
                case 2:
                    cancelSeat();
                    break;
                case 3:
                    findFirstAvailable();
                    break;
                case 4:
                    showSeatingPlan();
                    break;
                case 5:
                    printTicketsInfo();
                    break;
                case 6:
                    // Implement search tickets
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 0);
    }

    // Initialize seats array with 0s to indicate all seats are available
    private static void initializeSeats() {
        for (int i = 0; i < ROW_CAPACITY.length; i++) {
            seats[i] = new int[ROW_CAPACITY[i]];
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 0; // 0 indicates available seat
            }
        }
    }

    // Display menu options
    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1) Buy a seat");
        System.out.println("2) Cancel a seat");
        System.out.println("3) Find first available seat");
        System.out.println("4) Show seating plan");
        System.out.println("5) Print ticket information and current sales");
        System.out.println("6) Search tickets");
        System.out.println("0) Quit");
        System.out.print("Enter your choice: ");
    }

    // Method to check if a seat is available
    private static boolean isSeatAvailable(int row, int seatNumber) {
        if (row < 0 || row >= seats.length || seatNumber < 1 || seatNumber > ROW_CAPACITY[row]) {
            return false; // Invalid row or seat number
        }
        return seats[row][seatNumber - 1] == 0; // 0 indicates available seat
    }

    // Method to book a seat
    private static void buySeat() {
        System.out.print("Enter row letter (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        int row = rowLetter - 'A';
        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();

        if (row < 0 || row >= ROW_CAPACITY.length || seatNumber < 1 || seatNumber > ROW_CAPACITY[row]) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        if (isSeatAvailable(row, seatNumber)) {
            int price = getPrice(row);
            System.out.print("Enter passenger name: ");
            String name = scanner.next();
            System.out.print("Enter passenger surname: ");
            String surname = scanner.next();
            System.out.print("Enter passenger email: ");
            String email = scanner.next();

            Person person = new Person(name, surname, email);
            Ticket ticket = new Ticket(rowLetter, seatNumber, price, person);
            seats[row][seatNumber - 1] = 1; // Book the seat
            soldTickets.add(ticket); // Add ticket to sold tickets
            System.out.println("Seat " + rowLetter + seatNumber + " booked successfully.");
        } else {
            System.out.println("Seat " + rowLetter + seatNumber + " is not available.");
        }
    }

    // Method to cancel a booked seat
    private static void cancelSeat() {
        System.out.print("Enter row letter (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        int row = rowLetter - 'A';
        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();

        if (row < 0 || row >= ROW_CAPACITY.length || seatNumber < 1 || seatNumber > ROW_CAPACITY[row]) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        if (!isSeatAvailable(row, seatNumber)) {
            seats[row][seatNumber - 1] = 0; // Make the seat available
            Ticket canceledTicket = findTicket(rowLetter, seatNumber);
            if (canceledTicket != null) {
                soldTickets.remove(canceledTicket); // Remove ticket from sold tickets
                System.out.println("Seat " + rowLetter + seatNumber + " cancelled successfully.");
            }
        } else {
            System.out.println("Seat " + rowLetter + seatNumber + " is already available.");
        }
    }

    // Method to find a ticket in sold tickets
    private static Ticket findTicket(char row, int seatNumber) {
        for (Ticket ticket : soldTickets) {
            if (ticket.getRow() == row && ticket.getSeat() == seatNumber) {
                return ticket;
            }
        }
        return null; // Ticket not found
    }

    // Method to find the first available seat
    private static void findFirstAvailable() {
        for (int row = 0; row < ROW_CAPACITY.length; row++) {
            for (int seatNumber = 1; seatNumber <= ROW_CAPACITY[row]; seatNumber++) {
                if (isSeatAvailable(row, seatNumber)) {
                    char rowLetter = (char) ('A' + row);
                    System.out.println("First available seat: " + rowLetter + seatNumber);
                    return;
                }
            }
        }
        System.out.println("No available seats.");
    }
    
    private static void showSeatingPlan() {
        System.out.println("Seating Plan:");
        for (int row = 0; row < ROW_CAPACITY.length; row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int seatNumber = 1; seatNumber <= ROW_CAPACITY[row]; seatNumber++) {
                if (isSeatAvailable(row, seatNumber)) {
                    System.out.print("O "); // Available
                } else {
                    System.out.print("X "); // Booked
                }
            }
            System.out.println(); // Move to the next row
        }
    }

    private static void searchTicket() {
        if (soldTickets.isEmpty()) {
            System.out.println("No tickets have been sold yet.");
            return;
        }
        
        System.out.print("Enter row letter (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        int row = rowLetter - 'A';
        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();

        if (row < 0 || row >= ROW_CAPACITY.length || seatNumber < 1 || seatNumber > ROW_CAPACITY[row]) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        Ticket searchedTicket = findTicket(rowLetter, seatNumber);
        if (searchedTicket != null) {
            System.out.println("Ticket Information:");
            searchedTicket.printTicketInfo();
        } else {
            System.out.println("This seat is available.");
        }
    }
}