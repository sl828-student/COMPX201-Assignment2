
/**
 * this class implements a binary search tree (BST) for storing appliances.
 * It allows for insertion, deletion, searching, and printing of appliances 
 * based on various criteria such as category and price range.
 * the tree is balanced using AVL rotations
 */
public class ApplianceBST {

    private Node root; // Root of the tree
    /**
     * Constructor 
     * initlizes the root of the tree to null
     */
    public ApplianceBST() {
        this.root = null;
    }

    /**
     * getter for the root of the tree
     * @return the root node
     */
    public Node getRoot() {
        return root;
    }

    /**
     * setter for the root of the tree
     * @param root the new root node
     */
    public void insert(Appliance a) {
        root = insertIntoSubtree(root, a);
    }

    /**
     * inserts a new appliance into the tree
     * @param cRoot is the current root of the subtree
     * @param a is the apliance that is to be inersted
     * @return the updated root of the subtree
     */
    private Node insertIntoSubtree(Node cRoot, Appliance a) {
        if (cRoot == null) {
            return new Node(a); //if subtree empty create new node
        }

        if (a.compareTo(cRoot.value) < 0) {
            cRoot.left = insertIntoSubtree(cRoot.left, a); //left if smaller
        } else if (a.compareTo(cRoot.value) > 0) {
            cRoot.right = insertIntoSubtree(cRoot.right, a); //right if bigger
        } else {
            return cRoot; // Duplicates not allowed
        }
        //update the heigh of the current node
        updateHeight(cRoot);
        //get the balance factor of the current node
        int balance = getBalanceFactor(cRoot);
        // Left Left Case
        if (balance > 1 && a.compareTo(cRoot.left.value) < 0) {
            return rotateRight(cRoot); // Right rotation
        }
        // Right Right Case
        if (balance < -1 && a.compareTo(cRoot.right.value) > 0) {
            return rotateLeft(cRoot); // Left rotation
        }
        // Left Right Case
        if (balance > 1 && a.compareTo(cRoot.left.value) > 0) {
            cRoot.left = rotateLeft(cRoot.left); //Rotate left on childe
            return rotateRight(cRoot); // Rotate right on parent
        }
        // Right Left Case
        if (balance < -1 && a.compareTo(cRoot.right.value) < 0) {
            cRoot.right = rotateRight(cRoot.right); // Rotate right on child
            return rotateLeft(cRoot); // Rotate left on parent
        }
        
        return cRoot; //return updated root
    }

    /**
     * Removes an appliance from the tree
     * @param a is the appliance to be removed
     */
    public void remove(Appliance a) {
        root = removeFromSubtree(root, a);
    }

    /**
     * removes an appliance from the tree
     * @param cRoot is the current root of the subtree
     * @param a is the appliance that is going to be removed
     * @return the updated root of the subtree
     */
    private Node removeFromSubtree(Node cRoot, Appliance a) {
        if (cRoot == null) {
            return null; //check if subtree empy, nothing to remove
        }

        if (a.compareTo(cRoot.value) < 0) {
            cRoot.left = removeFromSubtree(cRoot.left, a); //left if smaller
        } else if (a.compareTo(cRoot.value) > 0) {
            cRoot.right = removeFromSubtree(cRoot.right, a); //right if bigger
        } else {
            if (cRoot.left == null || cRoot.right == null) {
                cRoot = (cRoot.left != null) ? cRoot.left : cRoot.right; //replace with child
            } else {
                Node minNode = findMin(cRoot.right); //find the minimum node in the right subtree
                cRoot.value = minNode.value; //replace current node with minimum node
                cRoot.right = removeFromSubtree(cRoot.right, minNode.value); //remove the minimum node from the right subtree
            }
        }
        if (cRoot == null) {
            return null; //if the subtree is empty after removal, retun null
        }
        updateHeight(cRoot); //update the height of the current node
        int balance = getBalanceFactor(cRoot); //get the balance factor of the current node
        // Left Left Case
        if (balance > 1 && getBalanceFactor(cRoot.left) >= 0) {
            return rotateRight(cRoot); // Right rotation
        }
        // Left Right Case  
        if (balance > 1 && getBalanceFactor(cRoot.left) < 0) {
            cRoot.left = rotateLeft(cRoot.left); // Rotate left on child
            return rotateRight(cRoot); // Rotate right on parent
        }
        // Right Right Case
        if (balance < -1 && getBalanceFactor(cRoot.right) <= 0) {
            return rotateLeft(cRoot); // Left rotation
        }
        // Right Left Case
        if (balance < -1 && getBalanceFactor(cRoot.right) > 0) {
            cRoot.right = rotateRight(cRoot.right); // Rotate right on child
            return rotateLeft(cRoot); // Rotate left on parent
        }

        return cRoot; //return updated root
    }

