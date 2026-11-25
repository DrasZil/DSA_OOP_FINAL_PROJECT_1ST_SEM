/** Labels restaurant donations while reusing the shared donor registration fields. */
public class RestaurantDonor extends Donor {

    public RestaurantDonor(int id, String userName, String contact) {
        super(id, userName, contact);
    }

    @Override
    public String getRole(){
        return "Restaurant Donor";
    }
}
