import java.util.ArrayList;
import java.util.List;

/*
 * User class
 */
public class User {

    private String name;
    private String email;
    private int phoneNumber;
    private int studentNumber;
    private List<User> contacts = new ArrayList<User>();
    private Role role;
    private Campus preferredCampus;
    private int accountCode;

    // Base constructor
    public User(String name, String email, int phoneNumber, int studentNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.studentNumber = studentNumber;
    }

    public User(String name, String email, int phoneNumber, int studentNumber, Role role, Campus preferredCampus,
                int accountCode) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.studentNumber = studentNumber;
        this.role = role;
        this.preferredCampus = preferredCampus;
        this.accountCode = accountCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPreferredCampus(Campus campus) {
        this.preferredCampus = campus;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public List<User> getContacts() {
        return contacts;
    }

    public Role getRole()
    {
        return role;
    }

    public Campus getPreferredCampus() {
        return preferredCampus;
    }

    public void addContact(User contact) {
        contacts.add(contact);
    }

    public void removeContact(User contact) {
        contacts.remove(contact);
    }
}