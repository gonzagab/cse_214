/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      This class describes and elevator and its functions.
 * Elevators can be in three states. Idle moving to pick up
 * a passenger or moving to drop off a passenger
 */
public class Elevator
{
    /**
     * Constant integer that is used to indicate an elevator is
     * Idle
     */
    public static final int IDLE = 0;
    /**
     * Constant integer that is used to indicate an elevator is
     * going to pick up a passenger
     */
    public static final int TO_SOURCE = 1;
    /**
     * Constant integer that is used to indicate an elevator is
     * going to drop off a passenger
     */
    public static final int TO_DESTINATION = 2;
    /**
     * Holds the value of the floor, which the elevator is on currently
     */
    private int currentFloor;
    /**
     * Holds one of the three possible states, which the elevator can be in.
     */
    private int elevatorState;
    /**
     * Request object that holds information regarding the request the
     * elevator is handling. null if elevator is not handling a request
     */
    private Request request;
    /**
     * Constructor that initiates variables. Initiates the elevator to
     * and idle state with no request and on floor 1.
     */
    public Elevator()
    {
        request = null;
        elevatorState = IDLE;
        currentFloor = 1;
    }
    /**
     * @return currentFloor: the floor the elevator is on currently
     */
    public int getCurrentFloor()
    {
        return currentFloor;
    }
    /**
     * @return elevatorState: an integer that indicates the state the
     * elevator is in.
     */
    public int getElevatorState()
    {
        return elevatorState;
    }
    /**
     * @return request: object holding information about request
     */
    public Request getRequest()
    {
        return request;
    }
    /**
     * @param currentFloor: the floor the elevator is currently on.
     * @throws IllegalArgumentException 
     *      Is thrown if current floor is less that 1
     */
    public void setCurrentFloor(int currentFloor) throws IllegalArgumentException
    {
        if(currentFloor < 1)
            throw new IllegalArgumentException("The current floor must be at least 1.");
        this.currentFloor = currentFloor;
    }
    /**
     *      Setter for elevator state. Sets the state to the parameter.
     * @param elevatorState: State the elevator is in.
     * @exception IllegalArgumentException
     *      Is thrown if elevatorState is not one of the three states.
     */
    public void setElevatorState(int elevatorState) throws IllegalArgumentException
    {
        if(elevatorState != IDLE && elevatorState != TO_DESTINATION && elevatorState != TO_SOURCE)
            throw new IllegalArgumentException("Elevator state must be either IDLE, TO_SOURCE, or TO_DESTINATION");
        this.elevatorState = elevatorState;
        if(elevatorState == IDLE)
            request = null;
    }
    /**
     *      Setter for the request, which the elevator is handling.
     * @param request 
     */
    public void setRequest(Request request)
    {
        this.request = request;
    }  
}
