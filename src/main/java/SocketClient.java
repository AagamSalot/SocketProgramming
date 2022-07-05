package main.java;// A Java program for a Client
import java.net.*;
import java.io.*;

public class SocketClient
{
    public static void main(String[] args) throws IOException {
       Socket socket = new Socket("127.0.0.1",3000);
       DataInputStream dataInputStream = new DataInputStream(System.in);
       DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream1 = new DataInputStream(socket.getInputStream());

       Thread t1 = new Thread(new Runnable() {
           @Override
           public void run() {
               String line= "";
               while(!line.equalsIgnoreCase("closed")) {
                   try {
                       line = dataInputStream.readLine();
                       dataOutputStream.writeUTF(line);

                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
               try {
                   dataOutputStream.close();
                   dataInputStream.close();
                   socket.close();
               }catch (IOException e) {
                   throw new RuntimeException(e);
               }
               }
       });

       Thread t2 = new Thread(new Runnable() {
           @Override
           public void run() {
               String line="";
               while(!line.equalsIgnoreCase("closed")){
                   try {
                       line = dataInputStream1.readUTF();
                       System.out.println("reading=="+line);
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
           }
       });

       t1.start();
      t2.start();


    }
}
