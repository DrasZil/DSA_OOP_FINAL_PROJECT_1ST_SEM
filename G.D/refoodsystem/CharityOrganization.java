public class CharityOrganization extends Recipient {

    public CharityOrganization(int id, String userName, String contact) {
        super(id, userName, contact);
    }
    
    @Override
    public String getRole(){
        return "Charity Organization Recipient";
    }
}
