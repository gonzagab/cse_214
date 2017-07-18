/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      Class that holds getter and setter methods for
 * the data fields. The data fields consist of three
 * references to other TreeNodes and three string type
 * variables that hold message, label and prompt.
 */
public class TreeNode
{
    /**
     * Reference to the left child
     */
    private TreeNode left;
    /**
     * Reference to the middle child
     */
    private TreeNode middle;
    /**
     * Reference to the right child
     */
    private TreeNode right;
    /**
     * Label of the node
     */
    private String label;
    /**
     * Message of the node
     */
    private String message;
    /**
     * Reason for visiting the node
     */
    private String prompt;
    /**
     * Constructor that initializes data fields
     */
    public TreeNode()
    {
        left = null;
        middle = null;
        right = null;
    }
    /**
     * Constructor that initializes a node with the folowing
     * parameters
     * @param label
     * @param prompt
     * @param message 
     */
    public TreeNode(String label, String prompt, String message)
    {
        this();
        this.label = label;
        this.message = message;
        this.prompt = prompt;
    }
    /**
     * @return Left Child
     */
    public TreeNode getLeft()
    {
        return left;
    }
    /**
     * @return Middle Child
     */
    public TreeNode getMiddle()
    {
        return middle;
    }
    /**
     * @return Right Child
     */
    public TreeNode getRight()
    {
        return right;
    }
    /**
     * @return Label
     */
    public String getLabel()
    {
        return label;
    }
    /**
     * @return Message
     */
    public String getMessage()
    {
        return message;
    }
    /**
     * @return Prompt
     */
    public String getPrompt()
    {
        return prompt;
    }
    /**
     * Setter for the left child
     * @param left 
     */
    public void setLeft(TreeNode left)
    {
        this.left = left;
    }
    /**
     * Setter for Middle Child
     * @param middle 
     */
    public void setMiddle(TreeNode middle)
    {
        this.middle = middle;
    }
    /**
     * Setter for Right Child
     * @param right 
     */
    public void setRight(TreeNode right)
    {
        this.right = right;
    }
    /**
     * Setter for Label
     * @param label 
     */
    public void setLabel(String label)
    {
        this.label = label;
    }
    /**
     * Setter for message
     * @param message 
     */
    public void setMessage(String message)
    {
        this.message = message;
    }
    /**
     * Setter for prompt
     * @param prompt 
     */
    public void setPrompt(String prompt)
    {
        this.prompt = prompt;
    }
    /**
     * @return true if the node is a leaf
     */
    public boolean isLeaf()
    {
        return (left == null && middle == null && right == null);
    }
}
