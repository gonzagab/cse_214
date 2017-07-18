/**
 * @author Bryant Gonzaga
 * @version 1.0
 *  This class handles exception for when an ItemList object
 * has no nodes and a user attempts to modify the non existing
 * nodes.
 */
class EmptyListException extends Exception
{
    /**
     * Constructor that calls the super constructor
     */
    public EmptyListException()
    {
        super();
    }
    /**
     * Constructor that calls the super constructor
     * @param message
     *      Describes the reason for throwing this exception
     */
    public EmptyListException(String message)
    {
        super(message);
    }
    /**
     * @return super.getMessage()
     *      returns a String describing the problem
     */
    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
}
