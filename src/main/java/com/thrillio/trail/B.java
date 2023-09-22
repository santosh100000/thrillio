package com.thrillio.trail;

public class B extends A {

    public static void main(String[] args) {
//        A instance = new A();

//        String iGotFromClassA = instance.gettable;
//        instance.count = 100;

        A instance1 = new B();
        B instanceOfB  = new B();
        A instanceOfAfromC  = new C();


        System.out.println(instance1 instanceof B);
        instance1.greet();
        instanceOfB.greet();
        instanceOfAfromC.greet();

    }

    @Override
    public  void greet(){
        System.out.println(
                "Hi from B"
        );
    }
}
