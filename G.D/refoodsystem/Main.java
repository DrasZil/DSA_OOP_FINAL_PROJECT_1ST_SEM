import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Keep the same managers when returning to the menu during a session.
        DonationManager donors = new DonationManager();
        RecipientManager recipients = new RecipientManager();
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== REFOOD ===\n1. Donors\n2. Recipients\n3. Donations\n4. Food requests\n0. Exit");
                int menu = choice(input, "Choice: ", 4);
                if (menu == 0) {
                    return;
                }
                String heading = switch (menu) {
                    case 1 -> "Donors";
                    case 2 -> "Recipients";
                    case 3 -> "Donations";
                    default -> "Food requests";
                };
                while (true) {
                    System.out.println("\n=== " + heading + " ===");
                    System.out.println("1. Create  2. List  3. Find by ID  4. Update  5. Delete  0. Back");
                    int action = choice(input, "Choice: ", 5);
                    if (action == 0) {
                        break;
                    }
                    try {
                        switch (menu) {
                            case 1 -> donorAction(input, donors, action);
                            case 2 -> recipientAction(input, recipients, action);
                            case 3 -> donationAction(input, donors, action);
                            case 4 -> requestAction(input, recipients, action);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (NoSuchElementException endOfInput) {
            System.out.println("Input closed. Exiting REFOOD.");
        }
    }

    private static void donorAction(Scanner input, DonationManager manager, int action) {
        if (action == 1) {
            int type = choice(input, "1. Bakery  2. Grocery  3. Restaurant  0. Cancel: ", 3);
            if (type == 0) {
                return;
            }
            Donor record = manager.registerDonor(type, text(input, "Organization name: "), text(input, "Contact: "));
            System.out.println("Registered donor ID " + record.getId());
        } else if (action == 2) {
            manager.displayDonors();
        } else {
            int id = integer(input, "Donor ID: ");
            manager.findDonor(id).displayInfo();
            if (action == 4) {
                manager.updateDonor(id, text(input, "Updated name: "), text(input, "Updated contact: "));
                System.out.println("Donor updated.");
            } else if (action == 5 && confirmDelete(input)) {
                manager.deleteDonor(id);
                System.out.println("Donor deleted.");
            }
        }
    }

    private static void recipientAction(Scanner input, RecipientManager manager, int action) {
        if (action == 1) {
            int type = choice(input, "1. Community pantry  2. Charity  0. Cancel: ", 2);
            if (type == 0) {
                return;
            }
            Recipient record = manager.registerRecipient(type, text(input, "Organization name: "), text(input, "Contact: "));
            System.out.println("Registered recipient ID " + record.getId());
        } else if (action == 2) {
            manager.displayRecipients();
        } else {
            int id = integer(input, "Recipient ID: ");
            manager.findRecipient(id).displayInfo();
            if (action == 4) {
                manager.updateRecipient(id, text(input, "Updated name: "), text(input, "Updated contact: "));
                System.out.println("Recipient updated.");
            } else if (action == 5 && confirmDelete(input)) {
                manager.deleteRecipient(id);
                System.out.println("Recipient deleted.");
            }
        }
    }

    private static void donationAction(Scanner input, DonationManager manager, int action) {
        if (action == 2) {
            manager.displayDonations();
            return;
        }
        int id = 0;
        if (action != 1) {
            id = integer(input, "Donation ID: ");
            manager.findDonation(id).displayInfo();
            if (action == 3) {
                return;
            }
            if (action == 5) {
                if (confirmDelete(input)) {
                    manager.deleteDonation(id);
                    System.out.println("Donation deleted.");
                }
                return;
            }
        }

        manager.displayDonors();
        int donorId = integer(input, "Registered donor ID (0 to cancel): ");
        if (donorId == 0) {
            return;
        }
        manager.findDonor(donorId);
        int type = choice(input, "1. Perishable  2. Non-perishable  0. Cancel: ", 2);
        if (type == 0) {
            return;
        }
        String name = text(input, "Food name: ");
        double quantity = positiveQuantity(input);
        String unit = text(input, "Unit (e.g. kg or pieces): ");
        while (true) {
            String expiry = text(input, "Expiry (MM/dd/yyyy): ");
            try {
                FoodItem food = type == 1 ? new PerishableFood(name, quantity, unit, expiry)
                        : new NonPerishableFood(name, quantity, unit, expiry);
                if (action == 1) {
                    Donation donation = manager.createDonation(donorId, food);
                    System.out.println("Created donation ID " + donation.getId());
                } else {
                    manager.updateDonation(id, donorId, food);
                    System.out.println("Donation updated.");
                }
                return;
            } catch (IllegalArgumentException invalidExpiry) {
                System.out.println(invalidExpiry.getMessage());
            }
        }
    }

    private static void requestAction(Scanner input, RecipientManager manager, int action) {
        if (action == 2) {
            if (manager.getRequests().isEmpty()) {
                System.out.println("No requests recorded.");
            }
            manager.getRequests().forEach(FoodRequest::displayInfo);
            return;
        }
        int id = 0;
        if (action != 1) {
            id = integer(input, "Request ID: ");
            FoodRequest request = manager.findRequest(id);
            request.displayInfo();
            if (action == 3) {
                return;
            }
            if (action == 5) {
                if (confirmDelete(input)) {
                    manager.deleteRequest(id);
                    System.out.println("Request deleted.");
                }
                return;
            }
        }

        manager.displayRecipients();
        int recipientId = integer(input, "Registered recipient ID (0 to cancel): ");
        if (recipientId == 0) {
            return;
        }
        manager.findRecipient(recipientId);
        String food = text(input, "Requested food: ");
        double quantity = positiveQuantity(input);
        String unit = text(input, "Unit (use the donation unit): ");
        if (action == 1) {
            FoodRequest request = manager.createRequest(recipientId, food, quantity, unit);
            System.out.println("Created request ID " + request.getId());
        } else {
            manager.updateRequest(id, recipientId, food, quantity, unit);
            System.out.println("Request updated.");
        }
    }

    private static boolean confirmDelete(Scanner input) {
        if (text(input, "Type YES to delete: ").equalsIgnoreCase("YES")) {
            return true;
        }
        System.out.println("Deletion cancelled.");
        return false;
    }

    // Read full lines so organization names can contain spaces and contacts keep leading zeros.
    private static String text(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Enter a value.");
        }
    }

    private static int integer(Scanner input, String prompt) {
        while (true) {
            try {
                return Integer.parseInt(text(input, prompt));
            } catch (NumberFormatException e) {
                System.out.println("Enter a whole number.");
            }
        }
    }

    private static int choice(Scanner input, String prompt, int max) {
        while (true) {
            int value = integer(input, prompt);
            if (value >= 0 && value <= max) {
                return value;
            }
            System.out.println("Choose a listed option.");
        }
    }

    private static double positiveQuantity(Scanner input) {
        while (true) {
            try {
                double value = Double.parseDouble(text(input, "Quantity: "));
                if (Double.isFinite(value) && value > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Text input and out-of-range quantities use the same retry prompt.
            }
            System.out.println("Enter a positive, finite quantity.");
        }
    }
}
