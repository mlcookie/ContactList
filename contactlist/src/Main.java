import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactList contactList = new ContactList();
        ContactListPersistentService persistence = new ContactListPersistentService("contacts.txt");

        // Load contacts from file
        try {
            persistence.loadContacts(contactList);
            System.out.println("Contacts loaded successfully.");
        } catch (IOException e) {
            System.out.println("No existing contacts found.");
        }

        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Contact");
            System.out.println("2. List Contacts");
            System.out.println("3. Search Contacts");
            System.out.println("4. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    contactList.addContact(new Person(name, email, phone, address, age));
                    System.out.println("Contact added!");
                    break;

                case 2:
                    System.out.println("\nContacts:");
                    for (Person person : contactList.getAllContacts()) {
                        System.out.println(person);
                    }
                    break;

                case 3:
                    System.out.print("Enter search query: ");
                    String query = scanner.nextLine();
                    System.out.println("\nSearch Results:");
                    for (Person person : contactList.searchContacts(query)) {
                        System.out.println(person);
                    }
                    break;

                case 4:
                    try {
                        persistence.saveContacts(contactList.getAllContacts());
                        System.out.println("Contacts saved. Exiting...");
                    } catch (IOException e) {
                        System.out.println("Failed to save contacts: " + e.getMessage());
                    }
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
