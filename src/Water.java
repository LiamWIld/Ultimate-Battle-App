/**
 * Water represents a shared resource that both contenders will use for drinking.
 * This class ensures that only one contender can drink at a time using synchronized methods.
 */

public class Water {
    /**
     *
     * the method drink simulates a contender drinking water.
     * The method is synchronized to ensure
     * that only one contender can drink at a time.
     *
     * contenderName is the name of the contender who is drinking.
     * drinkTime is the time that the contender will spend drinking.
     */
    public synchronized void drink(String contenderName, int drinkTime) {
        try {
            System.out.println(contenderName + " is drinking water...");
            Thread.sleep(drinkTime); // Simulates drinking time
            System.out.println(contenderName + " finished drinking water.");
        } catch (InterruptedException e) {
            System.out.println(contenderName + " was interrupted while drinking.");
        }
    }
}