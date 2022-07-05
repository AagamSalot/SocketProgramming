package main.java;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class ServerDataSocket {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(5252);
        datagramSocket.setReuseAddress(true);
        byte[] receivedByte = new byte[200];
        byte[] sendBytes = {45,35};
        datagramSocket.setSoTimeout(20000);
//        datagramSocket.setSendBufferSize(1);
        DatagramPacket datagramPacket = new DatagramPacket(receivedByte,200);
        datagramSocket.receive(datagramPacket);
//        System.out.println(Arrays.toString(datagramPacket.getData()));
        java.lang.String str= new java.lang.String(datagramPacket.getData());
        System.out.println("str===="+str);
        System.out.println(datagramPacket.getPort());
        DatagramPacket datagramPacketSend = new DatagramPacket(sendBytes,2,InetAddress.getByName("localhost") ,datagramPacket.getPort());
        datagramSocket.send(datagramPacketSend);

    }
}
