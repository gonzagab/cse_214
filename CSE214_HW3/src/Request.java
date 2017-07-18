/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      This class holds information pertaining to
 * requests for an elevator. Holds the source floor
 * and destination floor; as well as the time the
 * request was placed. Has accessors and mutators 
 * for all data fields.
 */
public class Request
{
    /**
     * Floor which the passenger is to be picked
     * up from.
     */
    private int sourceFloor;
    /**
     * Floor which the passenger is to be dropped
     * off on.
     */
    private int destinationFloor;
    /**
     * Integer describing the time the request was placed.
     */
    private int timeEntered;
    /**
     *      Constructor that initializes the sourceFloor and
     * destinationFLoor. They are randomly instantiated, using
     * the Math.Random() method to a value between 1 and the 
     * number of floors in the building.
     * @param numOfFloors:
     *      Describes the number of floors.
     * @throws IllegalArgumentException 
     *      Is thrown if the number of floors is less than 2.
     */
    public Request(int numOfFloors) throws IllegalArgumentException
    {
        if(numOfFloors  < 2)
            throw new IllegalArgumentException("Your Building must have more than 1 floor");
        sourceFloor = (int)(Math.random()*numOfFloors) + 1;
        destinationFloor = (int)(Math.random()*numOfFloors) + 1;
    }
    /**
     * @return Source Floor
     */
    public int getSourceFloor()
    {
        return sourceFloor;
    }
    /**
     * @return Destination Floor
     */
    public int getDestinationFloor()
    {
        return destinationFloor;
    }
    /**
     * @return Number indicating the time the request was placed
     */
    public int getTimeEntered()
    {
        return timeEntered;
    }
    /**
     *      Sets the Source Floor to the given parameter
     * @param sourceFloor
     * @throws IllegalArgumentException
     *      if the parameter is a negative number
     */
    public void setSourceFloor(int sourceFloor) throws IllegalArgumentException
    {
        if(sourceFloor < 1)
            throw new IllegalArgumentException("The source floor should be at least 1");
        this.sourceFloor = sourceFloor;
    }
    /**
     *      Sets the destination floor to the following parameter
     * @param destinationFloor
     * @throws IllegalArgumentException 
     *      If the destination floor is negative
     */
    public void setDestinationFloor(int destinationFloor) throws IllegalArgumentException
    {
        if(sourceFloor < 1)
            throw new IllegalArgumentException("The source floor should be at least 1");
        this.destinationFloor = destinationFloor;
    }
    /**
     *      Sets the time entered to the parameter given
     * @param timeEntered 
     */
    public void setTimeEntered(int timeEntered)
    {
        this.timeEntered = timeEntered;
    }
    /**
     * @return The information of the data fields.
     */
    @Override
    public String toString()
    {
        return "Request{" + "sourceFloor=" + sourceFloor + ", destinationFloor=" + destinationFloor + ", timeEntered=" + timeEntered + '}';
    }
}
