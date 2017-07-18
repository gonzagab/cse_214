/**
 *  Class that provides methods and pointers to create a node
 * in a list that holds item information.
 * @author Bryant Gonzaga
 * @version 1.0
 */
public class ItemInfoNode
{
    /**
     * Holds the information for an item
     */
    private ItemInfo itemInfo;
    /**
     * Points to the node previous.
     */
    private ItemInfoNode prev;
    /**
     * Points to the next node in the list
     */
    private ItemInfoNode next;
    /**
     * Creates and object of ItemInfoNode
     */
    public ItemInfoNode()
    {}
    /**
     * @return 
     *      Pointer to InfoItem
     */
    public ItemInfo getInfo()
    {
        return itemInfo;
    }
    /**
     * @return
     *      Pointer to the previous ItemInfoNode
     */
    public ItemInfoNode getPrev()
    {
        return prev;
    }
    /**
     * @return 
     *      Pointer to the next ItemInfoNode 
     */
    public ItemInfoNode getNext()
    {
        return next;
    }
    /**
     * Setter for ItemInfo data field. Sets it to the parameter passed
     * @param info 
     */
    public void setInfo(ItemInfo info)
    {
        this.itemInfo = info;
    }
    /**
     * Setter for the previous pointer.
     * @param node 
     */
    public void setPrev(ItemInfoNode node)
    {
        prev = node;
    }
    /**
     * Setter for the next pointer
     * @param node 
     */
    public void setNext(ItemInfoNode node)
    {
        next = node;
    }   
}