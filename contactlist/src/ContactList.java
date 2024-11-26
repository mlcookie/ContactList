import java.util.ArrayList;
import java.util.List;

public class ContactList {
    private List<Person> contacts;

    public ContactList() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Person person) {
        contacts.add(person);
    }

    public List<Person> getAllContacts() {
        return contacts;
    }

    public List<Person> searchContacts(String query) {
        List<Person> result = new ArrayList<>();
        for (Person person : contacts) {
            if (person.getName().toLowerCase().contains(query.toLowerCase()) ||
                person.getEmail().toLowerCase().contains(query.toLowerCase()) ||
                person.getPhone().contains(query)) {
                result.add(person);
            }
        }
        return result;
    }
}
