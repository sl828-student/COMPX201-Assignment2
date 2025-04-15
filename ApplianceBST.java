

public class ApplianceBST {

    private Node root;

    public ApplianceBST() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }


    public void insert(Appliance a) {
        root = insertIntoSubtree(root, a);
    }

    private Node insertIntoSubtree(Node cRoot, Appliance a) {
        if (cRoot == null) {
            return new Node(a);
        }

        if (a.compareTo(cRoot.value) < 0) {
            cRoot.left = insertIntoSubtree(cRoot.left, a);
        } else if (a.compareTo(cRoot.value) > 0) {
            cRoot.right = insertIntoSubtree(cRoot.right, a);
        } else {
            return cRoot; // Duplicate values are not allowed
        }
        updateHeight(cRoot);
        int balance = getBalanceFactor(cRoot);
        // Left Left Case
        if (balance > 1 && a.compareTo(cRoot.left.value) < 0) {
            return rotateRight(cRoot);
        }
        // Right Right Case
        if (balance < -1 && a.compareTo(cRoot.right.value) > 0) {
            return rotateLeft(cRoot);
        }
        // Left Right Case
        if (balance > 1 && a.compareTo(cRoot.left.value) > 0) {
            cRoot.left = rotateLeft(cRoot.left);
            return rotateRight(cRoot);
        }
        // Right Left Case
        if (balance < -1 && a.compareTo(cRoot.right.value) < 0) {
            cRoot.right = rotateRight(cRoot.right);
            return rotateLeft(cRoot);
        }
        
        return cRoot;
    }

    public void remove(Appliance a) {
        root = removeFromSubtree(root, a);
    }
    private Node removeFromSubtree(Node cRoot, Appliance a) {
        if (cRoot == null) {
            return null;
        }

        if (a.compareTo(cRoot.value) < 0) {
            cRoot.left = removeFromSubtree(cRoot.left, a);
        } else if (a.compareTo(cRoot.value) > 0) {
            cRoot.right = removeFromSubtree(cRoot.right, a);
        } else {
            if (cRoot.left == null || cRoot.right == null) {
                cRoot = (cRoot.left != null) ? cRoot.left : cRoot.right;
            } else {
                Node minNode = findMin(cRoot.right);
                cRoot.value = minNode.value;
                cRoot.right = removeFromSubtree(cRoot.right, minNode.value);
            }
        }
        if (cRoot == null) {
            return null;
        }
        updateHeight(cRoot);
        int balance = getBalanceFactor(cRoot);
        // Left Left Case
        if (balance > 1 && getBalanceFactor(cRoot.left) >= 0) {
            return rotateRight(cRoot);
        }
        // Left Right Case  
        if (balance > 1 && getBalanceFactor(cRoot.left) < 0) {
            cRoot.left = rotateLeft(cRoot.left);
            return rotateRight(cRoot);
        }
        // Right Right Case
        if (balance < -1 && getBalanceFactor(cRoot.right) <= 0) {
            return rotateLeft(cRoot);
        }
        // Right Left Case
        if (balance < -1 && getBalanceFactor(cRoot.right) > 0) {
            cRoot.right = rotateRight(cRoot.right);
            return rotateLeft(cRoot);
        }

        return cRoot;
    }

    public boolean search(Appliance a){
        return searchSubtree(root, a);
    }

    private boolean searchSubtree(Node cRoot, Appliance a){
        if (cRoot == null){
            return false;
        }
        else if (cRoot.value == a) return true;
        else if (a.compareTo(cRoot.value) < 0) return searchSubtree(cRoot.left, a);
        else if (a.compareTo(cRoot.value) > 0) return searchSubtree(cRoot.right, a);
        return false;
    }

    public int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }

    private void updateHeight(Node node){
        if (node != null){
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }
    public int getHeight(){
        return getTreeHeight(root);
    }

    private int getTreeHeight(Node node){
        if (node == null){
            return 0;
        }
        return 1 + Math.max(getTreeHeight(node.left), getTreeHeight(node.right));
    }

    public Appliance getMinimum(){
        if (root == null){
            return null;
        }
        return findMin(root).value;
    }

    private Node findMin(Node cRoot) {
        while (cRoot.left != null) {
            cRoot = cRoot.left;
        }
        return cRoot;
    }

    public Appliance getMaximum(){
        if (root == null){
            return null;
        }
        return findMaximum(root).value;
    }

    private Node findMaximum(Node cRoot){
        while(cRoot.right != null){
            cRoot = cRoot.right;
        }
        return cRoot;
    }

    private int getBalanceFactor(Node node) {
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public void print() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node != null){
            printInOrder(node.left);
            System.out.println(node.value);
            printInOrder(node.right);
        }
    }

    public void printCategory(String category) {
        System.out.println("Appliances in category: " + category);
        printCategoryInOrder(root, category);
    }

    private void printCategoryInOrder(Node node, String c) {
        if (node != null) {
            printCategoryInOrder(node.left, c);
            if (node.value.getCategory().equalsIgnoreCase(c)) {
                System.out.println(node.value);
            }
            printCategoryInOrder(node.right, c);
        }
    }

    public void printCategoryWithPrice(String category, float min, float max) {
        System.out.println("Appliances in category: " + category + " with price between " + min + " and " + max);
        printCategoryWithPriceInOrder(root, category, min, max);
    }

    private void printCategoryWithPriceInOrder(Node node, String c, float min, float max) {
        if (node != null) {
            printCategoryWithPriceInOrder(node.left, c, min, max);
            if (node.value.getCategory().equalsIgnoreCase(c) && node.value.getPrice() >= min && node.value.getPrice() <= max) {
                System.out.println(node.value);
            }
            printCategoryWithPriceInOrder(node.right, c, min, max);
        }
    }
    public void printCategoryAbovePrice(String category, float min) {
        System.out.println("Appliances in category: " + category + " with price above " + min);
        printCategoryAbovePriceInOrder(root, category, min);
    }
    private void printCategoryAbovePriceInOrder(Node node, String c, float min) {
        if (node != null) {
            printCategoryAbovePriceInOrder(node.left, c, min);
            if (node.value.getCategory().equalsIgnoreCase(c) && node.value.getPrice() > min) {
                System.out.println(node.value);
            }
            printCategoryAbovePriceInOrder(node.right, c, min);
        }
    }

    public void printCategoryBelowPrice(String category, float max) {
        System.out.println("Appliances in category: " + category + " with price below " + max);
        printCategoryBelowPriceInOrder(root, category, max);
    }

    private void printCategoryBelowPriceInOrder(Node node, String c, float max) {
        if (node != null) {
            printCategoryBelowPriceInOrder(node.left, c, max);
            if (node.value.getCategory().equalsIgnoreCase(c) && node.value.getPrice() < max) {
                System.out.println(node.value);
            }
            printCategoryBelowPriceInOrder(node.right, c, max);
        }
    }
    public void printCategoryWithPriceRange(String category, float min, float max) {
        System.out.println("Appliances in category: " + category + " with price range: " + min + " - " + max);
        printCategoryWithPriceRangeInOrder(root, category, min, max);
    }
    
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
