public abstract class User {
    private int id;
    private String userName;
    private String contact;

    public User(int id, String userName, String contact) {
        this.id = id;
        this.userName = userName;
        this.contact = contact;
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
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
