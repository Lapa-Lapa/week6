package com.epam.atm.waiters;

public class ThreadSleep {
    public static void waitSetTime(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
