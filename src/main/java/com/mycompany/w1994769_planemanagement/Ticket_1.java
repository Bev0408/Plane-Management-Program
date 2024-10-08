/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bev
 */
public class Ticket {
    private char row;
    private int seat;
    private int price;
    private com.mycompany.w1994769_planemanagement.Person person;

    // Constructor
    public Ticket(char row, int seat, int price, com.mycompany.w1994769_planemanagement.Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getters and setters
    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public com.mycompany.w1994769_planemanagement.Person getPerson() {
        return person;
    }

    public void setPerson(com.mycompany.w1994769_planemanagement.Person person) {
        this.person = person;
    }

    // Method to print ticket information
    public void printTicketInfo() {
        System.out.println("Ticket Information:");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        System.out.println("Passenger Information:");
        if (person != null) { // Check if person is not null
            person.printInfo(); // Assuming Person class has a printInfo() method
        } else {
            System.out.println("No passenger information available.");
        }
    }
}

