/** Identifies a registered bakery when a donor is displayed through the Donor type. */
public class BakeryDonor extends Donor {
    
    public BakeryDonor(int id, String userName, String contact) {
        super(id, userName, contact);
    }

    @Override
    public String getRole(){
        return "Bakery Donor";
    }
}
