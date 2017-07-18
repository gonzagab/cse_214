/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      Handles exception when Queue is full
 */
class QueueIsFullException extends Exception
{
    /**
     * Constructor that calls the supper constructor and takes
     * a message that describes the problem.
     * @param message 
     */
    public QueueIsFullException(String message)
    {
        super(message);
    }
    /**
     * @return a description of the problem
     */
    public String getMessage()
    {
        return super.getMessage();
    }
}
