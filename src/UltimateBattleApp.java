/**
 * Liam Wild
 * COP 3330C-24217
 * 2/16/25
 * Program: Ultimate Battle Simulation
 *
 * Purpose: Simulates a battle between two contenders using Java threads.
 * each contender performs an iterative action.
 * Each contender rests for a variable amount of time.
 * A shared Water resource is used, ensuring only one contender drinks at a time.
 * The first contender to reach 200 actions wins the battle.
 *
 * Inputs: No user input required.
 * Outputs: Real-time updates on each contender's progress, and a message announcing the winner.
 */

public class UltimateBattleApp {
    public static void main(String[] args) {
        // shared Water object that both contenders will use
        Water sharedWater = new Water();

        // two contenders with different action/rest strategies
        // Contender 1 takes smaller actions but rests less and drinks for a longer time
        Contender contender1 = new Contender("Yoda", 5, 500, 1000, sharedWater);

        // Contender 2 takes larger actions but rests longer and drinks for a shorter time
        Contender contender2 = new Contender("Darth Vader", 10, 1000, 500, sharedWater);

        // threads for both contenders
        Thread thread1 = new Thread(contender1);
        Thread thread2 = new Thread(contender2);

        // Starts both threads to begin the battle
        thread1.start();
        thread2.start();

        // Waits for both threads to finish execution
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        // Displays the end-of-battle message
        System.out.println("Battle Over!");
    }
}