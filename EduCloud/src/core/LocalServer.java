package core;

/**
 * LocalServer - simulates the on-premises EduCloud host.
 */
public class LocalServer {
    /**
     * Simulate starting the server.
     */
    public void startServer() {
        System.out.println("🖥️ EduCloud Local Server started (on-premises).");
    }

    /**
     * Simulate a client connecting to the server.
     * @param clientName name of the client (professor or student)
     */
    public void connectClient(String clientName) {
        System.out.println("🔗 Client connected: " + clientName);
    }
}
