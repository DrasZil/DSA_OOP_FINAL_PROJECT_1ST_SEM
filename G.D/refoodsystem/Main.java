public class Main {
    public static void main(String[] args) {
        //test run

        PerishableFood p1 = new PerishableFood("Pizza", 10, "pieces", "10/23/2020");
        NonPerishableFood np1 = new NonPerishableFood("Rice", 50, "kgs", "10/21/2027");


        // Create a donor
        Donor donor = new BakeryDonor(1, "ABC Bakery", "09123456789");

        // Create a donation
        Donation donation = new Donation( donor, np1, 101);

        // Display donation details
        donation.displayInfo();
    }
}
