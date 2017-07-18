
import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      Class that holds information regarding an email.
 */
public class Email implements Serializable
{
    /**
     * Holds the recipient of the email
     */
    private String to;
    /**
     * Carbon Copy recipient
     */
    private String cc;
    /**
     * blind carbon copy recipient
     */
    private String bcc;
    /**
     * Holds the subject of the email
     */
    private String subject;
    /**
     * Holds the body of the email
     */
    private String body;
    /**
     * Holds the time stamp of when the email was created
     */
    private GregorianCalendar timestamp;
    /**
     * Instantiates the email to the current time with empty data fields;
     */
    public Email()
    {
        timestamp = (GregorianCalendar)GregorianCalendar.getInstance();
    }

    public Email(String to, String cc, String bcc, String subject, String body)
    {
        this();
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
    }
    /**
     * @return recipient of the email
     */
    public String getTo()
    {
        return to;
    }
    /**
     * @return returns the carbon copy recipient
     */
    public String getCc()
    {
        return cc;
    }
    /**
     * @return blind carbon copy recipient
     */
    public String getBcc()
    {
        return bcc;
    }
    /**
     * @return subject of email
     */
    public String getSubject()
    {
        return subject;
    }
    /**
     * @return Body of email
     */
    public String getBody()
    {
        return body;
    }
    /**
     * @return returns a time stamp
     */
    public GregorianCalendar getTimestamp()
    {
        return timestamp;
    }
    /**
     *      Setter for the recipient
     * @param to 
     */
    public void setTo(String to)
    {
        this.to = to;
    }
    /**
     *      Setter for carbon copy
     * @param cc 
     */
    public void setCc(String cc)
    {
        this.cc = cc;
    }
    /**
     *      Setter for blind carbon copy
     * @param bcc 
     */
    public void setBcc(String bcc)
    {
        this.bcc = bcc;
    }
    /**
     *      Setter for subject
     * @param subject 
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    /**
     *      Setter for the body of email
     * @param body 
     */
    public void setBody(String body)
    {
        this.body = body;
    }
    /**
     *      Setter for time stamp
     * @param timestamp 
     */
    public void setTimestamp(GregorianCalendar timestamp)
    {
        this.timestamp = timestamp;
    }
}
