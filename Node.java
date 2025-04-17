/**
 * Node class representing a node in a binary tree.
 * Each node contains an Appliance object, references to left and right child nodes, and the height of the node.
 * 
 */
public class Node {
    public Appliance value; // Appliance object stored in the node
    public Node left; // Reference to the left child node
    public Node right;  // Reference to the right child node
    public int height; // Height of the node in the tree

    /**
     * Constructor to create a new node with the given Appliance value.
     * Initializes left and right child nodes to null and height to 1.
     * @param value is the appliance onject stored in the node
     */
    public Node(Appliance value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1;
    }      
}
