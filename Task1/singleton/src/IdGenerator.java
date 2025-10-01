public class IdGenerator {

    private int counter;
    private static IdGenerator instance;
    private IdGenerator() {
        counter = 0;
    }
    public static IdGenerator getInstance() {
        if (instance == null) {
            synchronized (IdGenerator.class) {
                if (instance == null) {
                    instance = new IdGenerator();
                }
            }
        }
        return instance;
    }

    public synchronized int getNextID() {
        counter++;
        return counter;
    }
    public synchronized void reset() {
        counter = 0;
    }
}
