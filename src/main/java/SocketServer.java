package main.java;// A Java program for a Server
import java.net.*;
import java.io.*;

public class SocketServer
{
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(3000);
		System.out.println("waiting for client to send messages");
		Socket socket = serverSocket.accept();
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		DataInputStream dataInputStream1 = new DataInputStream(System.in);

		Thread t1 = new Thread() {
			@Override
			public void run() {
				String line = "";
				while (!line.equalsIgnoreCase("closed")) {
					try {
						line = dataInputStream.readUTF();
						System.out.println(line);

					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
				try{
					dataInputStream.close();
					socket.close();
					serverSocket.close();
				} catch(IOException e){
					throw new RuntimeException(e);
				}
			}
		};

		Thread t2 = new Thread(){
			@Override
			public void run() {
				String line = "";
				while (!line.equalsIgnoreCase("closed")) {
					try {
						line = dataInputStream1.readLine();
						System.out.println("writing to client==" + line);
						dataOutputStream.writeUTF(line);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		};

		t1.start();
		t2.start();

	}
}
