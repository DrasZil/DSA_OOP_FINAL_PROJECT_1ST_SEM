public class CommunityPantry extends Recipient{
    
    public CommunityPantry(int id, String userName, String contact) {
        super(id, userName, contact);
    }
    
    @Override
    public String getRole(){
        return "Community Pantry Recipient";
    }
}
