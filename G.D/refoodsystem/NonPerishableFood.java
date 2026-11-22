public class NonPerishableFood extends FoodItem {

    public NonPerishableFood(String name, double quantity, String unit, String expirationDate) {
        super(name, quantity, unit, expirationDate);
    }

    @Override
    public String getFoodType() {
        return "Non Perishable";
    }
}