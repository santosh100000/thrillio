package com.thrillio.util;

import java.io.*;

public class Tryyy {

    public static void main(String[] args) {
        copyPhoto();
        try {
            File imageFile = new File("santosh.jpg");
            FileInputStream input = new FileInputStream(imageFile);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    String binary = String.format("%8s", Integer.toBinaryString(buffer[i] & 0xFF)).replace(' ', '0');
                    System.out.println(binary);
                }
            }

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyPhoto(){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("santosh.jpg")));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("newSantosh")))

        ){
            int read;
            while ((read = in.read()) != -1){
                System.out.println("I called");
                out.write(read);
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
