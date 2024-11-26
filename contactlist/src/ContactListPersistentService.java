import java.io.*;
import java.util.List;

public class ContactListPersistentService {
    private final String filePath;

    public ContactListPersistentService(String filePath) {
        this.filePath = filePath;
    }

    public void saveContacts(List<Person> contacts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Person person : contacts) {
                writer.write(person.getName() + "|" +
                             person.getEmail() + "|" +
                             person.getPhone() + "|" +
                             person.getAddress() + "|" +
                             person.getAge());
                writer.newLine();
            }
        }
    }

    public void loadContacts(ContactList contactList) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String name = parts[0];
                    String email = parts[1];
                    String phone = parts[2];
                    String address = parts[3];
                    int age = Integer.parseInt(parts[4]);
                    contactList.addContact(new Person(name, email, phone, address, age));
                }
            }
        }
    }
}
