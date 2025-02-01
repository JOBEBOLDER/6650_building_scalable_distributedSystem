import java.io.*;
import java.net.*;//java.net.* is for network communication (like connecting to a server

public class Client {// main class for the client program.
    public static void main(String[] args) {
        // errors handlings
        if (args.length != 2) {
            System.out.println("Usage: java Client <server_ip> <port>");
            return;
        }

        // variables definition
        String serverIP = args[0]; // get the command line
        int port = Integer.parseInt(args[1]); // get port from the command line

        try (Socket socket = new Socket(serverIP, port)) {
            System.out.println("Connected to server");

            // get inputout
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);

            // read the string from the userinput
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter text: ");
            String text = userInput.readLine();

            // Send data to the server:
            writer.println(text);

            // Receive data from the server:
            String response = reader.readLine();
            System.out.println("Response from server: " + response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}