public class BakeryDonor extends Donor {
    
    public BakeryDonor(int id, String userName, String contact) {
        super(id, userName, contact);
    }

    @Override
    public String getRole(){
        return "Bakery Donor";
    }
}
