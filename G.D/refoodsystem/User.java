public abstract class User {
    private int id;
    private String userName;
    private String contact;

    public User(int id, String userName, String contact) {
        this.id = id;
        updateDetails(userName, contact);
    }

    private static String required(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Organization name and contact are required.");
        }
        return value.trim();
    }

    // Validate both inputs before committing an edit to a registered organization.
    public final void updateDetails(String name, String contact) {
        String checkedName = required(name);
        this.contact = required(contact);
        this.userName = checkedName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = required(userName);
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = required(contact);
    }

    public String getRole(){
        return "Not Specified";
    }

    public void displayInfo(){
        System.out.println("User Name: " + getUserName() );
        System.out.println("UserID: " + getId() );
        System.out.println("User Contact: " + getContact());
        System.out.println("Role: " + getRole());
    }
}
