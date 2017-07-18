import java.io.File;
import java.util.Scanner;

public class PlaylistOperations
{
    public static final int TITLE = 0;
    public static final int ARTIST = 1;
    public static final int FILE_NAME = 2;
    public static final int SONG_MIN = 0;
    public static final int SONG_SEC = 1;
    public static final int POSITION = 2;
    public static final int MAX_PLAYLISTS = 10;
    {
        Playlist[] playlist = new Playlist[MAX_PLAYLISTS];
        playlist[0] = new Playlist("OG Playlist");
        Scanner input = new Scanner(System.in);
        String[] songInfo = new String[3];
        int[] songInfoInt = new int[3];
        String playlistName = "";
        int numOfPlaylist = 1;
        String choice = "";
        int index = 0;

        do
        {	
            System.out.println("Current Playlist: " + playlist[index].getPlaylistName());
            System.out.println("N: Create a new playlist and set as current playlist");
            System.out.println("V: Change current playlist");
            System.out.println("C: Copy the current playlist's songs into a new playlist");
            System.out.println("E: Compare the songs in the current playlist with another playlist");
            System.out.println("D: Display all playlist names.");
            System.out.println("A: Add Song");
            System.out.println("G: Get Song");
            System.out.println("R: Remove Song");
            System.out.println("P: Print All Songs");
            System.out.println("B: Print Songs By Artist");
            System.out.println("S: Size");
            System.out.println("Q: Quit");
            System.out.print("Choice: ");
            choice = input.nextLine();

            if(choice.equals("N") || choice.equals("n"))
            {
                System.out.println("What is the name of the new playlist?");
                System.out.print("Name: ");
                playlistName = input.nextLine();
                try
                {
                    playlist[++index] = new Playlist(playlistName);
                    numOfPlaylist++;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if(choice.equals("V") || choice.equals("v"))
            {
                    System.out.println("What is the name of the playlist you want to find?\nName of Playlist: ");
                    playlistName = input.nextLine();
                    for(int i = 0; i < numOfPlaylist; i++)
                            if(playlist[i].getPlaylistName().equals(playlistName))
                                    index = i;
                    if(!playlist[index].getPlaylistName().equals(playlistName))
                            System.out.println("Playlist not found.");
            }
            else if(choice.equals("C") || choice.equals("c"))
            {
                    System.out.print("Name of new playlist: ");
                    playlistName = input.nextLine();
                    try
                    {
                            playlist[numOfPlaylist] = (Playlist)playlist[index].clone();
                            playlist[numOfPlaylist++].setPlaylistName(playlistName);
                    }
                    catch(Exception e)
                    {
                            e.printStackTrace();
                    }
            }
            else if(choice.equals("E") || choice.equals("e"))
            {
                    System.out.print("Name of other playlist: ");
                    playlistName = input.nextLine();
                    for(int i = 0; i < numOfPlaylist; i++)
                            if(playlist[i].getPlaylistName().equals(playlistName))
                                    if(!playlist[index].equals(playlist[i]))
                                            System.out.println("Playlists are not the same.");
                                    else
                                            System.out.println("Playlist are exactly the same");
            }
            else if(choice.equals("D") || choice.equals("d"))
            {
                    System.out.println(String.format("%-15s %-5s", "Name", "# of Songs"));
                    for(int i = 0; i < numOfPlaylist; i++)
                    {
                            System.out.println(String.format("%-15s %d", playlist[i].getPlaylistName(), playlist[i].size()));
                    }
            }
            else if(choice.equals("A") || choice.equals("a"))
            {
                System.out.println("Title of Song: ");
                songInfo[TITLE] = input.nextLine();
                System.out.println("Artist of Song: ");
                songInfo[ARTIST] = input.nextLine();
                System.out.println("Minutes of Song: ");
                songInfoInt[SONG_MIN] = input.nextInt();
                System.out.println("Seconds of Song: ");
                songInfoInt[SONG_SEC] = input.nextInt();
                System.out.println("Position of Song: ");
                songInfoInt[POSITION] = input.nextInt();
                input.nextLine();
                try
                {
                    playlist[index].addSong(new SongRecord(songInfo[TITLE], songInfo[ARTIST], songInfoInt[SONG_MIN], songInfoInt[SONG_SEC]), songInfoInt[POSITION]);					
                }
                catch(IllegalArgumentException | FullPlaylistException e)
                {
                    System.out.println(e.getMessage());
                    System.out.println("Song not added.");
                }
            }
            else if(choice.equals("G") || choice.equals("g"))
            {
                    System.out.println("What position is the song you want in?\nPosition: ");
                    songInfoInt[POSITION] = input.nextInt();
                    input.nextLine();
                    try
                    {
                            System.out.println(playlist[index].getSong(songInfoInt[POSITION]));
                    }
                    catch(IllegalArgumentException | EmptyPlaylistException e)
                    {
                            System.out.println(e.getMessage());
                            System.out.println("Your song could not be found.");
                    }

            }
            else if(choice.equals("R") || choice.equals("r"))
            {
                    System.out.println("What position is the song that you want to remove in?");
                    System.out.print("Position: ");
                    songInfoInt[POSITION] = input.nextInt();
                    input.nextLine();
                    try
                    {
                            playlist[index].removeSong(songInfoInt[POSITION]);
                    }
                    catch(IllegalArgumentException | EmptyPlaylistException e)
                    {
                            System.out.println(e.getMessage());
                            System.out.println("Song could not be removed.");
                    }
            }
            else if(choice.equals("P") || choice.equals("p"))
            {
                    try
                    {
                            playlist[index].printAllSongs();
                    }
                    catch (EmptyPlaylistException e)
                    {
                            System.out.println(e.getMessage());
                    }
            }
            else if(choice.equals("B") || choice.equals("b"))
            {
                    System.out.print("Artist: ");
                    songInfo[ARTIST] = input.nextLine();
                    try
                    {
                            System.out.println(Playlist.getSongsByArtist(playlist[index], songInfo[ARTIST]));
                    }
                    catch(IllegalArgumentException | FullPlaylistException | EmptyPlaylistException e)
                    {
                            System.out.println(e.getMessage());
                    }
            }
            else if(choice.equals("S") || choice.equals("s"))
                    System.out.println("Size: " + playlist[index].size());
            else if(choice.equals("Q") || choice.equals("q"))
            {
                    System.out.println("Are you sure you want to quit the program?");
                    System.out.println("Q: Quit");
                    System.out.println("C: Continue");
                    System.out.print("Choice: ");
                    choice = input.nextLine();
            }
            else
                    System.out.println("Invalid entry. Please try again.");
            System.out.println();
        }while(!choice.equals("Q") && !choice.equals("q"));
        input.close();
        System.out.println("Program Terminated...");
    }
}
