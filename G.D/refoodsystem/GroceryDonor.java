public class GroceryDonor extends Donor {
    
    public GroceryDonor(int id, String userName, String contact) {
        super(id, userName, contact);
    }

    @Override
    public String getRole(){
        return "Grocery Donor";
    }
}
