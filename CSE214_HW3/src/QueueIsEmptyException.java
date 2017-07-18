/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      Class that handles exceptions for when the Queue is
 * empty
 */
class QueueIsEmptyException extends Exception
{
    /**
     * Constructor that calls the super class and takes a
     * message that describes the  problem.
     * @param message 
     */
    public QueueIsEmptyException(String message)
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
