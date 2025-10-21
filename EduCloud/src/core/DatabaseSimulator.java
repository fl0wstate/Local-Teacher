package core;

/**
 * DatabaseSimulator - prints simulated SQL operations to the console.
 */
public class DatabaseSimulator {
    /**
     * Simulate an insert operation by printing to console.
     * @param table table name
     * @param record record contents
     */
    public void insert(String table, String record) {
        System.out.println("INSERT INTO " + table + " VALUES('" + record + "')");
    }
}
