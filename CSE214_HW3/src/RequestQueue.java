
import java.util.Vector;

/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      This class implements a Queue that holds objects of
 * type Request. It has methods to access and add objects.
 * Uses FIFO - First In First Out.
 */
public class RequestQueue extends Vector
{
    /**
     * Constant value that indicates the maximum number of
     * elements that can be added to this queue.
     */
    public static final int CAPACITY = 1000;
    /**
     * Array of objects of type Request
     */
    private Request[] requests;
    /**
     * Keeps count of the total number of objects currently
     * in the queue
     */
    private int numOfElements;
    /**
     * Integer value that indicates the front of the queue at
     * any given time. -1 indicates the queue is empty
     */
    private int front;
    /**
     * Integer value that indicates the rear of the queue at any
     * given time. -1 indicates queue is empty
     */
    private int rear;
    /**
     * Constructor that creates a new array of of Request. Initializes
     * rear and front to -1 and initializes number of elements to 0.
     */
    public RequestQueue()
    {
        requests = new Request[CAPACITY];
        numOfElements = 0;
        front = -1;
        rear = -1;
    }
    /**
     *      Adds a request to the queue.
     * @param req
     *      request to be added to queue
     * @throws QueueIsFullException 
     *      Is thrown when the queue is full and no further items
     * can be added.
     */
    public void enqueue(Request req) throws QueueIsFullException
    {
        if(numOfElements == CAPACITY)
            throw new QueueIsFullException("Queue is full. No more items can be added unless some are removed");
        if(isEmpty())
        {
            front = 0;
            rear = 0;
        }
        else
        {
            rear = (++rear)%CAPACITY;
        }
        requests[rear] = req;
        numOfElements++;
    }
    /**
     *      Returns the front request of the queue.
     * @return req
     *      Front; oldest added request to the queue.
     * @throws QueueIsEmptyException 
     *      Is thrown if the queue is empty and nothing can be returned
     */
    public Request dequeue() throws QueueIsEmptyException
    {
        Request ret;
        if(isEmpty())
            throw new QueueIsEmptyException("Queue is empty. Nothing can be removed.");
        ret = requests[front];
        if(numOfElements == 1)
        {
            front = -1;
            rear = -1;
        }
        else
            front = (++front)%CAPACITY;
        numOfElements--;
        return ret;
    }
    /**
     * @return numOfElements
     *      Indicates how many elements are currently in the queue
     */
    public int size()
    {
        return numOfElements;
    }
    /**
     * @return True if the queue is empty. False if the queue has at least one item.
     */
    public boolean isEmpty()
    {
        return (numOfElements == 0);
    }
}
