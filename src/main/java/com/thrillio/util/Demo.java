package com.thrillio.util;

import java.io.*;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        gettingInputFromConsole();
    }


    public static void readFile(String [] data, String filename){
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-16"))
                ) {
            String line;
            int count =0;
            while( (line= in.readLine())!=null ){
                data[count++ ] =line;
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Taking input from console
    public static void gettingInputFromConsole(){
        System.out.println("Enter \n start to stop ");
        String data;
//        try(BufferedReader reader = new BufferedReader( new InputStreamReader((System.in), "UTF-8"))) {
//
//            while ((data = reader.readLine())!=null && !data.equals("start")){
//                System.out.println("You haven't put start");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //implenting with scanner
        Scanner scanner = new Scanner(System.in);
        while (!(data=scanner.next()).equals("start")){
            System.out.println("You haven't put start");
        }
        System.out.println("Correct");
    }
}
