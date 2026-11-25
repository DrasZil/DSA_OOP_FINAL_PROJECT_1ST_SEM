/** Represents a community pantry that can be selected as a food request recipient. */
public class CommunityPantry extends Recipient{
    
    public CommunityPantry(int id, String userName, String contact) {
        super(id, userName, contact);
    }
    
    @Override
    public String getRole(){
        return "Community Pantry Recipient";
    }
}
