package main.java;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CipherStreamExample {

    public static void main(final String[] args) {

        final CipherStreamExample cip = new CipherStreamExample();
        cip.runExperiments();

    }

    private void runExperiments() {

        CipherOutputStream output = null;
        CipherInputStream input = null;
        FileOutputStream fileOutput = null;
        FileInputStream fileInput = null;

        try {

            fileOutput = new FileOutputStream("CipherOutput.txt");
            fileInput = new FileInputStream("CipherOutput.txt");

            final KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(new SecureRandom(new byte[]{1, 2, 3}));
            final SecretKey key = kg.generateKey();

            final Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key);
            output = new CipherOutputStream(fileOutput, c);

            final PrintWriter pw = new PrintWriter(output);
            pw.println("Cipher Streams are working correctly.");
            pw.flush();
            pw.close();



            final KeyGenerator kg2 = KeyGenerator.getInstance("AES");
            kg2.init(new SecureRandom(new byte[]{1, 2, 3}));
            final SecretKey key2 = kg2.generateKey();

            final Cipher c2 = Cipher.getInstance("AES");
            c2.init(Cipher.DECRYPT_MODE, key);
            input = new CipherInputStream(fileInput, c2);

            final InputStreamReader r = new InputStreamReader(input);
            final BufferedReader reader = new BufferedReader(r);
            final String line = reader.readLine();
            System.out.println("Line : " + line);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Specified Algorithm does not exist");
        } catch (NoSuchPaddingException e) {
            System.out.println("Specified Padding does not exist");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find specified file to read / write to");
        } catch (InvalidKeyException e) {
            System.out.println("Specified key is invalid");
        } catch (IOException e) {
//            System.out.println("IOException from BufferedReader when reading file");
            e.printStackTrace();
        } finally {
            try {
                if (fileInput != null) {
                    fileInput.close();
                }
                if (fileOutput != null) {
                    fileOutput.flush();
                    fileOutput.close();
                }
                if (output != null) {
                    output.flush();
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}