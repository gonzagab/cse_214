import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      Class that folds the information for a folder.
 * Has an Array List of email's.
 */
public class Folder implements Serializable
{
    /**
     * List of email's 
     */
    private ArrayList<Email> emails;
    /**
     * name of folder
     */
    private String name;
    /**
     * String that holds the method of sorting
     */
    private String currentSortingMethod;
    /**
     * Constructor that instantiates the class
     */
    public Folder()
    {
        this("UNKNOWN");
    }
    /**
     * Constructor that sets the name and initializes the data fields
     * @param name 
     */
    public Folder(String name)
    {
        this.name = name;
        currentSortingMethod = "DateDescending";
        emails = new ArrayList<>();
    }
    /**
     *      Adds the given Email to the folder according
     * to the Sorting Method.
     * @param email
     */
    public void addEmail(Email email)
    {
        int  index= 0;
        if(emails.isEmpty())
            emails.add(email);
        else
        {
            if(currentSortingMethod.equals("DateAscending"))
            {
                while(emails.size() > index && emails.get(index).getTimestamp().compareTo(email.getTimestamp()) < 0)
                    index++;
                emails.add(index, email);
            }
            if(currentSortingMethod.equals("DateDescending"))
            {
                while(emails.size() > index && emails.get(index).getTimestamp().compareTo(email.getTimestamp()) > 0)
                    index++;
                emails.add(index, email);
            }
            if(currentSortingMethod.equals("SubjectAscending"))
            {
                while(emails.size() > index && emails.get(index).getSubject().compareToIgnoreCase(email.getSubject()) < 0)
                    index++;
                emails.add(index, email);
            }
            if(currentSortingMethod.equals("SubjectDescending"))
            {
                while(emails.size() > index && emails.get(index).getSubject().compareToIgnoreCase(email.getSubject()) > 0)
                    index++;
                emails.add(index, email);
            }
        }
    }
    /**
     *      Removes the email at the index given.
     * @param index
     * @return the removed email
     */
    public Email removeEmail(int index)
    {
        return emails.remove(index);
    }
    /**
     * Sorts email's by Ascending Subject
     */
    public void sortBySubjectAscending()
    {
        if(currentSortingMethod.equals("SubjectAscending"))
            return;
        currentSortingMethod = "SubjectAscending";
        int smallest;
        Email temp;
        for(int j = 0; j < emails.size(); j++)
        {
            smallest = 0;
            for(int i = 1; i < (emails.size()-j); i++)
            {
                if(emails.get(smallest).getSubject().compareToIgnoreCase(emails.get(i).getSubject()) > 0)
                    smallest = i;
            }
            temp = emails.remove(smallest);
            emails.add(temp);
        }
    }
    /**
     * Sorts email's by Descending Subject
     */
    public void sortBySubjectDescending()
    {
        if(currentSortingMethod.equals("SubjectDescending"))
            return;
        currentSortingMethod = "SubjectDescending";
        int largest;
        Email temp;
        for(int j = 0; j < emails.size(); j++)
        {
            largest = 0;
            for(int i = 1; i < (emails.size()-j); i++)
            {
                if(emails.get(largest).getSubject().compareToIgnoreCase(emails.get(i).getSubject()) < 0)
                    largest = i;
            }
            temp = emails.remove(largest);
            emails.add(temp);
        }
    }
    /**
     * Sorts email's by Ascending Date
     */
    public void sortByDateAscending()
    {
        if(currentSortingMethod.equals("DateAscending"))
            return;
        currentSortingMethod = "DateAscending";
        int smallest;
        Email temp;
        for(int j = 0; j < emails.size(); j++)
        {
            smallest = 0;
            for(int i = 1; i < (emails.size()-j); i++)
            {
                if(emails.get(smallest).getTimestamp().compareTo(emails.get(i).getTimestamp()) > 0)
                    smallest = i;
            }
            temp = emails.remove(smallest);
            emails.add(temp);
        }
    }
    /**
     * Sorts email's by Descending Date
     */
    public void sortByDateDescending()
    {
        if(currentSortingMethod.equals("DateDescending"))
            return;
        currentSortingMethod = "DateDescending";
        int largest;
        Email temp;
        for(int j = 0; j < emails.size(); j++)
        {
            largest = 0;
            for(int i = 1; i < (emails.size()-j); i++)
            {
                if(emails.get(largest).getTimestamp().compareTo(emails.get(i).getTimestamp()) < 0)
                    largest = i;
            }
            temp = emails.remove(largest);
            emails.add(temp);
        }
    }
    /**
     * @return name of folder
     */
    public String getName()
    {
        return name;
    }
    /**
     * @return the sorting method
     */
    public String getCurrentSortingMethod()
    {
        return currentSortingMethod;
    }
    /**
     * @return list of emails
     */
    public ArrayList<Email> getEmails()
    {
        return emails;
    }
    
}