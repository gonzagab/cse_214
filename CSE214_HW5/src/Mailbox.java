
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      Class that implements a mailbox for email's.
 * holds methods to access folders and email's.
 */
public class Mailbox implements Serializable
{
    /**
     * Inbox folder that holds emails
     */
    private Folder inbox;
    /**
     * Trash folder
     */
    private Folder trash;
    /**
     * List of added folders
     */
    private ArrayList<Folder> folders;
    /**
     * instantiation of mailbox
     */
    public static Mailbox mailbox;
    /**
     *      Main method that implements email services
     * @param args 
     */
    public static void main(String[] args)
    {
        mailbox = new Mailbox();
        mailbox.inbox = new Folder();
        mailbox.trash = new Folder();
        mailbox.folders = new ArrayList<>();
     
        SimpleDateFormat timeStamp;
        timeStamp = new SimpleDateFormat();
        timeStamp.applyPattern("hh:mma MM/dd/yyyy");
        
        Scanner input = new Scanner(System.in);
        String choice = "";
        String folderName;
        int index;
        
        try
        {
            FileInputStream fileInput = new FileInputStream("mailbox.obj");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            
            mailbox = (Mailbox)objectInput.readObject();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
   
        while(!choice.equals("Q"))
        {
            System.out.println("Mailbox:");
            System.out.println("--------");
            System.out.println("Inbox");
            System.out.println("Trash");
            for(Folder folder: mailbox.folders)
                System.out.println(folder.getName());
            System.out.println("\nA: Add Folder");
            System.out.println("R: Remove Folder");
            System.out.println("C: Compose Email");
            System.out.println("F: Open Folder");
            System.out.println("I: Open Inbox");
            System.out.println("T: Open Trash");
            System.out.println("E: Empty Trash");
            System.out.println("Q: Quit");
            System.out.print("Choice: ");
            choice = input.nextLine();
            choice = choice.toUpperCase();
            System.out.println();
            if(choice.equals("A"))
            {
                System.out.print("Name of Folder: ");
                folderName = input.nextLine();
                mailbox.addFolder(new Folder(folderName));
                System.out.println("Folder was added.\n");
            }
            else if(choice.equals("R"))
            {
                System.out.print("Name of Folder: ");
                folderName = input.nextLine();
                mailbox.deleteFolder(folderName);
                System.out.println();
            }
            else if(choice.equals("C"))
                mailbox.composeEmail();
            else if(choice.equals("F"))
            {
                index = 1;
                System.out.print("Name of Folder: ");
                folderName = input.nextLine();
                System.out.println();
                Folder temp = mailbox.getFolder(folderName);
                if(temp != null)
                {
                    while(!choice.equals("R"))
                    {
                        if(!temp.getEmails().isEmpty())
                        {
                            System.out.println("Index |        Time      | Subject");
                            System.out.print("----------------------------------");
                            for(Email mail : temp.getEmails())
                                System.out.printf("\n%d | %s | %s", index++, timeStamp.format(mail.getTimestamp().getTime()), mail.getSubject());
                            System.out.println("\nM: Move email");
                            System.out.println("D: Delete email");
                            System.out.println("V: View email contents");
                            System.out.println("SA: Sort by subject line in ascending order");
                            System.out.println("SD: Sort by subject line in descending order");
                            System.out.println("DA: Sort by date in ascending order");
                            System.out.println("DD: Sort by date in descending order");                    }
                        else
                            System.out.println("Folder is empty\n");
                        System.out.println("R: Return to mailbox");
                        System.out.print("Choice: ");
                        choice = input.nextLine();
                        choice = choice.toUpperCase();
                        System.out.println();
                         if(choice.equals("M") && !temp.getEmails().isEmpty())
                        {
                            System.out.print("Index of Email: ");
                            index = input.nextInt();
                            input.nextLine();
                            System.out.println("Folders");
                            System.out.println("-------");
                            System.out.println("Inbox");
                            System.out.println("Trash");
                            for(int i = 0; i < mailbox.folders.size(); i++)
                                System.out.println(mailbox.folders.get(i).getName());
                            System.out.print("Name of Folder: ");
                            folderName = input.nextLine();
                            try
                            {
                                mailbox.moveEmail(temp.getEmails().get(index-1),mailbox.getFolder(folderName));
                                temp.removeEmail(index-1);
                                System.out.println("Email deleted.\n");
                            }
                            catch(Exception e)
                            {
                                System.out.println("Could not process request: " + e.getMessage() + "\n");
                            }
                        }
                        else if(choice.equals("D") && !temp.getEmails().isEmpty())
                        {
                            System.out.print("Index of Email: ");
                            index = input.nextInt();
                            input.nextLine();
                            try
                            {
                                mailbox.deleteEmail(temp.getEmails().get(index-1));
                                temp.removeEmail(index-1);
                            }
                            catch(Exception e)
                            {System.out.println("Request could not be processed: " + e.getMessage() + "\n");}
                        }
                        else if(choice.equals("V") && !temp.getEmails().isEmpty())
                        {
                            System.out.print("Index of Email: ");
                            index = input.nextInt();
                            input.nextLine();
                            try
                            {
                                System.out.println("\nTo: " + temp.getEmails().get(index-1).getTo());
                                System.out.println("CC: " + temp.getEmails().get(index-1).getCc());
                                System.out.println("BCC: " + temp.getEmails().get(index-1).getBcc());
                                System.out.println("Subject: " + temp.getEmails().get(index-1).getSubject());
                                System.out.println(temp.getEmails().get(index-1).getBody());
                                System.out.println();
                            }
                            catch(Exception e)
                            {
                                System.out.println("Request could not be processed: " + e.getMessage() + "\n");
                            }
                        }
                            else if(choice.equals("SA") && !temp.getEmails().isEmpty())
                                temp.sortBySubjectAscending();                    
                            else if(choice.equals("SD") && !temp.getEmails().isEmpty())
                                temp.sortBySubjectDescending();
                            else if(choice.equals("DA") && !temp.getEmails().isEmpty())
                                temp.sortByDateAscending();
                            else if(choice.equals("DD") && !temp.getEmails().isEmpty())
                                temp.sortByDateDescending();
                            else
                                System.out.println("Invalid entry. Try again/n");
                        }
                }
                else
                    System.out.println("Folder could not be found.");
            }
            else if(choice.equals("I"))
            {
                while(!choice.equals("R"))
                {
                    System.out.println("Inbox");
                    System.out.println("-----");
                    if(!mailbox.inbox.getEmails().isEmpty())
                    {
                        index = 1;
                        System.out.println("Index |        Time      | Subject");
                        System.out.print("----------------------------------");
                        for(Email mail : mailbox.inbox.getEmails())
                            System.out.printf("\n%3d | %s | %s", index++, timeStamp.format(mail.getTimestamp().getTime()), mail.getSubject());
                        System.out.println("\nM: Move email");
                        System.out.println("D: Delete email");
                        System.out.println("V: View email contents");
                        System.out.println("SA: Sort by subject line in ascending order");
                        System.out.println("SD: Sort by subject line in descending order");
                        System.out.println("DA: Sort by date in ascending order");
                        System.out.println("DD: Sort by date in descending order");
                    }
                    else
                        System.out.println("Inbox is empty.");                    
                    System.out.println("R: Return to mailbox");
                    System.out.print("Choice: ");
                    choice = input.nextLine();
                    choice = choice.toUpperCase();
                    System.out.println();
                    if(choice.equals("M") && !mailbox.inbox.getEmails().isEmpty())
                    {
                        System.out.print("Index of Email: ");
                        index = input.nextInt();
                        input.nextLine();
                        System.out.println("Folders");
                        System.out.println("-------");
                        System.out.println("Inbox");
                        System.out.println("Trash");
                        for(int i = 0; i < mailbox.folders.size(); i++)
                            System.out.println(mailbox.folders.get(i).getName());
                        System.out.print("Name of Folder: ");
                        folderName = input.nextLine();
                        try
                        {
                            mailbox.moveEmail(mailbox.inbox.getEmails().get(index-1),mailbox.getFolder(folderName));
                            mailbox.inbox.removeEmail(index-1);
                        }
                        catch(Exception e)
                        {System.out.println("Request could not be processed: " + e.getMessage() + "\n");}
                    }
                    else if(choice.equals("D") && !mailbox.inbox.getEmails().isEmpty())
                    {
                        System.out.print("Index of Email: ");
                        index = input.nextInt();
                        input.nextLine();
                        try
                        {
                            mailbox.deleteEmail(mailbox.inbox.getEmails().get(index-1));
                            mailbox.inbox.removeEmail(index-1);
                            System.out.println("Email deleted.\n");
                        }
                        catch(Exception e)
                        {System.out.println("Request could not be processed: " + e.getMessage() + "\n");}
                    }
                    else if(choice.equals("V") && !mailbox.inbox.getEmails().isEmpty())
                    {
                        System.out.print("Index of Email: ");
                        index = input.nextInt();
                        input.nextLine();
                        try
                        {
                            System.out.println("\nTo: " + mailbox.inbox.getEmails().get(index-1).getTo());
                            System.out.println("CC: " + mailbox.inbox.getEmails().get(index-1).getCc());
                            System.out.println("BCC: " + mailbox.inbox.getEmails().get(index-1).getBcc());
                            System.out.println("Subject: " + mailbox.inbox.getEmails().get(index-1).getSubject());
                            System.out.println(mailbox.inbox.getEmails().get(index-1).getBody());
                            System.out.println();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Couldnt process request: " + e.getMessage() + "\n");
                        }
                    }
                    else if(choice.equals("SA") && !mailbox.inbox.getEmails().isEmpty())
                        mailbox.inbox.sortBySubjectAscending();                    
                    else if(choice.equals("SD") && !mailbox.inbox.getEmails().isEmpty())
                        mailbox.inbox.sortBySubjectDescending();
                    else if(choice.equals("DA") && !mailbox.inbox.getEmails().isEmpty())
                        mailbox.inbox.sortByDateAscending();
                    else if(choice.equals("DD") && !mailbox.inbox.getEmails().isEmpty())
                        mailbox.inbox.sortByDateDescending();
                    else
                        System.out.println("Invalid entry. Try again\n");
                }
            }
            else if(choice.equals("T"))
            {
                while(!choice.equals("R"))
                {
                    System.out.println("Trash");
                    System.out.println("-----");
                    if(!mailbox.trash.getEmails().isEmpty())
                    {
                        index = 1;
                        System.out.println("Index |        Time      | Subject");
                        System.out.print("----------------------------------");
                        for(Email mail : mailbox.trash.getEmails())
                            System.out.printf("\n%3d | %s | %s", index++, timeStamp.format(mail.getTimestamp().getTime()), mail.getSubject());
                        System.out.println("\nM: Move email");
                        System.out.println("D: Delete email");
                        System.out.println("V: View email contents");
                        System.out.println("SA: Sort by subject line in ascending order");
                        System.out.println("SD: Sort by subject line in descending order");
                        System.out.println("DA: Sort by date in ascending order");
                        System.out.println("DD: Sort by date in descending order");
                    }
                    else
                        System.out.println("Trash is empty.");                    
                    System.out.println("R: Return to mailbox");
                    System.out.print("Choice: ");
                    choice = input.nextLine();
                    choice = choice.toUpperCase();
                    System.out.println();
                    if(choice.equals("M") && !mailbox.trash.getEmails().isEmpty())
                    {
                        System.out.print("Index of Email: ");
                        index = input.nextInt();
                        input.nextLine();
                        System.out.println("Folders");
                        System.out.println("-------");
                        System.out.println("Inbox");
                        System.out.println("Trash");
                        for(int i = 0; i < mailbox.folders.size(); i++)
                            System.out.println(mailbox.folders.get(i).getName());
                        System.out.print("Name of Folder: ");
                        folderName = input.nextLine();
                        try
                        {
                            mailbox.moveEmail(mailbox.trash.getEmails().get(index-1),mailbox.getFolder(folderName));
                            mailbox.trash.removeEmail(index-1);
                        }
                        catch(Exception e)
                        {System.out.println("Request could not be processed: " + e.getMessage() + "\n");}
                    }
                    else if(choice.equals("D") && !mailbox.trash.getEmails().isEmpty())
                    {
                        System.out.print("Index of Email: ");
                        index = input.nextInt();
                        input.nextLine();
                        try
                        {
                            mailbox.deleteEmail(mailbox.trash.getEmails().get(index-1));
                            mailbox.trash.removeEmail(index-1);
                            System.out.println("Email deleted.\n");
                        }
                        catch(Exception e)
                        {System.out.println("Request could not be processed: " + e.getMessage() + "\n");}
                    }
                    else if(choice.equals("V") && !mailbox.trash.getEmails().isEmpty())
                    {
                        System.out.print("Index of Email: ");
                        index = input.nextInt();
                        input.nextLine();
                        try
                        {
                            System.out.println("\nTo: " + mailbox.trash.getEmails().get(index-1).getTo());
                            System.out.println("CC: " + mailbox.trash.getEmails().get(index-1).getCc());
                            System.out.println("BCC: " + mailbox.trash.getEmails().get(index-1).getBcc());
                            System.out.println("Subject: " + mailbox.trash.getEmails().get(index-1).getSubject());
                            System.out.println(mailbox.trash.getEmails().get(index-1).getBody());
                            System.out.println();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Couldnt process request: " + e.getMessage() + "\n");
                        }
                    }
                    else if(choice.equals("SA") && !mailbox.trash.getEmails().isEmpty())
                        mailbox.trash.sortBySubjectAscending();                    
                    else if(choice.equals("SD") && !mailbox.trash.getEmails().isEmpty())
                        mailbox.trash.sortBySubjectDescending();
                    else if(choice.equals("DA") && !mailbox.trash.getEmails().isEmpty())
                        mailbox.trash.sortByDateAscending();
                    else if(choice.equals("DD") && !mailbox.trash.getEmails().isEmpty())
                        mailbox.trash.sortByDateDescending();
                    else
                        System.out.println("Invalid entry. Try again\n");
                }
            }
            else if(choice.equals("E"))
            {
                mailbox.clearTrash();
                System.out.println("Trash cleared\n");
            }
            else if(choice.equals("Q"))
            {}
            else
                System.out.println("Invald entry please try again.\n");
        }
        
        try
        {
            FileOutputStream fileOut = new FileOutputStream("mailbox.obj");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
            objectOut.writeObject(mailbox);
            System.out.println("File Written");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     *      Adds folder to list of folder
     * @param folder 
     */
    public void addFolder(Folder folder)
    {
        mailbox.folders.add(folder);
    }
    /**
     *      deletes folder
     * @param name 
     */
    public void deleteFolder(String name)
    {
        Folder temp = getFolder(name);
        if(temp == null)
            System.out.println("Your folder name could not be found");
        if(temp == inbox || temp == trash)
            System.out.println("You can NOT remove trash or inbox");
        else
        {
            folders.remove(temp);
            System.out.println("Folder was removed");
        }
    }
    /**
     * Makes an email with all the info
     */
    public void composeEmail()
    {
        Scanner in = new Scanner(System.in);
        String to;
        String cc;
        String bcc;
        String subject;
        String body;
        
        System.out.print("To: ");
        to = in.nextLine();
        System.out.print("CC: ");
        cc = in.nextLine();
        System.out.print("BCC: ");
        bcc = in.nextLine();
        System.out.print("Subject: ");
        subject = in.nextLine();
        System.out.print("Body: ");
        body = in.nextLine();
        
        mailbox.inbox.addEmail(new Email(to, cc, bcc, subject, body));
        System.out.println("\nEmail composed and added to inbox.\n");
    }
    /**
     *      deletes the email passed. by moving it to the trash
     * @param email 
     */
    public void deleteEmail(Email email)
    {
        moveEmail(email, trash);
    }
    /**
     * cleans the trash out.
     */
    public void clearTrash()
    {
        trash = new Folder();
    }
    /**
     * Moves emial to desired destination
     * @param email
     * @param target 
     */
    public void moveEmail(Email email, Folder target)
    {
        if(target == null)
        {
            System.out.println("Couldnt find folder.");
            return;
        }
        target.addEmail(email);
    }
    /**
     * @param name
     * @return the folder with the given name
     */
    public Folder getFolder(String name)
    {
        if(name.equals("Trash"))
            return this.trash;
        if(name.equals("Inbox"))
            return this.inbox;
        for(Folder folder : folders)
            if(folder.getName().equals(name))
                return folder;
        return null;
    }
}
