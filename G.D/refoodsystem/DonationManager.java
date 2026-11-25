import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DonationManager {
    private final List<Donor> donors = new ArrayList<>();
    private int nextDonorId = 1;
    private final LinkedList<Donation> donations = new LinkedList<>();
    private int nextDonationId = 1;

    public Donor registerDonor(int type, String name, String contact) {
        Donor donor = switch (type) {
            case 1 -> new BakeryDonor(nextDonorId, name, contact);
            case 2 -> new GroceryDonor(nextDonorId, name, contact);
            case 3 -> new RestaurantDonor(nextDonorId, name, contact);
            default -> throw new IllegalArgumentException("Unknown organization type.");
        };
        donors.add(donor);
        nextDonorId++;
        return donor;
    }

    public Donor findDonor(int id) {
        for (Donor donor : donors) {
            if (donor.getId() == id) {
                return donor;
            }
        }
        throw new IllegalArgumentException("Donor ID " + id + " was not found.");
    }

    public void displayDonors() {
        if (donors.isEmpty()) {
            System.out.println("No donors registered.");
        }
        for (Donor donor : donors) {
            donor.displayInfo();
        }
    }

    public void updateDonor(int id, String name, String contact) {
        findDonor(id).updateDetails(name, contact);
    }

    public void deleteDonor(int id) {
        Donor record = findDonor(id);
        // A donation must keep its registered donor until the donation is removed.
        for (Donation donation : donations) {
            if (donation.getDonor() == record) {
                throw new IllegalArgumentException("Cannot delete donor with existing donations.");
            }
        }
        donors.remove(record);
    }

    private void validateStock(FoodItem food) {
        if (food == null || food.getQuantity() <= 0 || food.getExpiryDate().isBefore(java.time.LocalDate.now())) {
            throw new IllegalArgumentException("Enter a positive quantity and an expiry of today or later.");
        }
    }

    public Donation createDonation(int donorId, FoodItem food) {
        validateStock(food);
        Donation donation = new Donation(findDonor(donorId), food, nextDonationId);
        donations.add(donation);
        nextDonationId++;
        return donation;
    }

    public Donation findDonation(int id) {
        for (Donation donation : donations) {
            if (donation.getId() == id) {
                return donation;
            }
        }
        throw new IllegalArgumentException("Donation ID " + id + " was not found.");
    }

    public void displayDonations() {
        if (donations.isEmpty()) {
            System.out.println("No donations recorded.");
        }
        for (Donation donation : donations) {
            donation.displayInfo();
        }
    }

    public void updateDonation(int id, int donorId, FoodItem food) {
        // Check every replacement value before changing either link.
        Donation donation = findDonation(id);
        Donor donor = findDonor(donorId);
        validateStock(food);
        donation.setDonor(donor);
        donation.setFoodItem(food);
    }

    public void deleteDonation(int id) {
        donations.remove(findDonation(id));
    }
}
