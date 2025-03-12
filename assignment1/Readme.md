---

# Java Socket Programming: Client-Server Communication

This project demonstrates a simple TCP-based client-server communication using Java. The server listens for client connections, processes a string sent by the client, and sends back the modified string.

---

## **Features**
- **Server**:
  - Listens on a specified port for client connections.
  - Receives a string from the client.
  - Reverses the string and changes the case of each character (e.g., "Hello" becomes "OLLEh").
  - Sends the modified string back to the client.
  - Closes the connection after processing.

- **Client**:
  - Connects to the server using the server's IP address and port number.
  - Sends a string to the server.
  - Receives and displays the modified string from the server.
  - Closes the connection.

---

## **How to Use**

### **1. Compile the Code**
First, compile the `Server.java` and `Client.java` files:
```bash
javac Server.java
javac Client.java
```

---

### **2. Start the Server**
Run the server program and specify the port number to listen on. For example:
```bash
java Server 32000
```
- The server will start and display:
  ```
  Server is listening on port 32000
  ```

---

### **3. Start the Client**
Run the client program and provide the server's IP address and port number. For example:
```bash
java Client 127.0.0.1 32000
```
- The client will connect to the server and prompt you to enter a string:
  ```
  Enter text: 
  ```

---

### **4. Enter a String**
Type a string (up to 80 characters) and press `Enter`. For example:
```
Enter text: Hello World
```

---

### **5. View the Result**
The server will process the string and send it back to the client. The client will display the result:
```
Response from server: DLROw OLLEh
```

---

### **6. Close the Connection**
After processing one string, both the server and client will close the connection.

---

## **Example**

### **Server Output**
```
Server is listening on port 32000
Client connected
Received from client: Hello World
Sent to client: DLROw OLLEh
Client disconnected
```

### **Client Output**
```
Connected to server
Enter text: Hello World
Response from server: DLROw OLLEh
```

---

## **Code Files**
- **`Server.java`**: The server program that listens for client connections and processes strings.
- **`Client.java`**: The client program that connects to the server and sends/receives strings.

---

## **Requirements**
- Java Development Kit (JDK) installed.
- A terminal or command prompt to run the programs.

---

## **Limitations**
- The server can only handle one client at a time.
- The input string must be 80 characters or less.

---

## **Future Improvements**
- Add support for multiple clients using threads.
- Validate input length and handle errors gracefully.
- Allow the server to run continuously and handle multiple requests.

---

## **Credits**
This project is based on an assignment by **Prof. K. C. Almeroth, UCSB**.

---

Enjoy using the client-server communication program! If you have any questions or suggestions, feel free to reach out. 😊

--- 