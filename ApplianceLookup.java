import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ApplianceLookup {
    public static void main(String[] args) {
        ApplianceBST applianceBST = new ApplianceBST();
        Scanner scanner = new Scanner(System.in);

        // Load appliances from a file
        System.out.println("Loading appliances from file...");
        loadAppliancesFromFile(applianceBST, "appliances.csv");

        // Command-line interface
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Search for an appliance");
            System.out.println("2. Add a new appliance");
            System.out.println("3. Remove an appliance");
            System.out.println("4. Print all appliances in a category");
            System.out.println("5. Print appliances in a category with a price range");
            System.out.println("6. Print appliances in a category above a price");
            System.out.println("7. Print appliances in a category below a price");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter appliance name, category, and price (comma-separated): ");
                    String[] searchInput = scanner.nextLine().split(",");
                    Appliance searchAppliance = new Appliance(searchInput[1].trim(), Float.parseFloat(searchInput[2].trim()), searchInput[0].trim());
                    System.out.println("Appliance found: " + applianceBST.search(searchAppliance));
                    break;

                case 2:
                    System.out.print("Enter appliance name, category, and price (comma-separated): ");
                    String[] addInput = scanner.nextLine().split(",");
                    Appliance newAppliance = new Appliance(addInput[1].trim(), Float.parseFloat(addInput[2].trim()), addInput[0].trim());
                    applianceBST.insert(newAppliance);
                    System.out.println("Appliance added.");
                    break;

                case 3:
                    System.out.print("Enter appliance name, category, and price (comma-separated): ");
                    String[] removeInput = scanner.nextLine().split(",");
                    Appliance removeAppliance = new Appliance(removeInput[1].trim(), Float.parseFloat(removeInput[2].trim()), removeInput[0].trim());
                    applianceBST.remove(removeAppliance);
                    System.out.println("Appliance removed.");
                    break;

                case 4:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    applianceBST.printCategory(category);
                    break;

                case 5:
                    System.out.print("Enter category, min price, and max price (comma-separated): ");
                    String[] rangeInput = scanner.nextLine().split(",");
                    applianceBST.printCategoryWithPriceRange(rangeInput[0].trim(), Float.parseFloat(rangeInput[1].trim()), Float.parseFloat(rangeInput[2].trim()));
                    break;

                case 6:
                    System.out.print("Enter category and min price (comma-separated): ");
                    String[] aboveInput = scanner.nextLine().split(",");
                    applianceBST.printCategoryAbovePrice(aboveInput[0].trim(), Float.parseFloat(aboveInput[1].trim()));
                    break;

                case 7:
                    System.out.print("Enter category and max price (comma-separated): ");
                    String[] belowInput = scanner.nextLine().split(",");
                    applianceBST.printCategoryBelowPrice(belowInput[0].trim(), Float.parseFloat(belowInput[1].trim()));
                    break;

                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadAppliancesFromFile(ApplianceBST applianceBST, String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                try {
                    String[] parts = line.split(",");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Invalid line format: " + line);
                    }
                    String name = parts[0].trim();
                    String category = parts[1].trim();
                    float price = Float.parseFloat(parts[2].trim());
                    applianceBST.insert(new Appliance(category, price, name));
                } catch (Exception e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Appliances loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }
}