    /**
     * Searches for a specific appliance in the tree
     * @param a is the appliance that will be searched
     * @return true if the appliance is found, false otherwise
     */
    public boolean search(Appliance a){
        return searchSubtree(root, a);
    }

    /**
     * Helper method to search for an appliance in the tree
     * @param cRoot is the current root of the subtree
     * @param a ist the appliance that will be searched
     * @return true if the appliance is found, false otherwise
     */
    private boolean searchSubtree(Node cRoot, Appliance a){
        if (cRoot == null){
            return false; //if subtree empty, appliance not found
        }
        else if (cRoot.value == a) return true; //if appliance found
        else if (a.compareTo(cRoot.value) == 0) return true; //if appliance found
        else if (a.compareTo(cRoot.value) < 0) return searchSubtree(cRoot.left, a); //left if smaller
        else if (a.compareTo(cRoot.value) > 0) return searchSubtree(cRoot.right, a); //right if bigger
        else
        return false; //if appliance not found
    }

    public int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }
    /**
     * updates the height of the node
     * @param node is the node whose height is to be updated
     */
    private void updateHeight(Node node){
        if (node != null){
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    /**
     * getter for the height of the tree
     * @param node is the node whose height is to be returned
     * @return the height of the tree
     */
    public int getHeight(){
        return getTreeHeight(root);
    }

    /**
     * getter for the height of the tree
     * @param node 
     * @return
     */
    private int getTreeHeight(Node node){
        if (node == null){
            return 0;
        }
        return 1 + Math.max(getTreeHeight(node.left), getTreeHeight(node.right));
    }

    /**
     * getter for the minimum value in the tree
     * @return the minimum value in the tree
     */
    public Appliance getMinimum(){
        if (root == null){
            return null;
        }
        return findMin(root).value;
    }

    /**
     * finds the minimum value in the tree
     * @param cRoot is the current root of the subtree
     * @return the minimum value in the tree
     */
    private Node findMin(Node cRoot) {
        while (cRoot.left != null) {
            cRoot = cRoot.left;
        }
        return cRoot;
    }

    /**
     * getter for the maximum value in the tree
     * @return the maximum value in the tree
     */
    public Appliance getMaximum(){
        if (root == null){
            return null;
        }
        return findMaximum(root).value;
    }

    /**
     * finds the maximum value in the tree
     * @param cRoot is the current root of the subtree
     * @return the maximum value in the tree
     */
    private Node findMaximum(Node cRoot){
        while(cRoot.right != null){
            cRoot = cRoot.right;
        }
        return cRoot;
    }

    /**
     * getter for the balance factor of the node
     * @param node is the node whose balance factor is to be returned
     * @return the balance factor of the node
     */
    private int getBalanceFactor(Node node) {
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * rotates the tree to the right
     * @param y is the node that is going to be rotated
     * @return the new root of the subtree
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /**
     * rotates the tree to the left
     * @param x is the node that is going to be rotated
     * @return the new root of the subtree
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    /**
     * prints the tree in order
     */
    public void print() {
        printInOrder(root);
    }

    /**
     * prints the tree in order
     * @param node is the current root of the subtree
     */
    private void printInOrder(Node node) {
        if (node != null){
            printInOrder(node.left);
            System.out.println(node.value);
            printInOrder(node.right);
        }
    }

    /**
     * prints the nodes of a specific category
     */
    public void printCategory(String category) {
        System.out.println("Appliances in category: " + category);
        printCategoryInOrder(root, category);
    }

    /**
     * prints the nodes of a specific category
     * @param node is the current root of the subtree
     * @param c is the category that is going to be printed
     */
    private void printCategoryInOrder(Node node, String c) {
        if (node != null) {
            printCategoryInOrder(node.left, c);
            if (node.value.getCategory().equalsIgnoreCase(c)) {
                System.out.println(node.value);
            }
            printCategoryInOrder(node.right, c);
        }
    }

    /**
     * prints the nodes of a specific category with a price range
     * @param category is the category that is going to be printed
     * @param min is the min price
     * @param max is the max price
     */
    public void printCategoryWithPrice(String category, float min, float max) {
        System.out.println("Appliances in category: " + category + " with price between " + min + " and " + max);
        printCategoryWithPriceInOrder(root, category, min, max);
    }

    /**
     * prints the nodes of a specific category with a price range
     * @param node is the current root of the subtree
     * @param c is the category that is going to be printed
     * @param min is the min price
     * @param max is the max price
     */
    private void printCategoryWithPriceInOrder(Node node, String c, float min, float max) {
        if (node != null) {
            printCategoryWithPriceInOrder(node.left, c, min, max);
            if (node.value.getCategory().equalsIgnoreCase(c) && node.value.getPrice() >= min && node.value.getPrice() <= max) {
                System.out.println(node.value);
            }
            printCategoryWithPriceInOrder(node.right, c, min, max);
        }
    }

    /**
     * prints the nodes of a specific category with a price above a certain value
     * @param category is the category that is going to be printed
     * @param min is the min price
     */
    public void printCategoryAbovePrice(String category, float min) {
        System.out.println("Appliances in category: " + category + " with price above " + min);
        printCategoryAbovePriceInOrder(root, category, min);
    }

    /**
     * prints the nodes of a specific category with a price above a certain value
     * @param node is the current root of the subtree
     * @param c is the category that is going to be printed
     * @param min is the min price
     */
    private void printCategoryAbovePriceInOrder(Node node, String c, float min) {
        if (node != null) {
            printCategoryAbovePriceInOrder(node.left, c, min);
            if (node.value.getCategory().equalsIgnoreCase(c) && node.value.getPrice() > min) {
                System.out.println(node.value);
            }
            printCategoryAbovePriceInOrder(node.right, c, min);
        }
    }

    /**
     * prints the nodes of a specific category with a price below a certain value
     * @param category is the category that is going to be printed
     * @param max is the max price
     */
    public void printCategoryBelowPrice(String category, float max) {
        System.out.println("Appliances in category: " + category + " with price below " + max);
        printCategoryBelowPriceInOrder(root, category, max);
    }

    /**
     * prints the nodes of a specific category with a price below a certain value
     * @param node is the current root of the subtree
     * @param c is the category that is going to be printed
     * @param max is the max price
     */
    private void printCategoryBelowPriceInOrder(Node node, String c, float max) {
        if (node != null) {
            printCategoryBelowPriceInOrder(node.left, c, max);
            if (node.value.getCategory().equalsIgnoreCase(c) && node.value.getPrice() < max) {
                System.out.println(node.value);
            }
            printCategoryBelowPriceInOrder(node.right, c, max);
        }
    }
    /**
     * prints the nodes of a specific category with a price range
     * @param category is the category that is going to be printed
     * @param min is the min price
     * @param max is the max price
     */
    public void printCategoryWithPriceRange(String category, float min, float max) {
        System.out.println("Appliances in category: " + category + " with price range: " + min + " - " + max);
        printCategoryWithPriceRangeInOrder(root, category, min, max);
    }
    
    /**
     * prints the nodes of a specific category with a price range
     * @param node is the current root of the subtree
     * @param category is the category that is going to be printed
     * @param min is the min price
     * @param max is the max price
     */
    private void printCategoryWithPriceRangeInOrder(Node node, String category, float min, float max) {
        if (node == null) {
            return;
        }
    
        // Always traverse the left subtree to ensure all relevant nodes are visited
        printCategoryWithPriceRangeInOrder(node.left, category, min, max);
    
        // Check if the current node matches the category and is within the price range
        if (node.value.getCategory().equalsIgnoreCase(category) &&
            node.value.getPrice() >= min && node.value.getPrice() <= max) {
            System.out.println(node.value);
        }
    
        // Always traverse the right subtree to ensure all relevant nodes are visited
        printCategoryWithPriceRangeInOrder(node.right, category, min, max);
    }
    
}
