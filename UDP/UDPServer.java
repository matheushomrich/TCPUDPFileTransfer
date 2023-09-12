import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java UDPServer <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        // Create a UDP socket for the server with the specified port
        DatagramSocket serverSocket = new DatagramSocket(port);

        while (true) {
            byte[] receiveData = new byte[1024];

            // Declare the packet to be received
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receive a packet from the client
            serverSocket.receive(receivePacket);

            // Extract the received data
            byte[] receivedBytes = receivePacket.getData();
            int receivedLength = receivePacket.getLength();

            // Handle the received data (e.g., save it to a file)
            // You can change this to save the data to a file
            FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt", true);
            fileOutputStream.write(receivedBytes, 0, receivedLength);
            fileOutputStream.close();

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            // Send an acknowledgment to the client (optional)
            // String ackMessage = "Data received successfully.";
            // byte[] ackData = ackMessage.getBytes();
            // DatagramPacket ackPacket = new DatagramPacket(ackData, ackData.length, clientAddress, clientPort);
            // serverSocket.send(ackPacket);
        }
    }
}
