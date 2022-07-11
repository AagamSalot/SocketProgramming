package main.java;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class SSLSocketServer {

    public static void main(String[] args) throws IOException {

        System.setProperty("javax.net.ssl.keyStore","keys.store");
        System.setProperty("javax.net.ssl.keyStorePassword","changeit");
        ServerSocketFactory factory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        ServerSocket serverSocket = factory.createServerSocket(4444);
        System.out.println("server ready for connection....");
        Socket socket = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));
        String  line = "";

        while (!line.equalsIgnoreCase("closed")) {
            line = bufferedReader.readLine();
            System.out.println("reading====" + line);
        }
        socket.close();
        serverSocket.close();
        //        startServer(8443);
    }

//    static void startServer(int port) throws IOException {
//
//
//        try (ServerSocket listener = factory.createServerSocket(port)) {
//            ((SSLServerSocket) listener).setNeedClientAuth(true);
//            ((SSLServerSocket) listener).setEnabledCipherSuites(
//                    new String[] { "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256"});
//            ((SSLServerSocket) listener).setEnabledProtocols(
//                    new String[] { "TLSv1.2"});
//            while (true) {
//                try (Socket socket = listener.accept()) {
//                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//                    out.println("Hello World!");
//                }
//            }
//        }
//    }



}
