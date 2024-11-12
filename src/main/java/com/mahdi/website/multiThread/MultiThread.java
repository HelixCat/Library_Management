package com.mahdi.website.multiThread;

public class MultiThread {

    public static void main(String[] args) {


        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable, "t1");

        thread.start();

        myRunnable.doStop();
    }
}
