/**
 * EmptyPlaylistException class handles exceptions for
 * when a playlist is empty and items in a playlist are
 * attempted to be modified.
 * @author Bryant Gonzaga
 */
public class EmptyPlaylistException extends Exception
{
    /**
     * Constructor that calls the super class Exception
     */
    EmptyPlaylistException()
    {
        super();
    }
    /**
     * Message is passed describing the problem
     * super(problem) is then called
     * @param problem 
     */
    EmptyPlaylistException(String problem)
    {
            super(problem);
    }
    /**
     * Returns the message provided of the problem
     * @return 
     */
    public String getMessage()
    {
            return super.getMessage();
    }
}
