
import java.util.Scanner;

/**
 * @author Bryant Gonzaga
 * @version 1.0
 *  This class creates a list of type ItemList.
 * In this list different objects are stored with
 * information specified in the ItemInfo class.
 */
public class DepartmentStore
{
    /**
     * Index for array that holds the information of a given item.
     * the index is named with the information it holds
     */
    private static final int NAME = 0;
    /**
     * Index for array that holds the information of a given item.
     * the index is named with the information it holds
     */
    private static final int RFID = 1;
    /**
     * Index for array that holds the information of a given item.
     * the index is named with the information it holds
     */
    private static final int OG_LOC = 2;
    /**
     * Index for array that holds the information of a given item.
     * the index is named with the information it holds
     */
    private static final int CU_LOC = 3;
    /**
     * Main method that controls printing a menu for the user to control
     * an itemList. You can add items and move them around by using the
     * options provided. also handles exceptions when users give invalid
     * input.
     * @param args 
     */
    @SuppressWarnings("ConvertToStringSwitch")
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        ItemList itemList = new ItemList();
        String itemInfo[] = new String[4];
        String choice = "z";
        double price;
        while(!choice.equals("Q") && !choice.equals("q"))
        {
            System.out.println("I: Insert an item to your list.");
            System.out.println("M: Move an item in the store.");
            System.out.println("R: Print by RFID tag Number");
            System.out.println("L: List by location.");
            System.out.println("U: Update inventory.");
            System.out.println("P: Print all items.");
            System.out.println("C: Clean Store.");
            System.out.println("O: Checkout.");
            System.out.println("Q: Quit.");
            System.out.print("Choice: ");
            choice = input.nextLine();
            System.out.println();
            if(choice.equals("I") || choice.equals("i"))
            {
                System.out.print("Name of Item: ");
                itemInfo[NAME] = input.nextLine();
                System.out.print("RFID Tag Number: ");
                itemInfo[RFID] = input.nextLine();
                System.out.print("Original Location: ");
                itemInfo[OG_LOC] = input.nextLine();
                System.out.print("Price of Item: ");
                price = input.nextDouble();
                input.nextLine();
                try
                {
                    itemList.insertInfo(itemInfo[NAME], itemInfo[RFID], price, itemInfo[OG_LOC]);
                    System.out.println("Item added.");
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println(e.getMessage() + "\nItem not added.");
                }
            }
            else if(choice.equals("M") || choice.equals("m"))
            {
                System.out.print("RFID Tag Number: ");
                itemInfo[RFID] = input.nextLine();
                System.out.print("Current Location: ");
                itemInfo[OG_LOC] = input.nextLine();
                System.out.print("New Location: ");
                itemInfo[CU_LOC] = input.nextLine();
                try
                {
                    if(itemList.moveItem(itemInfo[RFID], itemInfo[OG_LOC], itemInfo[CU_LOC]))
                        System.out.println("Item moved.");
                    else
                        System.out.println("Item could not be located.");
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println(e.getMessage() + "\nItem not moved");
                }
            }
            else if(choice.equals("R") || choice.equals("r"))
            {
                System.out.print("RFID Tag Number:");
                itemInfo[RFID] = input.nextLine();
                try
                {
                    itemList.printByRfid(itemInfo[RFID]);
                }
                catch(EmptyListException | IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            else if(choice.equals("L") || choice.equals("l"))
            {
                System.out.print("Location of Item(s)");
                itemInfo[CU_LOC] = input.nextLine();
                try
                {
                    itemList.printByLocation(itemInfo[CU_LOC]);
                }
                catch(EmptyListException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            else if(choice.equals("U") || choice.equals("u"))
            {
                try
                {
                    itemList.removeAllPurchased();
                }
                catch(EmptyListException e)
                {
                    System.out.println(e.getMessage() + "\n Nothing could be removed.");
                }
            }
            else if(choice.equals("P") || choice.equals("p"))
            {
                try
                {
                    itemList.printAll();
                }
                catch(EmptyListException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            else if(choice.equals("C") || choice.equals("c"))
            {
                try
                {
                    itemList.cleanStore();
                }
                catch(EmptyListException e)
                {
                    System.out.println(e.getMessage() + "/nThere is nothing to clean");
                }
            }
            else if(choice.equals("O") || choice.equals("o"))
            {
                System.out.print("Cart number: ");
                itemInfo[CU_LOC] = input.nextLine();
                try
                {
                    System.out.println("Total Cost: " + itemList.checkOut(itemInfo[CU_LOC]));
                }
                catch(IllegalArgumentException | EmptyListException e)
                {
                    System.out.println(e.getMessage() + "Checkout not possible.");
                }
            }
            else if(choice.equals("Q") || choice.equals("q"))
            {
                System.out.println("Are you sure you want to quit?");
                System.out.println("Q: Quit\nC: Continue\nChoice:");
                choice = input.nextLine();
            }
            else
                System.out.println("Invalid entry!");
            System.out.println();
        }
    }
}