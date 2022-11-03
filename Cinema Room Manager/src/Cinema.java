import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    public static int ticketsSold;
    public static double currentIncome;
    public static double percentageOfOccupany;
    private static char[][] cinemaHall;

    public static void menu() {
        int command;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            command = scanner.nextInt();

            switch (command) {
                case 1:
                    printCinemaHall();
                    break;
                case 2:
                    takeTicket();
                    break;
                case 3:
                    statMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error! Wrong command.");
                    break;
            }
        } while(command != 0);
    }
    public static void statMenu() {
        int command;
        do {
            System.out.printf("Tickets Sold: %d\n", ticketsSold);
            System.out.printf("Current income: %.2f$\n", currentIncome);
            System.out.printf("Percent of seats sold: %.2f%%\n", percentageOfOccupany);
            System.out.println("0. back to menu");
            command = scanner.nextInt();

            if (command == 0) {
                menu();
            } else {
                System.out.println("Error! Wrong command.");
                statMenu();
            }
        } while(command != 0);
    }
    public static void takeTicket() {
        System.out.println("Enter a row number");
        int seatRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row");
        int seatNumber = scanner.nextInt();

        int totalNumberOfSeats = cinemaHall.length * cinemaHall[0].length;

        int priceTicket;

        if(totalNumberOfSeats <= 60) {
            priceTicket = 10;
        } else {
            int frontHalfOfRows = cinemaHall.length / 2;
            if(seatRow <= frontHalfOfRows) {
                priceTicket = 10;
            } else {
                priceTicket = 8;
            }
        }
        if(cinemaHall[seatRow - 1][seatNumber - 1] == 'B') {
            System.out.println("This seat is not available. Please take another seat.");
            takeTicket();
        } else {
            System.out.println("Ticket price: " + priceTicket + "$");
            cinemaHall[seatRow - 1][seatNumber - 1] = 'B';
            getTicketsSold();
            getCurrentIncome(priceTicket);
        }
        getPercentageOfOccupany(totalNumberOfSeats);
    }
    public static void createCinema() {

        System.out.print("Enter the number of rows: ");
        int numberOfRows = scanner.nextInt();
        System.out.print("Enter number of seats in each row: ");
        int numberOfSeats = scanner.nextInt();

        cinemaHall = new char[numberOfRows][numberOfSeats];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                cinemaHall[i][j] = 'S';
            }
        }
        System.out.println();
        menu();
    }
    public static void printCinemaHall() {
        System.out.println("Cinema: ");
        System.out.print(" ");
        for (int i = 1; i <= cinemaHall[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = cinemaHall.length - 1; i > -1; i--) {
            System.out.print(i + 1);
            for (int j = 0; j < cinemaHall[0].length; j++) {
                System.out.print(" " + cinemaHall[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    private static void getTicketsSold() {
        ticketsSold++;
    }
    private static void getCurrentIncome(double price) {
        currentIncome += price;
    }
    private static void getPercentageOfOccupany(int numberOfSeats) {
        percentageOfOccupany = (ticketsSold * numberOfSeats) / 100.0;
    }
}