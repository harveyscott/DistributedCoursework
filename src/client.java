import java.io.*;
import java.net.Socket;

public class client {
    static String host = "localhost";
    static int port = 4444;

    public static void main(String[] args) {
        System.out.println("Enter numbers separated by spaces into console then press ENTER");


        // create Socket for communication
        try (Socket kkSocket = new Socket(host, port)) {
            try {
                // Create Input and Output streams
                PrintWriter os = new PrintWriter(new OutputStreamWriter(
                        kkSocket.getOutputStream()));
                BufferedReader is = new BufferedReader(new InputStreamReader(
                        kkSocket.getInputStream()));
                String fromServer;
                String input;
                try {
                    while ((fromServer = is.readLine()) != null) {
                        System.out.println("Server: " + fromServer);
                        // sets up a stream for user input
                        BufferedReader userInput = new BufferedReader(
                                new InputStreamReader(System.in));
                        input = userInput.readLine();
                        System.out.println("Client: " + input);
                        os.println(input); // sending message to the server
                        os.flush();
                    } // end while
                } catch (IOException e) {
                    System.err.println("Unable to open I/O streams  " + e);
                }
                os.close();
                is.close();
            } catch (IOException e) {
                System.err.println("Unable to open socket to Host  " + e);
            }
            kkSocket.close();
        } catch (IOException ex) {
            System.err.println("Unable to connect to host  " + ex);
        }
    }

}
