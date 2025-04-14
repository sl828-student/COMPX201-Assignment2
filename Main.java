public class Main {
    public static void main(String[] args) {
        // Create an instance of ApplianceBST
        ApplianceBST applianceBST = new ApplianceBST();

        // Create appliances
        Appliance standFreezer = new Appliance("Fridge", 1000.20f, "Stand Freezer");
        Appliance standerFreezest = new Appliance("Fridge", 10430.20f, "Stander Freezest");
        Appliance candle = new Appliance("Oven", 100.20f, "Candle");
        Appliance gasStove = new Appliance("Oven", 10200.20f, "Gas Stove");
        Appliance heatPump = new Appliance("Aircon", 1030.20f, "Heat pump");
       
        // Insert appliances into the BST
        applianceBST.insert(standFreezer);
        applianceBST.insert(standerFreezest);
        applianceBST.insert(candle);
        applianceBST.insert(gasStove);
        applianceBST.insert(heatPump);

        // Print the tree structure
        System.out.println("Tree:");
        StrBSTPrinter.printNode(applianceBST.getRoot());

        // Test search for specific appliances
        System.out.println("\nHas element Candle: " + applianceBST.search(candle));
        System.out.println("Has element Heat Pump: " + applianceBST.search(heatPump));
        System.out.println("Has element Nonexistent: " + applianceBST.search(new Appliance("Nonexistent", 0, "Nonexistent")));

        // Print sorted appliances (in-order traversal)
        System.out.println("\nSorted Appliances:");
        applianceBST.print();

        // Test height of the tree
        System.out.println("\nHeight: " + applianceBST.getHeight());

        // Test minimum and maximum appliances
        System.out.println("Min: " + applianceBST.getMinimum());
        System.out.println("Max: " + applianceBST.getMaximum());
    }
}
 
