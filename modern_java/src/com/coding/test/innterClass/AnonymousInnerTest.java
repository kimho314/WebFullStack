package com.coding.test.innterClass;

class Outer2{

    int outNum = 100;
    static int sNum = 200;

    Runnable getRunnable(int i){
        int num = 10;

//        class MyRunnable implements Runnable{
//
//            int localNum = 1000;
//
//            @Override
//            public void run() {
//
////                i = 450;
////                num = 20;
//
//                System.out.println("i = " + 1);
//                System.out.println("num = " + num);
//                System.out.println("localNum = " + localNum);
//
//                System.out.println("outNum = " + outNum + "(외부 클래스 인스턴스 변수)");
//                System.out.println("Outer.sNum = " + Outer2.sNum + "(외부 클래스 정적 변수)");
//            }
//        }
//        return new MyRunnable();

        return new Runnable() {
            int localNum = 1000;
            @Override
            public void run() {
                System.out.println("i = " + 1);
                System.out.println("num = " + num);
                System.out.println("localNum = " + localNum);

                System.out.println("outNum = " + outNum + "(외부 클래스 인스턴스 변수)");
                System.out.println("Outer.sNum = " + Outer2.sNum + "(외부 클래스 정적 변수)");
            }
        };
    }
}

public class AnonymousInnerTest {

    public static void main(String[] args) {
	// write your code here
        Outer2 out = new Outer2();
        Runnable runnable = out.getRunnable(100);

        runnable.run();
    }
}
