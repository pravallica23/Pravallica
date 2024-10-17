import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact {
    String name;
    String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + ": " + phone;
    }
}

public class ContactBook {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter a command (add/view/remove/exit): ");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "add":
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter phone number: ");
                    String phone = scanner.nextLine();
                    contacts.add(new Contact(name, phone));
                    break;
                case "view":
                    System.out.println("Your contacts:");
                    for (Contact contact : contacts) {
                        System.out.println(contact);
                    }
                    break;
                case "remove":
                    System.out.println("Enter contact index to remove: ");
                    int index = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer
                    if (index >= 0 && index < contacts.size()) {
                        contacts.remove(index);
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;
                case "exit":
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
