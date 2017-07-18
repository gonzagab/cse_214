/**
 * @author Bryant Gonzaga
 * @version 1.0
 * The Playlist class stores up to 50 SongRecord objects. SongRecord objects
 * contain information about a given song, such as Title, Artist, and duration.
 */
public class Playlist implements Cloneable
{
	/**
	 * MAX_SONGS is a variable that stores the maximum number of songs
	 * that can be stored in this class
	 */
	public static final int MAX_SONGS = 50;
	/**
	 * SongRecord[] is an array of type SonRecord.
	 */
	private SongRecord[] song;
	/**
	 * numOfSongs holds the number of song currently in the playlist.
	 */
	private int numOfSongs;
	/**
	 * Holds the name of this playlist.
	 */
	private String playlistName;
	/**
	 * table is of type StringBuilder that holds a neatly organized
	 * table of String representation of all the songs in a playlist
	 */
	private StringBuilder table;
	/**
	 * Playlist is a no argument constructor
	 */
	public Playlist()
	{
		this("UNTITLED");
	}
	public Playlist(String name)
	{
		song = new SongRecord[MAX_SONGS];
		playlistName = name;
		numOfSongs = 0;
	}
	/**
	 * equals is a method that compares to see if a given playlist is
	 * the same as another. Songs must match as the order they are in
	 * the playlist. returns true if playlist are equal else false.
	 * @return answer
	 */
	public boolean equals(Object obj)
	{
		boolean answer = true;
		
		if(obj instanceof Playlist)
		{
			if(((Playlist)obj).size() == numOfSongs)
			{
				for(int i = 0; i < numOfSongs; i++)
				{
					try
					{
                                            if(!((Playlist)obj).getSong(i+1).equals(this.getSong(i+1)))
                                            {
                                                    answer = false;
                                                    break;
                                            }
					}
					catch (IllegalArgumentException | EmptyPlaylistException e)
					{
						System.out.println("There was a problem in the program");
					}
				}
				return answer;
			}
			else
				return false;
		}
		else
			return false;
	}
	/**
	 * @return playlistName
	 */
	public String getPlaylistName()
	{
		return playlistName;
	}
	/**
	 * Sets the name of the playlist
	 */
	public void setPlaylistName(String name)
	{
		playlistName = name;
	}
	/**
	 * size is a method that gives the number of songs in a given playlist
	 * @return numOfSongs
	 */
	public int size()
	{
		return numOfSongs;
	}
	/**
	 * addSong method takes a song of type SongRecord and adds it to the array
	 * at the position passed. Position must be, or greater than, 1 and less
	 * than or equal to the number of Songs + 1  currently on the playlist.
	 * if playlist is full FullPlaylistException is thrown. if position deosn't
	 * meet the requirements an IllegalArgumentException is thrown. 
	 * @param song
	 * @param position
	 * @throws IllegalArgumentException
	 * @throws FullPlaylistException
	 */
	public void addSong(SongRecord song, int position) throws IllegalArgumentException, FullPlaylistException
	{
		if(numOfSongs >= MAX_SONGS)
			throw new FullPlaylistException("This playlist is full.");
		else if(position < 1 || position > (numOfSongs+1) || position > MAX_SONGS)
			throw new IllegalArgumentException("Position must be 1 - " + (numOfSongs+1));
		else
		{
			for(int i = numOfSongs; i >= position; i--)
			{
				this.song[i] = this.song[i-1];
			}
			this.song[position-1] = song;
			numOfSongs++;
			updateTable();
		}
	}
	/**
	 * removeSong Method takes the position of the song to be removed and
	 * rmoves that song. If the playlist is emppty then EmptyPlaylistEception
	 * is thrown. Position must be equal to or greater than 1 and must be
	 * equal or less than number of songs on playlist. if requirments are not
	 * met then IllegalArgumentException is thrown.
	 * @param position
	 * @throws IllegalArgumentException
	 * @throws EmptyPlaylistException
	 */
	public void removeSong(int position) throws IllegalArgumentException, EmptyPlaylistException
	{
		if(numOfSongs == 0)
			throw new EmptyPlaylistException("This playlist is empty.");
		else if(position < 1 || position > numOfSongs)
			throw new IllegalArgumentException("Position must be 1 - " + numOfSongs);
		else
		{
			for(int i = (position-1); i < (numOfSongs-1); i++)
			{
				this.song[i] = this.song[i+1];
			}
			numOfSongs--;
			updateTable();
		}
	}
	/**
	 * Returns the SongRecord object at the specified position.
	 * throws exception if playlist is empty or the position
	 * specified is not within the proper range 1 - MAX_SONGS
	 * @param position
	 * @return
	 * @throws IllegalArgumentException
	 * @throws EmptyPlaylistException
	 */
	public SongRecord getSong(int position) throws IllegalArgumentException, EmptyPlaylistException
	{
		if(numOfSongs < 1)
			throw new EmptyPlaylistException("This playlist is empty.");
		else if(position < 0 || position > numOfSongs)
			throw new IllegalArgumentException("That is an invalid choice");
		else
			return song[position-1];
	}
	/**
	 * This method prints out all the songs in this playlist.
	 * If the Playlist is empty then the following exception is thrown.
	 * @throws EmptyPlaylistException
	 */
	public void printAllSongs() throws EmptyPlaylistException
	{
		if(numOfSongs == 0)
			throw new EmptyPlaylistException("This playlist is empty");
		else
			System.out.println(toString());
	}
	/**
	 * Given a playlist and an artist, this method returns a playlist that
	 * only contains songs by that artist. Throws following exceptions if
	 * artist provided could not be found.
	 * @param originalList
	 * @param artist
	 * @return
	 * @throws IllegalArgumentException
	 * @throws FullPlaylistException
	 * @throws EmptyPlaylistException
	 */
	public static Playlist getSongsByArtist(Playlist originalList, String artist) throws IllegalArgumentException, FullPlaylistException, EmptyPlaylistException
	{
		Playlist newList = new Playlist();
		int n = 0;
		for(int i = 1; i <= originalList.size(); i++)
			if(artist.equals(originalList.getSong(i).getArtist()))
				newList.addSong(originalList.getSong(i), ++n);
		if(n == 0)
		{
			newList = null;
			throw new IllegalArgumentException("Artist could not be found in this playlist");
		}
		return newList;
	}
	/**
	 * @return table.toString()
	 */
	public String toString()
	{
		updateTable();
		return table.toString();
	}
	/**
	 * Helper method that updates table of type StringBuilder. This method
	 * will create an object that holds the table of songs. 
	 */
	private void updateTable()
	{
		table = new StringBuilder(String.format("%-7s %-30s %-30s %-5s\n", "Song#", "Title", "Artist", "Time"));
		double time = 0.0;
		for(int i = 0; i < numOfSongs; i++)
		{
			time = (double)song[i].getMinLength() + (song[i].getSecLength()/100.0); 
			table.append(String.format("%-7d %-30s %-30s %-5s", (i+1), song[i].getTitle(), song[i].getArtist(), time));
			if(i < (numOfSongs-1))
				table.append("\n");
		}
	}
	/**
	 * This method returns a deep clone of this object
	 * @return clone
	 */
	public Object clone() throws CloneNotSupportedException
	{
            Playlist clone = new Playlist();
            for(int i = 0; i < numOfSongs; i++)
            {
                try
                {
                        clone.addSong((SongRecord)this.getSong(i+1).clone(), i+1);
                } catch (IllegalArgumentException | FullPlaylistException | EmptyPlaylistException e)
                {
                        e.printStackTrace();
                }
            }
            return clone;
	}
}
