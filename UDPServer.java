import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    // Function to encode message (Caesar Cipher shift = 1)
    private static String encodeMessage(String msg) {
        StringBuilder encoded = new StringBuilder();
        for (char c : msg.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                encoded.append((char) ((c - 'a' + 1) % 26 + 'a'));
            } else if (c >= 'A' && c <= 'Z') {
                encoded.append((char) ((c - 'A' + 1) % 26 + 'A'));
            } else {
                encoded.append(c); // keep spaces/punctuation unchanged
            }
        }
        return encoded.toString();
    }

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("Server started. Waiting for messages...");

            while (true) {
                // Receive packet from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Exit condition
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Server shutting down...");
                    break;
                }

                System.out.println("Received from client: " + clientMessage);

                // Encode the message
                String encodedMessage = encodeMessage(clientMessage);
                System.out.println("Sending encoded: " + encodedMessage);

                // Send back to client
                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                sendData = encodedMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
                serverSocket.send(sendPacket);
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
