/**
 * @author Bryant Gonzaga
 * @version 1.0
 *  Class that has a head and tail pointer to a list of
 * ItemInfoNode's. With methods to modify the list and the
 * information in the nodes.
 * @author Bryant Gonzaga
 */
public class ItemList
{
    /**
     * Holds a pointer to the first node in the list
     */
    private ItemInfoNode head;
    /**
     * Holds a pointer to the last node in the list
     */
    private ItemInfoNode tail;
    /**
     * Holds a pointer that points to any node in the list
     */
    private ItemInfoNode cur;
    /**
     * Constructor that creates an object of ItemInfoNode and
     * initiates all the pointers to null
     */
    public ItemList()
    {
        head = null;
        tail = head;
        cur = head;
    }
    /**
     * inserts a new node into the list with the specified parameters
     * @param name
     * @param rfidTag
     * @param price
     * @param initPosition 
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition)
    {
        ItemInfoNode node = new ItemInfoNode();
        node.setInfo(new ItemInfo(name, rfidTag, price, initPosition));
        if(head == null)
        {
            head = node;
            tail = node;
            cur = node;
        }
        else if(head.getNext() == null || hexToLong(head.getInfo().getTagNumber()) >= hexToLong(node.getInfo().getTagNumber()) || hexToLong(tail.getInfo().getTagNumber()) <= hexToLong(node.getInfo().getTagNumber()))
        {
            if(hexToLong(head.getInfo().getTagNumber()) >= hexToLong(node.getInfo().getTagNumber()))
            {
                head.setPrev(node);
                node.setNext(head);
                head = node;
            }
            else if(hexToLong(tail.getInfo().getTagNumber()) <= hexToLong(node.getInfo().getTagNumber()))
            {
                tail.setNext(node);
                node.setPrev(tail);
                tail = node;
            }
        }
        else
        {
            while(hexToLong(cur.getInfo().getTagNumber()) < hexToLong(node.getInfo().getTagNumber()))
            {
                cur = cur.getNext();
            }
            while(hexToLong(cur.getInfo().getTagNumber()) > hexToLong(node.getInfo().getTagNumber()))
            {
                cur = cur.getPrev();
            }
            cur.getNext().setPrev(node);
            node.setNext(cur.getNext());
            node.setPrev(cur);
            cur.setNext(node);
        }
    }
    /**
     * Finds the node according to the RFID tag number and checks if the 
     * location is equal to source. it then changes the current location
     * to the new location.
     * @param rfidTag
     * @param source
     * @param dest
     * @return itemMoved: True if item was moved false if item could not be located
     * @throws IllegalArgumentException
     *      if the source is out or dest does not match a proper format
     */
    public boolean moveItem(String rfidTag, String source, String dest) throws IllegalArgumentException
    {
        source = source.toLowerCase();
        boolean itemMoved = false;
        ItemInfo temp;
        if(source.equals("out"))
            throw new IllegalArgumentException("You can't move an item that is already out.");
        while(hexToLong(cur.getInfo().getTagNumber()) < hexToLong(rfidTag))
        {
            if(cur.getNext() == null)
                break;
            cur = cur.getNext();
        }
        while(hexToLong(cur.getInfo().getTagNumber()) > hexToLong(rfidTag))
        {
            if(cur.getPrev() == null)
                break;
            cur = cur.getPrev();
        }
        if(hexToLong(rfidTag) != hexToLong(cur.getInfo().getTagNumber()))
            throw new IllegalArgumentException("The item with the specified RFID tag could not be found.");
        while(hexToLong(rfidTag) == hexToLong(cur.getInfo().getTagNumber()) && !itemMoved)
        {
            if(cur.getInfo().getCurrentLocation().equals(source))
            {
                temp = cur.getInfo();
                temp.setCurrentLocation(dest);
                itemMoved = true;
            }
            else if(cur.getNext() == null)
                break;
            else
                cur = cur.getNext();
        }
        return itemMoved;
    }
    /**
     * Prints all the items in the list in a table according to RFID tag Number
     * @throws EmptyListException 
     *      if the list is empty then there is nothing to print out
     */
    public void printAll() throws EmptyListException
    {
        if(head == null)
            throw new EmptyListException("List is empty.");
        cur = head;
        System.out.println(String.format("%-18s %-18s %-18s %-16s %-5s", "RFID Tag #", "Item Name", "Original Location", "Current Location", "Price"));
        System.out.println("-------------------------------------------------------------------------------");
        while(cur != null)
        {
            System.out.println(String.format("%-18s %-18s %-18s %-16s $%.2f", cur.getInfo().getTagNumber(), cur.getInfo().getName(), cur.getInfo().getOriginalLocation(), cur.getInfo().getCurrentLocation(), cur.getInfo().getPrice()));
            cur = cur.getNext();
        }
        cur = head;
    }
    /**
     * Prints all the items in the specified location
     * @param location
     * @throws EmptyListException 
     *      if the list is empty then there is nothing to print
     */
    public void printByLocation(String location) throws EmptyListException
    {
        if(head == null)
            throw new EmptyListException("List is empty.");
        cur = head;
        System.out.println(String.format("%-18s %-18s %-18s %-16s %-5s", "RFID Tag #", "Item Name", "Original Location", "Current Location", "Price"));
        System.out.println("-------------------------------------------------------------------------------");
        while(cur != null)
        {
            if(location.equals(cur.getInfo().getCurrentLocation()))
                System.out.println(String.format("%-18s %-18s %-18s %-16s %.2f", cur.getInfo().getTagNumber(), cur.getInfo().getName(), cur.getInfo().getOriginalLocation(), cur.getInfo().getCurrentLocation(), cur.getInfo().getPrice()));
            cur = cur.getNext();
        }
        cur = head;
    }
    /**
     *  Prints all the items with the specifeid RFID tag number.
     * @param rfid
     * @throws EmptyListException
     *      there is nothing to be printed if the list is empty
     */
    public void printByRfid(String rfid) throws EmptyListException
    {
        if(head == null)
            throw new EmptyListException("List is empty.");
        boolean headerPrinted = false;
        cur = head;
        while(cur != null)
        {
            if(hexToLong(rfid) == hexToLong(cur.getInfo().getTagNumber()))
            {
                 if(!headerPrinted)
                {
                    System.out.println(String.format("%-18s %-18s %-18s %-16s %-5s", "RFID Tag #", "Item Name", "Original Location", "Current Location", "Price"));
                    System.out.println("-------------------------------------------------------------------------------");
                    headerPrinted = true;
                }
                System.out.println(String.format("%-18s %-18s %-18s %-16s %.2f", cur.getInfo().getTagNumber(), cur.getInfo().getName(), cur.getInfo().getOriginalLocation(), cur.getInfo().getCurrentLocation(), cur.getInfo().getPrice()));
            }
            cur = cur.getNext();
        }
        cur = head;
        if(!headerPrinted)
            throw new IllegalArgumentException("Object with RFID tag number could not be found.");
    }
    /**
     * Removes all the purchased items.removes items marked as out.
     * @throws EmptyListException 
     *      if the list is empty then there is nothing to remove or print
     */
    public void removeAllPurchased() throws EmptyListException
    {
        if(head == null)
            throw new EmptyListException("List is empty.");
        boolean headerPrinted = false;
        cur = head;
        while(cur != null)
        {
            if(cur.getInfo().getCurrentLocation().equals("out"))
            {
                if(!headerPrinted)
                {
                    System.out.println(String.format("%-18s %-18s %-18s %-16s %-5s", "RFID Tag #", "Item Name", "Original Location", "Current Location", "Price"));
                    System.out.println("-------------------------------------------------------------------------------");
                    headerPrinted = true;
                }
                System.out.println(String.format("%-18s %-18s %-18s %-16s $%.2f", cur.getInfo().getTagNumber(), cur.getInfo().getName(), cur.getInfo().getOriginalLocation(), cur.getInfo().getCurrentLocation(), cur.getInfo().getPrice()));
                removeNode(cur);
            }
            else
                cur = cur.getNext();
        }
        cur = head;
    }
    /**
     * Takes all the items that are not in their original location and returns
     * them to their original location. items that are out or in a cart are
     * not affected.
     * @throws EmptyListException 
     *      if the list is empty then there is nothing to me moved or printed
     */
    public void cleanStore() throws EmptyListException
    {
        if(head == null)
            throw new EmptyListException("List is empty");
        boolean headerPrinted = false;
        cur = head;
        while(cur != null)
        {
            if(!cur.getInfo().getCurrentLocation().equals(cur.getInfo().getOriginalLocation()) && !cur.getInfo().getCurrentLocation().equals("out") && cur.getInfo().getCurrentLocation().charAt(0) == 's')
            {
                if(!headerPrinted)
                {
                    System.out.println(String.format("%-18s %-18s %-18s %-16s %-5s", "RFID Tag #", "Item Name", "Original Location", "Current Location", "Price"));
                    System.out.println("-------------------------------------------------------------------------------");
                    headerPrinted = true;
                }
                System.out.println(String.format("%-18s %-18s %-18s %-16s $%.2f", cur.getInfo().getTagNumber(), cur.getInfo().getName(), cur.getInfo().getOriginalLocation(), cur.getInfo().getCurrentLocation(), cur.getInfo().getPrice()));
                cur.getInfo().setCurrentLocation(cur.getInfo().getOriginalLocation());
            }
            cur = cur.getNext();
        }
        cur = head;
    }
    /**
     * Takes all the items in the specified cart and marks them as out.
     * @param cartNumber
     * @return total: the sum of the price of all the items in the specified cart
     * @throws IllegalArgumentException
     *      if the specified cart number is not found or is invalid
     * @throws EmptyListException 
     *      if the list is empty then there can't be any items in carts
     */
    public double checkOut(String cartNumber) throws IllegalArgumentException, EmptyListException
    {
        if(head == null)
            throw new EmptyListException("This list is empty. Nothing can be checked out.");
        boolean cartFound = false;
        boolean headerPrinted = false;
        double total = 0.0;
        if(cartNumber.charAt(0) != 'c')
                throw new IllegalArgumentException("The cart number does not meet any cart number specifications.");
        for(int i = 1; i < 4; i++)
            if(cartNumber.charAt(i) < 48 || cartNumber.charAt(i) > 57)
                throw new IllegalArgumentException("The cart number does not meet any cart number specifications.");
        cur = head;
        while(cur != null)
        {
            if(cur.getInfo().getCurrentLocation().equals(cartNumber))
            {
                cartFound = true;
                total += cur.getInfo().getPrice();
                cur.getInfo().setCurrentLocation("out");
                if(!headerPrinted)
                {
                    System.out.println(String.format("%-18s %-18s %-18s %-16s %-5s", "RFID Tag #", "Item Name", "Original Location", "Current Location", "Price"));
                    System.out.println("-------------------------------------------------------------------------------");
                    headerPrinted = true;
                }
                System.out.println(String.format("%-18s %-18s %-18s %-16s %.2f", cur.getInfo().getTagNumber(), cur.getInfo().getName(), cur.getInfo().getOriginalLocation(), cur.getInfo().getCurrentLocation(), cur.getInfo().getPrice()));
            }
            cur = cur.getNext();
        }
        cur = head;
        if(!cartFound)
            throw new IllegalArgumentException("No cart with such number was found.");
        return total;
    }
    /**
     * Helper method that takes a string and converts it to a long. assuming
     * that the string is in the form of a hexadecimal number
     * @param hexVal
     * @return answer
     *      long value holding the decimal value of the hexadecimal number given
     */
    private long hexToLong(String hexVal)
    {
        long answer = 0;
        int n = 0;
        for(int i = (hexVal.length()-1); i >= 0; i--)
        {
            if(hexVal.charAt(i) < 58)
                answer += (hexVal.charAt(i)-48) * Math.pow(16, n++);
            else if(hexVal.charAt(i) < 71)
                answer += (hexVal.charAt(i)-55) * Math.pow(16, n++);
            else if(hexVal.charAt(i) < 103)
                answer += (hexVal.charAt(i)-87) * Math.pow(16, n++);
        }
        return answer;
    }
    /**
     * Helper method that removes the specified node
     * @param node 
     */
    private void removeNode(ItemInfoNode node)
    {
        if(node == head)
        {
            head = node.getNext();
            head.setPrev(null);
            cur = head;
        }
        else if(node == tail)
        {
            tail = node.getPrev();
            tail.setNext(null);
            cur = null;
        }
        else
        {
            cur = node.getPrev();
            cur.setNext(node.getNext());
            cur.getNext().setPrev(cur);
            cur = cur.getNext();
        }
    }
}
