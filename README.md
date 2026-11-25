# REFOOD
Community-Based Food Rescue and Redistribution Management System. A first-semester BSIT 2B project for Data Structures and Algorithms and Object-Oriented Programming.

## Run the application
Use JDK 17 or newer. From the repository root in PowerShell:

```powershell
Set-Location G.D/refoodsystem
javac *.java
java Main
```

The program starts with empty lists. Records stay available while navigating the menus and are cleared when the process exits.

## What works
1. Open **Donors** and register a bakery, grocery, or restaurant. Keep the generated donor ID.
2. Open **Donations**, select **Create**, and enter that donor ID. Choose a food type, then enter its name, quantity, unit, and expiry in MM/dd/yyyy format. A donor can supply multiple donations.
3. Open **Recipients** to register a community pantry or charity organization.
4. Each of these three menus supports create, list, find by ID, update, delete, and back. Updating an organization edits its name and contact. Updating a donation can change its donor and food details. Type YES to confirm deletion. Use 0 in the main menu to exit.

## Changes in this handoff
- Added interactive recipient registration and donation management. Finished donor management and replaced the original sample-data flow with keyboard input.
- Fixed the donor update condition, switch blocks, and delete confirmation. Looking up a record for confirmation now leaves it intact. Donors with existing donations cannot be deleted.
- Added quantity and strict calendar-date validation. Available donations must have positive finite stock and expire today or later. Existing records display their current urgency as time passes.
- Kept the original donor, recipient, and food subclasses. FoodItem stores a parsed date internally and keeps the existing formatted getter and string setter for callers. Available stock still comes directly from the food item.

## Where the code lives

| Class | Responsibility |
| --- | --- |
| Main | Owns the Scanner and one instance of each manager. Reads full lines, retries invalid input, and dispatches menu actions. |
| User, Donor, Recipient and their subclasses | Share ID, name, contact, getters, setters, and display behavior. Each concrete organization supplies its own role. |
| DonationManager | Keeps registered donors in an ArrayList and donation records in a LinkedList. Generates IDs, looks up records, and protects donor links. |
| RecipientManager | Keeps recipients in an ArrayList, assigns IDs, and handles recipient and food-request CRUD. |
| FoodItem and its subclasses | Validate food details and quantity through setters. getExpiryDate() returns a LocalDate for comparisons. calculateUrgency() produces the display label. |
| Donation | Links a registered donor to a food batch. getAvailableQuantity() reads the batch quantity. |

Managers assign IDs once per record. Use manager update methods for registered records so references remain consistent. Keep the collections and ID counters private.

## Food request handoff

Menu 4 lets you create, list, find, edit, and delete food requests. Each request belongs to a registered recipient and records a food name, quantity, unit, and creation date. Creating a request leaves donation stock unchanged. Editing preserves the request ID and creation date. The recipient stays registered while any request references it.

To demonstrate, register a pantry in menu 2, then request 25 pieces of bread in menu 4. Find the request by ID, edit the quantity to 30, and list it again. Try deleting the pantry while the request exists. Delete the request, then retry deleting the pantry.

The implementation stops at food-request CRUD. MatchingEngine, SortingManager, Distribution, and ReportManager are placeholders for later group work.
