public class PerishableFood extends FoodItem {

    public PerishableFood(String name, double quantity, String unit, String expirationDate) {
        super(name, quantity, unit, expirationDate);
    }

    @Override
    public String getFoodType() {
        return "Perishable";
    }
}