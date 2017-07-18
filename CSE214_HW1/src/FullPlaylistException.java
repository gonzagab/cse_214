/**
 * FullPlaylistException is thrown when a playlist is full
 * and people try to add a songRecord to Playlist
 * @author Bryant Gonzaga
 */
public class FullPlaylistException extends Exception
{
    /**
     * Constructor that calls super()
     */
    public FullPlaylistException()
    {
        super();
    }
    /**
     * Constructor that takes a message describing the problem
     * then calls super(String arg)
     * @param problem 
     */
    public FullPlaylistException(String problem)
    {
            super(problem);
    }
    /**
     * getMessage returns the problem message by calling
     * super.getMessage();
     * @return super.getMessage()
     */
    public String getMessage()
    {
            return super.getMessage();
    }
}
