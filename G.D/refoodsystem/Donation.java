/** A donor offers one food batch. Its quantity is the available stock. */
public class Donation {
    private int id;
    private Donor donor;
    private FoodItem foodItem;

    public Donation(Donor donor, FoodItem foodItem, int id) {
        this.donor = donor;
        this.foodItem = foodItem;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAvailableQuantity() {
        return foodItem.getQuantity();
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public void reduceQuantity(double amount){
        if (amount > 0 && amount <= foodItem.getQuantity()) {
            foodItem.setQuantity(foodItem.getQuantity() - amount);
        } else {
            System.out.println("Invalid quantity!");
        }
    }

    public void displayInfo(){
        System.out.println("=========Donation=========");
        System.out.println("Donation ID: " + getId());
        System.out.println("Donor: " + donor.getUserName());
        System.out.println("Food Name: " + foodItem.getName());
        System.out.println("Food Type: " + foodItem.getFoodType());
        System.out.println("Available Quantity: " + getAvailableQuantity() + " " + foodItem.getUnit());
        System.out.println("Expiration Date: " + foodItem.getExpirationDate());
        System.out.println("Remaining Shelf life: " + foodItem.calculateUrgency());
        System.out.println("==========================");
    }
}
