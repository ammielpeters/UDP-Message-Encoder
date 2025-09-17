import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);

            byte[] sendData;
            byte[] receiveData = new byte[1024];

            System.out.println("Enter messages (type 'exit' to quit):");

            while (true) {
                // Read input from user
                String message = sc.nextLine();
                sendData = message.getBytes();

                // Send to server
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, 9876);
                clientSocket.send(sendPacket);

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Client shutting down...");
                    break;
                }

                // Receive response (encoded message)
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Encoded message from server: " + serverResponse);
            }

            clientSocket.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
