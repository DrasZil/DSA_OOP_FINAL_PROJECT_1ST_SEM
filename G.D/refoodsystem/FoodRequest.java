import java.time.LocalDate;

public class FoodRequest {
    private final int id;
    private final Recipient recipient;
    private final String foodName;
    private final double quantity;
    private final String unit;
    private final LocalDate requestDate;
    private double remainingQuantity;

    public FoodRequest(int id, Recipient recipient, String foodName, double quantity, String unit, LocalDate requestDate) {
        if (id <= 0){
            throw new IllegalArgumentException("ID must be a positive number.");
        }

        if (recipient == null) {
            throw new IllegalArgumentException("Select a recipient organization.");
        }

        if (foodName == null || foodName.trim().isEmpty()) {
            throw new IllegalArgumentException("Food name cannot be empty.");
        }

        if (unit == null || unit.trim().isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be empty.");
        }

        if (!Double.isFinite(quantity) || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive number.");
        }
        if (requestDate == null) {
            throw new IllegalArgumentException("Request date is required.");
        }
        // Preserve the original demand while allocations reduce the outstanding balance.
        this.id = id;
        this.recipient = recipient;
        this.foodName = foodName.trim();
        this.quantity = quantity;
        this.unit = unit.trim();
        this.requestDate = requestDate;
        this.remainingQuantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getQuantity() {
        return quantity;
    }

    /** Compatibility with the original prototype getter. */
    @Deprecated
    public double getQuantitiy() {
        return getQuantity();
    }

    public String getUnit() {
        return unit;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public double getRemainingQuantity() {
        return remainingQuantity;
    }

    public void reduceRemaining(double amount){
        if (!Double.isFinite(amount) || amount <= 0 || amount > remainingQuantity) {
            throw new IllegalArgumentException(
                "Amount must be a positive number and less than or equal to the remaining quantity."
            );
        }
        remainingQuantity = java.math.BigDecimal.valueOf(remainingQuantity)
                .subtract(java.math.BigDecimal.valueOf(amount)).doubleValue();
    }

    public void displayInfo() {
        System.out.println("Food Request ID: " + id);
        System.out.println("Recipient: " + recipient.getUserName());
        System.out.println("Food Name: " + foodName);
        System.out.println("Quantity: " + quantity + " " + unit);
        System.out.println("Request Date: " + requestDate);
        System.out.println("Remaining Quantity: " + remainingQuantity + " " + unit);
    }
}
