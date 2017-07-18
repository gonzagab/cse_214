/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      This is a class were the probability is set. It has
 * one method that returns true probability percent of the time.
 */
public class BooleanSource
{
    /**
     * The probability which the function will return true
     */
    private double probability;
    /**
     *      Constructor that sets the probability to the passed 
     * parameter
     * @param arg
     * @throws IllegalArgumentException 
     *      Is thrown if the parameter passes is not between 0
     * and 1 inclusively
     */
    public BooleanSource(double arg) throws IllegalArgumentException
    {
        if(arg < 0 || arg > 1)
            throw new IllegalArgumentException("The probability must be equal or greater that 0 and equal or less that 1");
        probability = arg;
    }
    /**
     * @return true when Math.random() returns a number smaller
     * than the probability. false otherwise.
     */
    public boolean requestArrived()
    {
        return (Math.random() < probability);
    }
}