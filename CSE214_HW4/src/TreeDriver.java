
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      Class that controls a tree that is created from
 * a text file provided by the user. Displays menu to access
 * the tree's potential.
 */
public class TreeDriver
{
    /**
     * Constant to hold array place
     */
    public static final int LABEL = 0;
    /**
     * Constant to hold array place
     */
    public static final int PARENT = 1;
    /**
     * Constant to hold array place
     */
    public static final int PROMPT = 2;
    /**
     * Constant to hold array place
     */
    public static final int MESSAGE = 3;
    /**
     * Displays menu that helps users load a file to create
     * a tree.
     * @param args 
     */
    @SuppressWarnings("ConvertToStringSwitch")
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String[] nodeInfo = new String[4];
        Tree tree = new Tree();
        String choice = "";
        Scanner fileReader;
        String fileName;
        int children;
        File file;
        int temp;
       
        while(!choice.equals("Q"))
        {
            System.out.println("------------Main Menu------------");
            System.out.println("T: Traverse the Tree in Pre-order");
            System.out.println("L: Load File and Build Tree.");
            System.out.println("H: Start Help Session.");
            System.out.println("Q: Quit");
            System.out.print("Choice: ");
            choice = input.nextLine();
            choice = choice.toUpperCase();

            if(choice.equals("T"))
            {
                if(tree.isEmpty())
                    System.out.println("The tree is empty or was not yet created.");
                else
                    tree.preorder();
            }
            else if(choice.equals("L"))
            {
                System.out.println("What is the name of the file that contains the tree information?");
                System.out.print("File Name: ");
                fileName = input.nextLine();
                file = new File(fileName);
                try
                {
                    fileReader = new Scanner(file);
                    nodeInfo[LABEL] = fileReader.nextLine();
                    nodeInfo[LABEL] = nodeInfo[LABEL].trim();
                    nodeInfo[PROMPT] = fileReader.nextLine();
                    nodeInfo[PROMPT] = nodeInfo[PROMPT].trim();
                    nodeInfo[MESSAGE] = fileReader.nextLine();
                    nodeInfo[MESSAGE] = nodeInfo[MESSAGE].trim();
                    tree.addNode(nodeInfo[LABEL], nodeInfo[PROMPT], nodeInfo[MESSAGE], "root");
                    
                    while(fileReader.hasNextLine())
                    {
                        nodeInfo[PARENT] = fileReader.nextLine();
                        nodeInfo[PARENT] = nodeInfo[PARENT].trim();
                        temp = nodeInfo[PARENT].indexOf(" ");
                        children = Integer.parseInt(nodeInfo[PARENT].substring(temp+1));
                        nodeInfo[PARENT] = nodeInfo[PARENT].substring(0, temp);
                 
                        while(children > 0)
                        {
                            nodeInfo[LABEL] = fileReader.nextLine();
                            nodeInfo[LABEL] = nodeInfo[LABEL].trim();
                            nodeInfo[PROMPT] = fileReader.nextLine();
                            nodeInfo[PROMPT] = nodeInfo[PROMPT].trim();
                            nodeInfo[MESSAGE] = fileReader.nextLine();
                            nodeInfo[MESSAGE] = nodeInfo[MESSAGE].trim();
                            
                            tree.addNode(nodeInfo[LABEL], nodeInfo[PROMPT], nodeInfo[MESSAGE], nodeInfo[PARENT]);
                            
                            children--;
                        }
                    }
                }
                catch(FileNotFoundException | NumberFormatException e)
                {
                    System.out.println("Something went wrong with your inputs.");
                }
            }
            else if(choice.equals("H"))
            {
                if(tree.isEmpty())
                    System.out.println("Tree is empty. Please load a tree first");
                else
                    tree.beginSession();
            }
            else if(choice.equals("Q"))
            {
                System.out.println("Are you sure you want to quite?");
                System.out.println("Q: Quit\nC: Continue");
                System.out.print("Choice: ");
                choice = input.nextLine();
                choice = choice.toUpperCase();
            }
            else
            {
                System.out.println("Invalid entry please try again.");
            }
        }
    }
}
