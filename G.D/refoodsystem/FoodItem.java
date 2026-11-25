import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;

public abstract class FoodItem {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MM/dd/uuuu").withResolverStyle(ResolverStyle.STRICT);
    private String name;
    private double quantity;
    private String unit;
    private LocalDate expirationDate;

    public FoodItem(String name, double quantity, String unit, String expirationDate) {
        setName(name);
        setQuantity(quantity);
        setUnit(unit);
        setExpirationDate(expirationDate);
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Food name is required.");
        }
        this.name = name.trim();
    }

    public double getQuantity() {
        return quantity;
    }

    public final void setQuantity(double quantity) {
        // Zero is valid after the last portion of a donation is allocated.
        if (!Double.isFinite(quantity) || quantity < 0) {
            throw new IllegalArgumentException("Quantity must be finite and nonnegative.");
        }
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public final void setUnit(String unit) {
        if (unit == null || unit.trim().isEmpty()) {
            throw new IllegalArgumentException("Food unit is required.");
        }
        this.unit = unit.trim();
    }

    public String getExpirationDate() {
        return expirationDate.format(DATE_FORMAT);
    }

    public final void setExpirationDate(String expirationDate) {
        try {
            this.expirationDate = LocalDate.parse(expirationDate == null ? "" : expirationDate.trim(), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Enter a real date in MM/dd/yyyy format.");
        }
    }

    public String getFoodType(){
        return "not Specified";
    }

    // Matching and sorting can compare actual dates while the console uses formatted text.
    public LocalDate getExpiryDate() {
        return expirationDate;
    }

    public String calculateUrgency() {

        LocalDate expiry = getExpiryDate();
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
