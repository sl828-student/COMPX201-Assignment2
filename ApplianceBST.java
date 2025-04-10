

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
            if (cRoot.left == null && cRoot.right == null) {
                return null;
            } else if (cRoot.left == null) {
                return cRoot.right;
            } else if (cRoot.right == null) {
                return cRoot.left;
            } else {
                Node minNode = findMin(cRoot.right);
                cRoot.value = minNode.value;
                cRoot.right = removeFromSubtree(cRoot.right, minNode.value);
            }
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
}
