
import java.util.Scanner;

/**
 * @author Bryant Gonzaga
 * @version 1.0
 *      Holds the main method that takes input from a user
 * and runs a simulation.
 */
public class Analyzer
{
    /**
     *      Main method that take user input and runs a simulation
     * according to that input.
     * @param args 
     */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        double prob = 0.0;
        int[] in = new int[3];
        
        System.out.println("Please enter the following information to run your simulation.");
        System.out.print("Probability of Arrival: ");
        prob = input.nextDouble();
        System.out.print("Number of Floors: ");
        in[0] = input.nextInt();
        System.out.print("Number of Elevators: ");
        in[1] = input.nextInt();
        System.out.print("Length of Simulation (in time units): ");
        in[2] = input.nextInt();
        try
        {
            Simulator.simulate(prob, in[0], in[1], in[2]);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
