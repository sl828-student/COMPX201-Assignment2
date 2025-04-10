public class TestApplianceBST {
    public static void main(String[] args) {
        // Create a new ApplianceBST
        ApplianceBST bst = new ApplianceBST();

        // Insert appliances into the BST
        bst.insert(new Appliance("Aircon", 1030.20f, "Heat pump"));
        bst.insert(new Appliance("Fridge", 1000.20f, "Stand Freezer"));
        bst.insert(new Appliance("Fridge", 10430.20f, "Stander Freezest"));
        bst.insert(new Appliance("Oven", 100.20f, "Candle"));
        bst.insert(new Appliance("Oven", 10200.20f, "Gas Stove"));

        // Print the tree structure using StrBSTPrinter
        System.out.println("Tree:");
        StrBSTPrinter.printNode(bst.getRoot());

        // Test other methods
        System.out.println("\nHas element Candle: " + bst.search(new Appliance("Oven", 100.20f, "Candle")));
        System.out.println("Has element Heat Pump: " + bst.search(new Appliance("Aircon", 1030.20f, "Heat pump")));

        System.out.println("\nSorted Appliances:");
        bst.print();

        System.out.println("\nHeight: " + bst.getHeight());
        System.out.println("Min: " + bst.getMinimum());
        System.out.println("Max: " + bst.getMaximum());
    }
}
