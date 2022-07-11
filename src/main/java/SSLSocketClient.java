package main.java;

import javax.net.SocketFactory;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;

public class SSLSocketClient {

    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore","keys.store");
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("127.0.0.1",4444);
            PrintWriter printWriter = new PrintWriter(sslSocket.getOutputStream(),true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            String line = "";
            while (!line.equalsIgnoreCase("closed")) {
                line = bufferedReader.readLine();
                printWriter.println(line);
                System.out.println("Write done from client to Server");
            }
            sslSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    static String startClient(String host, int port) throws IOException {
//        SocketFactory factory = SSLSocketFactory.getDefault();
//
//        try (Socket connection = factory.createSocket(host, port)) {
//            ((SSLSocket) connection).setEnabledCipherSuites(
//                    new String[] { "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256"});
//            ((SSLSocket) connection).setEnabledProtocols(
//                    new String[] { "TLSv1.2"});
//            SSLParameters sslParams = new SSLParameters();
//            sslParams.setEndpointIdentificationAlgorithm("HTTPS");
//            ((SSLSocket) connection).setSSLParameters(sslParams);
//            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            return input.readLine();
//        }
//    }
}
