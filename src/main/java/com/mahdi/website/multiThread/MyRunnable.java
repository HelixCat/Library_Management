package com.mahdi.website.multiThread;

public class MyRunnable implements Runnable{

    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    @Override
    public void run() {
        while(keepRunning()) {

            System.out.println("Running");
            try {
                Thread.sleep(5L * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("this is it!!!");
    }
}
