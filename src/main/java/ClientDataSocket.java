package main.java;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class ClientDataSocket {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        String s1 = "message from client---hello";
        byte[] sendBytes = s1.getBytes();
        byte[] receiveBytes = new byte[2];
        DatagramPacket datagramPacket = new DatagramPacket(sendBytes,27, InetAddress.getByName("localhost"),5252);
        DatagramPacket datagramPacket1 = new DatagramPacket(receiveBytes,2);
        datagramSocket.connect(InetAddress.getByName("localhost"),5252);
        datagramSocket.send(datagramPacket);
        System.out.println("sent successfully...");
        datagramSocket.receive(datagramPacket1);
        System.out.println("data received from server=="+ Arrays.toString(datagramPacket1.getData()));
        System.out.println("Address"+datagramPacket1.getAddress() +"Port "+datagramPacket1.getPort());
        System.out.println("Address"+datagramSocket.getLocalAddress() +"Port "+datagramSocket.getPort());
        System.out.println("Is connected="+datagramSocket.isConnected());
    }
}
