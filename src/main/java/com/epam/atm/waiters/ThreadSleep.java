package com.epam.atm.waiters;

public class ThreadSleep {
    public static void waitElement(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
