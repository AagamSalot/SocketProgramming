package main.java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class SmallServer {

	public static void main(String[] args) throws IOException {
		
		DatagramSocket ds = new DatagramSocket(5252);
		byte buf[] = new byte[2];
		byte send[] = { 13, 18 };
		DatagramPacket dp = new DatagramPacket(buf, 2);
		ds.setSoTimeout(20000);
		ds.receive(dp);
		System.out.println("Address====="+ dp.getAddress()+"Port===="+dp.getPort());
		System.out.println("Received=="+ Arrays.toString(dp.getData()));
		DatagramPacket senddp = new DatagramPacket(send, 2,
							dp.getAddress(), dp.getPort());
		ds.close();
		ds.send(senddp);
	}

}
