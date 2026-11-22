import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class FoodItem {
    private String name;
    private double quantity;
    private String unit;
    private String expirationDate; // follow mm/dd/yyyy format

    public FoodItem(String name, double quantity, String unit, String expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getFoodType(){
        return "not Specified";
    }

    public String calculateUrgency() {

        String[] dateParts = expirationDate.split("/");

        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        // Convertion
        LocalDate expiry = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();

        long daysLeft = ChronoUnit.DAYS.between(today, expiry);

        if (daysLeft < 0) return "Expired! ";
        if (daysLeft == 0) return "Expires Today";
        if (daysLeft == 1) return "1 day left";
        if (daysLeft <= 3) return "2–3 days left";
        if (daysLeft <= 7) return "4–7 days left";

        return "More than 7 days";
    }

    public void displayInfo() {
        System.out.println("food Type: " + getFoodType());
        System.out.println("Food Name: " + getName());
        System.out.println("Quantity: " + getQuantity());
        System.out.println("Unit: " + getUnit());
        System.out.println("Days Remaining: " + calculateUrgency());
        System.out.println("Expiry Date: " + getExpirationDate());
    }
}
