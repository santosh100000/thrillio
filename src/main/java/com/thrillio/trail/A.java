package com.thrillio.trail;

public class A {
    private String trys = "I am trying";
    public int count = 12;
    public String gettable = "I am public";

    public static int counts;

    public A (){
        counts++;
        System.out.println(" I am called");
    }



    public  void greet(){
        System.out.println(
                "Hi from A"
        );
    }
}
