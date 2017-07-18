/**
 * @author Bryant Gonzaga
 * SongRecord class holds information pertaining to songs.
 * such ass, title, artist, and duration. Also allows for change
 * in datafields through getters and setters.
 */
public class SongRecord implements Cloneable
{
    /**
     * title holds the name of the given song
     */
    private String title;
    /**
     * artist holds the name of the artist
     */
    private String artist;
    /**
     * minLength holds the minute portion of the song.
     */
    private int minLength;
    /**
     * secLength holds the seconds portion of the song.
     */
    private int secLength;
    /**
     * SongRecord constructor creates a SongRecord object
     * with title and artist.
     * @param title
     * @param artist
     */
    public SongRecord(String title, String artist)
    {
            this.title = title;
            this.artist = artist;
    }
    /**
     * Constructor that creates a SongRecord object with
     * the following specified parameters 
     * @param title
     * @param artist
     * @param minLength
     * @param secLength
     */
    public SongRecord(String title, String artist, int minLength, int secLength)
    {
            this(title, artist);
            setMinLength(minLength);
            setSecLength(secLength);
    }
    /**
     * returns the title of this song
     * @return title
     */
    public String getTitle()
    {
            return title;
    }
    /**
     * setter for title
     * @param title
     */
    public void setTitle(String title)
    {
            this.title = title;
    }
    /**
     * getter for artist
     * @return artist
     */
    public String getArtist()
    {
            return artist;
    }
    /**
     * setter for artist
     * @param artist
     */
    public void setArtist(String artist)
    {
            this.artist = artist;
    }
    /**
     * getter for minutes of the song
     * @return minLength
     */
    public int getMinLength()
    {
            return minLength;
    }
    /**
     * sets the minutes portion of the song. the minutes must be
     * positive value or exception is thrown
     * @param minLength
     * @throws IllegalArgumentException
     */
    public void setMinLength(int minLength) throws IllegalArgumentException
    {
            if(minLength < 0) {
                    throw new IllegalArgumentException("Duration of song must be greater than 0");
            } else {
                    this.minLength = minLength;
            }
    }
    /**
     * returns the seconds portion of a song
     * @return
     */
    public int getSecLength()
    {
            return secLength;
    }
    /**
     * setter for the seconds portion of the song. Seconds must be
     * 0 - 59. including 0 and 59. else exception is thrown
     * @param secLength
     * @throws IllegalArgumentException
     */
    public void setSecLength(int secLength) throws IllegalArgumentException
    {
        if(secLength < 0 || secLength > 59)
                throw new IllegalArgumentException("Second's of the song must be between 0 and 59");
        else
                this.secLength = secLength;
    }
    /**
     * toString returns a string representation of the object. In this case
     * it returns the name of the song and the artist
     */
    public String toString()
    {
        return (title + " By " + artist);
    }
    /**
     * checks if two objects are equal. Checking that the artist
     * name and duration of the song match
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof SongRecord) {
                return	(
                ((SongRecord)obj).getArtist().equals(artist) && 
                ((SongRecord)obj).getTitle().equals(title) &&
                ((SongRecord)obj).getMinLength() == minLength &&
                ((SongRecord)obj).getSecLength() == secLength
                );
        } else
                return false;
    }
    /**
     * Clone method that returns a new instantiation of this object
     */
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}