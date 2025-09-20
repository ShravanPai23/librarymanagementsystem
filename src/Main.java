import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        BorrowDAO borrowDAO = new BorrowDAO();

        while (true) {
            System.out.println("\n--- Library Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add User");
            System.out.println("4. View Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    bookDAO.addBook(title, author);
                    break;

                case 2:
                    bookDAO.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter user name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    userDAO.addUser(name, email);
                    break;

                case 4:
                    userDAO.viewUsers();
                    break;

                case 5:
                    System.out.print("Enter user ID: ");
                    int uIdBorrow = sc.nextInt();
                    System.out.print("Enter book ID to borrow: ");
                    int bIdBorrow = sc.nextInt();
                    borrowDAO.borrowBook(uIdBorrow, bIdBorrow);
                    break;

                case 6:
                    System.out.print("Enter user ID: ");
                    int uIdReturn = sc.nextInt();
                    System.out.print("Enter book ID to return: ");
                    int bIdReturn = sc.nextInt();
                    borrowDAO.returnBook(uIdReturn, bIdReturn);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
