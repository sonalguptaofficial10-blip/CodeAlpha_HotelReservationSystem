import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean booked;
    String customerName;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.booked = false;
        this.customerName = "";
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        rooms.add(new Room(101, "Standard", 1000));
        rooms.add(new Room(102, "Standard", 1000));
        rooms.add(new Room(201, "Deluxe", 2000));
        rooms.add(new Room(202, "Deluxe", 2000));
        rooms.add(new Room(301, "Suite", 4000));

        int choice;

        do {
            System.out.println("\n========== HOTEL RESERVATION SYSTEM ==========");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: viewRooms(); break;
                case 2: bookRoom(); break;
                case 3: cancelBooking(); break;
                case 4: viewBookings(); break;
                case 5: System.out.println("Thank you for using Hotel Reservation System."); break;
                default: System.out.println("Invalid Choice!");
            }
        } while (choice != 5);

        sc.close();
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms");
        for (Room room : rooms) {
            if (!room.booked) {
                System.out.println("Room No : " + room.roomNumber);
                System.out.println("Category : " + room.category);
                System.out.println("Price : Rs." + room.price);
                System.out.println("--------------------------");
            }
        }
    }

    static void bookRoom() {
        System.out.print("\nEnter Room Number to Book: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room room : rooms) {
            if (room.roomNumber == roomNo) {
                if (room.booked) {
                    System.out.println("Room already booked.");
                    return;
                }

                System.out.print("Enter Customer Name: ");
                room.customerName = sc.nextLine();

                System.out.println("Room Price : Rs." + room.price);
                System.out.print("Enter Payment Amount: Rs.");
                double payment = sc.nextDouble();

                if (payment >= room.price) {
                    room.booked = true;
                    System.out.println("Payment Successful!");
                    System.out.println("Room Booked Successfully.");
                    if (payment > room.price) {
                        System.out.println("Balance : Rs." + (payment - room.price));
                    }
                } else {
                    System.out.println("Insufficient Payment.");
                }
                return;
            }
        }

        System.out.println("Room not found.");
    }

    static void cancelBooking() {
        System.out.print("\nEnter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {
            if (room.roomNumber == roomNo) {
                if (room.booked) {
                    room.booked = false;
                    room.customerName = "";
                    System.out.println("Booking Cancelled Successfully.");
                } else {
                    System.out.println("Room is not booked.");
                }
                return;
            }
        }

        System.out.println("Room not found.");
    }

    static void viewBookings() {
        boolean found = false;
        System.out.println("\nBooked Rooms");

        for (Room room : rooms) {
            if (room.booked) {
                found = true;
                System.out.println("Customer : " + room.customerName);
                System.out.println("Room No  : " + room.roomNumber);
                System.out.println("Category : " + room.category);
                System.out.println("Price    : Rs." + room.price);
                System.out.println("--------------------------");
            }
        }

        if (!found) {
            System.out.println("No Bookings Found.");
        }
    }
}