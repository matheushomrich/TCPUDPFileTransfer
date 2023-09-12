import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java UDPClient <server_ip> <port>");
            return;
        }

        String serverAddr = args[0];
        int port = Integer.parseInt(args[1]);

        // Create a UDP socket for the client
        DatagramSocket clientSocket = new DatagramSocket();

        // Get the server's IP address
        InetAddress ipAddress = InetAddress.getByName(serverAddr);

        // Specify the file to send
        String filePath = "file_to_send.txt"; // Change this to the path of your file

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            while (true) {
                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];

                // Read a chunk of data from the file
                int bytesRead = fileInputStream.read(sendData);

                if (bytesRead == -1) {
                    // End of file reached
                    System.out.println("File sent successfully.");
                    break;
                }

                // Create a packet with the data, server's address, and port
                DatagramPacket sendPacket = new DatagramPacket(sendData, bytesRead, ipAddress, port);

                // Send the packet
                clientSocket.send(sendPacket);

                // Declare a packet to receive a response from the server (optional)
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive a response from the server (optional)
                // clientSocket.receive(receivePacket);

                // Handle the response (if needed)
                // String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
                // System.out.println("Server Response: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the socket when done
            clientSocket.close();
        }
    }
}
