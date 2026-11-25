import java.util.ArrayList;
import java.util.List;

public class RecipientManager {
    private final List<Recipient> recipients = new ArrayList<>();
    private final List<FoodRequest> requests = new ArrayList<>();
    private int nextRequestId = 1;
    private int nextRecipientId = 1;

    public Recipient registerRecipient(int type, String name, String contact) {
        Recipient recipient = switch (type) {
            case 1 -> new CommunityPantry(nextRecipientId, name, contact);
            case 2 -> new CharityOrganization(nextRecipientId, name, contact);
            default -> throw new IllegalArgumentException("Unknown organization type.");
        };
        recipients.add(recipient);
        nextRecipientId++;
        return recipient;
    }

    public Recipient findRecipient(int id) {
        for (Recipient recipient : recipients) {
            if (recipient.getId() == id) {
                return recipient;
            }
        }
        throw new IllegalArgumentException("Recipient ID " + id + " was not found.");
    }

    public void displayRecipients() {
        if (recipients.isEmpty()) {
            System.out.println("No recipients registered.");
        }
        for (Recipient recipient : recipients) {
            recipient.displayInfo();
        }
    }

    public void updateRecipient(int id, String name, String contact) {
        findRecipient(id).updateDetails(name, contact);
    }

    public void deleteRecipient(int id) {
        Recipient record = findRecipient(id);
        for (FoodRequest request : requests) {
            if (request.getRecipient() == record) {
                throw new IllegalArgumentException("Delete this recipient's requests first.");
            }
        }
        recipients.remove(record);
    }

    public FoodRequest createRequest(int recipientId, String food, double quantity, String unit) {
        FoodRequest request = new FoodRequest(nextRequestId, findRecipient(recipientId), food,
                quantity, unit, java.time.LocalDate.now());
        requests.add(request);
        nextRequestId++;
        return request;
    }

    public FoodRequest findRequest(int id) {
        for (FoodRequest request : requests) {
            if (request.getId() == id) {
                return request;
            }
        }
        throw new IllegalArgumentException("Request ID " + id + " was not found.");
    }

    public List<FoodRequest> getRequests() {
        return List.copyOf(requests);
    }

    public void updateRequest(int id, int recipientId, String food, double quantity, String unit) {
        FoodRequest original = findRequest(id);
        // Validate the replacement before swapping it into the same position.
        FoodRequest updated = new FoodRequest(id, findRecipient(recipientId), food, quantity,
                unit, original.getRequestDate());
        requests.set(requests.indexOf(original), updated);
    }

    public void deleteRequest(int id) {
        requests.remove(findRequest(id));
    }
}
