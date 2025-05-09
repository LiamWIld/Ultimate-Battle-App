/**
 * Contender represents a battle participant using the Runnable interface. Each contender
 * will perform an action iteratively until one reaches 200 actions and is declared the winner.
 */

import java.util.Random;

public class Contender implements Runnable {
    private String name;          // Contender's name
    private int actionAmount;     // Number of actions performed per iteration
    private int totalActions;     // Running total of actions performed
    private int maxRest;          // Maximum rest time in milliseconds
    private int drinkTime;        // Time taken to drink water
    private static volatile boolean winner = false; // Tracks if a winner has been determined
    private Water water;          // Shared water object for synchronized drinking

    /**
     * Constructor: Initializes a Contender with the given attributes.
     */
    public Contender(String name, int actionAmount, int maxRest, int drinkTime, Water water) {
        this.name = name;
        this.actionAmount = actionAmount;
        this.maxRest = maxRest;
        this.drinkTime = drinkTime;
        this.water = water;
        this.totalActions = 0;
    }

    /**
     * run executes the logic for the contender.
     * Rest for a random period (up to maxRest milliseconds).
     * Perform the action (increment totalActions by actionAmount).
     * Check if the contender has reached 200 actions to win.
     * If not take a water break using the synchronized drink() method.
     */
    @Override
    public void run() {
        Random random = new Random();

        // Loop until a winner is determined
        while (!winner) {
            try {
                // generates a random rest time (up to maxRest milliseconds)
                int restTime = random.nextInt(maxRest + 1);
                Thread.sleep(restTime); // Simulates resting

                // performs the action by incrementing totalActions
                totalActions += actionAmount;
                System.out.println(name + " performed action, total: " + totalActions);

                // Checks if the contender has won
                if (totalActions >= 200) {
                    winner = true; // Set winner to true, stopping all contenders
                    System.out.println("*** " + name + " wins the battle! ***");
                } else {
                    // Take sa synchronized water break
                    water.drink(name, drinkTime);
                }
            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted.");
            }
        }
    }
}