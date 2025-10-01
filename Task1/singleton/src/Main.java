public class Main {
    public static void main(String[] args) {

        IdGenerator generator = IdGenerator.getInstance();

        System.out.println("Generating 5 unique IDs manually:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Generated ID: " + generator.getNextID());
        }
        IdGenerator another = IdGenerator.getInstance();
        System.out.println("Next ID from another reference: " + another.getNextID());
    }
}
