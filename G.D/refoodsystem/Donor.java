/** Common type for organizations that can own donation records. */
public abstract class Donor extends User {

    public Donor(int id, String userName, String contact) {
        super(id, userName, contact);
    }
}
