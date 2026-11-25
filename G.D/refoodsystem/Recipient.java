/** Common type for organizations eligible to submit food requests. */
public abstract class Recipient extends User {

    public Recipient(int id, String userName, String contact) {
        super(id, userName, contact);
    }
}
