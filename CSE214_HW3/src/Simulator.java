/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      This class has a single static void method
 * that runs a simulation with the the indicated parameters
 * passe to it.
 */
public class Simulator
{
    /**
     *      Takes the parameters and runs a simulation according to
     * parameters.
     * @param prob
     * @param floors
     * @param elevators
     * @param length
     * @throws IllegalArgumentException
     *      Is thrown if any of the arguments are not within the
     * range which they are suppose to be in
     * @throws QueueIsEmptyException
     *      is thrown if Queue is empty and an element is tried to
     * be accessed from the Queue
     * @throws QueueIsFullException
     *      Is Thrown is an element is tried to be added to the Queue
     * and the Queue is full      
     */
    public static void simulate(double prob, int floors, int elevators, int length) throws IllegalArgumentException, QueueIsEmptyException, QueueIsFullException
    {
        if(prob < 0 || prob > 1)
            throw new IllegalArgumentException("The probability must be in the range [0,1]");
        if(floors < 2)
            throw new IllegalArgumentException("The number of floors must be greater than 1");
        if(elevators < 0)
            throw new IllegalArgumentException("The number of elevators must be more positive");
        if(length < 0)
            throw new IllegalArgumentException("The length of the simulation must be positive");
        
        boolean debug = false;
        RequestQueue requests = new RequestQueue();
        BooleanSource booSource = new BooleanSource(prob);
        Request newReq;
        Elevator[] elev = new Elevator[100];
        
        for(int i = 0; i < elevators; i++)
            elev[i] = new Elevator();
        
        int waitingSum = 0;
        int reqCount = 0;
        
        for(int i = 1; i <= length; i++)
        {
            //Begining of Time Unit   
            //Check to add requests to queue
            ////////////DEBUG/////////////////////////////////////////////
            if(debug)                                                   //
                System.out.println("\nBegining of time unit: " + i);    //
            //////////////////////////////////////////////////////////////
            if(booSource.requestArrived())
            {
                ////////////////DEBUG/////////////////////////////////
                if(debug)                                           //
                    System.out.println("New request was made.");    //
              ////////////////////////////////////////////////////////
                newReq = new Request(floors);
                newReq.setTimeEntered(i);
                requests.enqueue(newReq);
                ///////////////DEBUG//////////////////////////
                if(debug)                                   //
                    System.out.println(newReq.toString());  //
                ////////////////////////////////////////////// 
            }
            else
                /////////////////DEBUG////////////////////////////////
                if(debug)/////////////////////////////////////////////
                    System.out.println("New request was NOT made.");//
            //////////////////////////////////////////////////////////
            //Give IDLE elevators requests to fulfill
            for(int k = 0; k < elevators; k++)
            {
                if(elev[k].getElevatorState() == Elevator.IDLE && !requests.isEmpty())
                {
                    //////////////////////////DEBUG///////////////////////
                    if(debug)                                           //
                        System.out.println("Elevator "+k+" is free");   //
                    //////////////////////////////////////////////////////
                    elev[k].setRequest(requests.dequeue());
                    if(elev[k].getCurrentFloor() == elev[k].getRequest().getSourceFloor())   
                    {
                        elev[k].setElevatorState(Elevator.TO_DESTINATION);
                        reqCount++;
                        waitingSum += i - elev[k].getRequest().getTimeEntered();
                        //////////////////////////DEBUG///////////////////////
                        if(debug)                                           //
                            System.out.println("Source floor is the same"); //
                        //////////////////////////////////////////////////////
                    }
                    else
                        elev[k].setElevatorState(Elevator.TO_SOURCE);         
                }
                else
                    ///////////////DEBUG//////////////////////////////////////////////////
                    if(debug)                                                   //////////
                        System.out.println("Elevator " + k + " is not free or no requests to be handled");
                ////////////////////////////////////////////////////////////////////////////
            
                //Move Elevators closer to completing request
                if(elev[k].getElevatorState() == Elevator.TO_SOURCE)
                {
                    /////////////////////debug///////////////////////////////////////////////////
                    if(debug)                                                           ////////////////
                        System.out.println("Elevator moving to source; CF: " + elev[k].getCurrentFloor());
                    ///////////////////////////////////////////////////////////////////////////////
                    if(elev[k].getCurrentFloor() < elev[k].getRequest().getSourceFloor())
                        elev[k].setCurrentFloor(elev[k].getCurrentFloor()+1);
                    else if(elev[k].getCurrentFloor() > elev[k].getRequest().getSourceFloor())
                        elev[k].setCurrentFloor(elev[k].getCurrentFloor()-1);
                    if(elev[k].getCurrentFloor() == elev[k].getRequest().getSourceFloor())
                    {
                        reqCount++;
                        waitingSum += i - elev[k].getRequest().getTimeEntered();
                        elev[k].setElevatorState(Elevator.TO_DESTINATION);
                        if(elev[k].getCurrentFloor() == elev[k].getRequest().getDestinationFloor())
                            elev[k].setElevatorState(Elevator.IDLE);
                        //////////////////////////DEBUG///////////////////
                        if(debug)                                       //
                            System.out.println("Source floor reached"); //
                        //////////////////////////////////////////////////
                    }
                    ////////////////////DEBUG//////////////////////////////////////////////////////////
                    if(debug)                                                                       //
                        System.out.println("Elevator moved to source; CF: " + elev[k].getCurrentFloor());
                    ///////////////////////////////////////////////////////////////////////////////////
                }
                else if(elev[k].getElevatorState() == Elevator.TO_DESTINATION)
                {
                    ///////////////////////////////DEBUG////////////////////////////////////////////////
                    if(debug)                                                                         /////
                        System.out.println("Elevator "+k+" moving to destination; CF: " + elev[k].getCurrentFloor());
                    ///////////////////////////////////////////////////////////////////////////////////////
                    if(elev[k].getCurrentFloor() < elev[k].getRequest().getDestinationFloor())
                        elev[k].setCurrentFloor(elev[k].getCurrentFloor()+1);
                    else if(elev[k].getCurrentFloor() > elev[k].getRequest().getDestinationFloor())
                        elev[k].setCurrentFloor(elev[k].getCurrentFloor()-1);
                    if(elev[k].getCurrentFloor() == elev[k].getRequest().getDestinationFloor())
                        elev[k].setElevatorState(Elevator.IDLE);
                    ///////////////////////////////DEBUG////////////////////////////////////////////////
                    if(debug)                                                                         /////
                        System.out.println("Elevator "+k+" moved to destination; CF: " + elev[k].getCurrentFloor());
                    ///////////////////////////////////////////////////////////////////////////////////////
                }
                else
                    //////////////DEBUG///////////////////////////////////////////////
                    if(debug)                                                       //
                    {                                                               //
                        System.out.print("Elevator " + k + " did not move; Stat: ");//
                        System.out.println(elev[k].getElevatorState());             //
                    }                                                               //
                //////////////////////////////////////////////////////////////////////
            }   
        }
        System.out.println("Waiting Sum: " + waitingSum);
        System.out.println("Request Count: " + reqCount);
        System.out.println("Average Wait Time: " + waitingSum/reqCount);
    }
}
