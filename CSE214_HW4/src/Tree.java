
import java.util.Scanner;

/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      Class that holds a single reference to
 * the root of the tree and has methods to modify
 * and access different elements of the tree.
 */
public class Tree
{
    /**
     * Reference to the root of the tree
     */
    private TreeNode root;
    /**
     * Constructor that initializes the root to null
     */
    public Tree()
    {
        root = null;
    }
    /**
     * Adds a new node to the tree. Adds the node as a child
     * of the parent with the parameter parentLabel.
     * @param newNode
     * @param parentLabel
     * @return True if node could be added; False if node was not added
     */
    public boolean addNode(TreeNode newNode, String parentLabel)
    {
        if(root == null)
        {
            root = newNode;
            return true;
        }
        TreeNode nodePtr = getNodeReference(parentLabel);
        if(nodePtr == null)
            return false;
        else if(nodePtr.getLeft() == null)
            nodePtr.setLeft(newNode);
        else if(nodePtr.getMiddle() == null)
            nodePtr.setMiddle(newNode);
        else if(nodePtr.getRight() == null)
            nodePtr.setRight(newNode);
        else
            return false;
        return true;   
    }
    /**
     * Adds a new node to the tree. Adds the node as a child
     * of the parent with the parameter parentLabel.
     * @param label
     * @param prompt
     * @param message
     * @param parentLabel
     * @return True if node could be added; False if node was not added
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel)
    {
        if(root == null)
        {
            root = new TreeNode(label, prompt, message);
            return true;
        }
        TreeNode nodePtr = getNodeReference(parentLabel);
        if(nodePtr == null)
            return false;
        if(nodePtr.getLeft() == null)
            nodePtr.setLeft(new TreeNode(label, prompt, message));
        else if(nodePtr.getMiddle() == null)
            nodePtr.setMiddle(new TreeNode(label, prompt, message));
        else if(nodePtr.getRight() == null)
            nodePtr.setRight(new TreeNode(label, prompt, message));
        else
            return false;
        return true;
    }
    /**
     * Returns the Node Reference of the node with the
     * label passed.
     * @param label
     * @return a TreeNode Reference
     */
    public TreeNode getNodeReference(String label)
    {
        return getNodeReference(root, label);
    }
    /**
     * Prints the tree in pre-order
     */
    public void preorder()
    {
        preorder(root);
    }
    /**
     * Starts a help session with the user and traverses the
     * tree according to the answers
     */
    public void beginSession()
    {
        Scanner input = new Scanner(System.in);
        TreeNode nodePtr = root;
        TreeNode trailPtr = root;
        String choice;
        do
        {
            System.out.println(nodePtr.getMessage());
            if(nodePtr.getLeft() != null)
                System.out.println("1) " + nodePtr.getLeft().getPrompt());
            if(nodePtr.getMiddle() != null)
                System.out.println("2) " + nodePtr.getMiddle().getPrompt());
            if(nodePtr.getRight() != null)
                System.out.println("3) " + nodePtr.getRight().getPrompt());
            if(nodePtr != root)
                System.out.println("4) Go Back");
            System.out.println("0) Exit Session.");
            choice = input.nextLine();
            switch(choice)
            {
                case "0": return;
                case "1":
                    if(nodePtr.getLeft() == null)
                        break;
                    trailPtr = nodePtr;
                    nodePtr = nodePtr.getLeft();
                    break;
                case "2":
                    if(nodePtr.getMiddle() == null)
                        break;
                    trailPtr = nodePtr;
                    nodePtr = nodePtr.getMiddle();
                    break;
                case "3":
                    if(nodePtr.getRight() == null)
                        break;
                    trailPtr = nodePtr;
                    nodePtr = nodePtr.getRight();
                    break;
                case "4":
                    if(nodePtr == root)
                        break;
                    nodePtr = trailPtr;
                    trailPtr= getParentNode(nodePtr, root);
                    break;
                default:
                    System.out.println("Invalid entry. Try again");
            }
        }while(!choice.equals("0") && !isEmpty(nodePtr));
        System.out.println(nodePtr.getMessage());
    }
    /**
     * @return true if tree is empty
     */
    public boolean isEmpty()
    {
        return (root == null);
    }
    /**
     * Check the Node that is passed has children or is null
     * @param root
     * @return true is root is empty
     */
    static public boolean isEmpty(TreeNode root)
    {
        if(root == null)
            return false;
        return (root.getLeft() == null);
    }
    /**
     * Prints the tree in pre-order
     */
    private void preorder(TreeNode root)
    {
        System.out.println("Label: " + root.getLabel());
        System.out.println("Prompt: " + root.getPrompt());
        System.out.println("Message: " + root.getMessage());
        System.out.println();
        if(root.getLeft() != null)
            preorder(root.getLeft());
        if(root.getMiddle() != null)
            preorder(root.getMiddle());
        if(root.getRight() != null)
            preorder(root.getRight());
    }
    /**
     * Returns the Node Reference of the node with the
     * label passed.
     * @param label
     * @return a TreeNode Reference
     */
    private TreeNode getNodeReference(TreeNode root, String label)
    {
        TreeNode pointer;
        if(root.getLabel().equals(label))
            return root;
        if(root.getLeft() != null)
        {
            pointer = getNodeReference(root.getLeft(), label);
            if(pointer != null && pointer.getLabel().equals(label))
                return pointer;
        }      
        if(root.getMiddle() != null)
        {
            pointer = getNodeReference(root.getMiddle(), label);
            if(pointer != null && pointer.getLabel().equals(label))
                return pointer;
        }
        if(root.getRight() != null)
        {
            pointer = getNodeReference(root.getRight(), label);
            if(pointer != null && pointer.getLabel().equals(label))
                return pointer;
        }
        return null;
    }
    /**
     * returns the parent of the child that is in the tree parent
     * @param child
     * @param parent
     * @return Tree Node reference
     */
    private TreeNode getParentNode(TreeNode child, TreeNode parent)
    {
        if(child == null || child == root || parent.isLeaf())
            return null;
        if(parent.getLeft() == child || parent.getMiddle() == child || parent.getRight() == child)
            return parent;
        
        TreeNode leftPtr = null;
        TreeNode middlePtr = null;
        TreeNode rightPtr = null;
        
        if(parent.getLeft() != null)
            leftPtr = getParentNode(child, parent.getLeft());
        if(parent.getRight() != null)
            middlePtr = getParentNode(child, parent.getMiddle());
        if(parent.getMiddle() != null)
            rightPtr = getParentNode(child, parent.getRight());
       
        if(leftPtr != null)
            return leftPtr;
        if(middlePtr != null)
            return middlePtr;
        if(rightPtr != null)
            return rightPtr;
        return null;
    }
}
