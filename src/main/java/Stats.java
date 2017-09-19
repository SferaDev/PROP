public class Stats {
    private static Stats mInstance = new Stats();

    public static Stats getInstance() {
        return mInstance;
    }

    private Stats() {
        // Should never be instantiated
    }
}
