package com.wanglongbiao.mist.auth.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadMonitor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        testBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }

    private static void createLockThread(Object obj) {
        new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread11").start();
    }

    private static void testBusyThread() {
        new Thread(() -> {
            while (true) ;
        }, "testBusyThread12").start();
    }

}
