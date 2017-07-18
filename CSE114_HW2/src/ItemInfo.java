/**
 *  Class that holds the info of an item and also provides
 * methods for modifying the information of the item.
 * @author Bryant Gonzaga
 * @version 1.0
 */
public class ItemInfo
{
    /**
     * Describes the item
     */
    private String name;
    /**
     * Stores the price of the item
     */
    private double price;
    /**
     * holds RFID tag number in hex
     */
    private String tagNumber;
    /**
     * Holds the original position of item
     */
    private String originalLocation;
    /**
     * Holds the current location of item
     */
    private String currentLocation;
    /**
     * Constructor - Creates an object
     */
    public ItemInfo()
    {}
    /**
     * Creates an object of type ItemInfo with specified parameters.
     * @param name
     * @param rfidTag
     * @param price
     * @param position 
     */
    public ItemInfo(String name, String rfidTag, double price, String position)
    {
        setName(name);
        setTagNumber(rfidTag);
        setPrice(price);
        setOriginalLocation(position);
    }
    /**
     * @return
     *      The name of the product
     */
    public String getName()
    {
        return name;
    }
    /**
     * @return
     *      The price of the item.
     */
    public double getPrice()
    {
        return price;
    }
    /**
     * @return
     *      The RFID tag number.
     */
    public String getTagNumber()
    {
        return tagNumber;
    }
    /**
     * @return
     *      Original location of item.
     */
    public String getOriginalLocation()
    {
        return originalLocation;
    }
    /**
     * @return
     *      Current location of item.
     */
    public String getCurrentLocation()
    {
        return currentLocation;
    }
    /**
     *  Sets the name of the item.
     * @param name 
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     *  Sets the price of the item.
     * @param price
     * @throws IllegalArgumentException 
     *      If price is less that 0.
     */
    public void setPrice(double price) throws IllegalArgumentException
    {
        if(price < 0)
            throw new IllegalArgumentException("The price must be a positive number");
        else
            this.price = price;
    }
    /**
     *  Set the RFID tag number of the item
     * @param tagNumber    
     *      Must be a 9 character String representing a Hexadecimal number.
     * @IllegalArgumentException
     *      Is thrown when tagNumber does not meet the requirments.
     */
    public void setTagNumber(String tagNumber) throws IllegalArgumentException
    {
        boolean tagIsGood = true;
        
        if(tagNumber.length() != 9)
            tagIsGood = false;
        else
            for(int i = 0; i < tagNumber.length(); i++)
                if(tagNumber.charAt(i) < 48 || (tagNumber.charAt(i) > 57 && tagNumber.charAt(i) < 65) || (tagNumber.charAt(i) > 70 && tagNumber.charAt(i) < 97) || tagNumber.charAt(i) > 102)
                    tagIsGood = false;
        if(tagIsGood)
            this.tagNumber = tagNumber;
        else
            throw new IllegalArgumentException("The rfid tag number is improper.");
    }
    /**
     *  Sets the original location of the item.
     * @param originalLocation
     *      Must start with 's' followed by 5 digits
     * @throws IllegalArgumentException 
     *      If the parameter doesn't meet the specifications.
     */
    public void setOriginalLocation(String originalLocation) throws IllegalArgumentException
    {
        originalLocation = originalLocation.toLowerCase();
        if(originalLocation.charAt(0) != 's' || originalLocation.length() != 6)
            throw new IllegalArgumentException("The shelf designation does not match a proper code.");
        else
            for(int i = 1; i < 6; i++)
                if(originalLocation.charAt(i) < 48 || originalLocation.charAt(i) > 57)
                    throw new IllegalArgumentException("The shelf designation does not match a proper code.");
        this.originalLocation = originalLocation;
        setCurrentLocation(originalLocation);
    }
    /**
     *  Sets the current location to a the parameter passed.
     * @param currentLocation
     *      Must be a shelf location or a cart location or "out".
     * @throws IllegalArgumentException 
     *      If current location is not a cart location or a shelf location.
     */
    public void setCurrentLocation(String currentLocation) throws IllegalArgumentException
    {
        currentLocation = currentLocation.toLowerCase();
        if(currentLocation.charAt(0) != 's' && currentLocation.charAt(0) != 'c' && !currentLocation.equals("out"))
            throw new IllegalArgumentException("The location does not meet any location specifications.");
        if(currentLocation.charAt(0) == 's' && currentLocation.length() != 6)
            throw new IllegalArgumentException("The location does not meet any location specifications.");            
        if(currentLocation.charAt(0) == 'c' && currentLocation.length() != 4)
            throw new IllegalArgumentException("The location does not meet any location specifications.");
        if(currentLocation.charAt(0) == 's')
            for(int i = 1; i < 6; i++)
                if(currentLocation.charAt(i) < 48 || currentLocation.charAt(i) > 57)
                    throw new IllegalArgumentException("The location does not meet any location specifications.");
        if(currentLocation.charAt(0) == 'c')
            for(int i = 1; i < 4; i++)
                if(currentLocation.charAt(i) < 48 || currentLocation.charAt(i) > 57)
                    throw new IllegalArgumentException("The location does not meet any location specifications.");
        this.currentLocation = currentLocation;
    }
}