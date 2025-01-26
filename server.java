import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        // error handling
        if (args.length != 1) {
            System.out.println("Usage: java Server <port>");
            return;
        }

        int port = Integer.parseInt(args[0]); // Get the port number from the command line arguments

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept(); // wait for the client side to connect
                System.out.println("Client connected");

                // get the input stream
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output, true);

                // 读取客户端发送的字符串
                String text = reader.readLine();
                System.out.println("Received from client: " + text);

                // 处理字符串：反转并反转大小写
                String reversedText = reverseString(text);
                String result = reverseCase(reversedText);

                // 将处理后的字符串发送回客户端
                writer.println(result);
                System.out.println("Sent to client: " + result);

                // 关闭连接
                socket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // reverse String function
    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // reverse char function
    private static String reverseCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                chars[i] = Character.toLowerCase(chars[i]);
            } else if (Character.isLowerCase(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return new String(chars);
    }
}