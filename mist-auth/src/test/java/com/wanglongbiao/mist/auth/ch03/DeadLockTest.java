package com.wanglongbiao.mist.auth.ch03;

public class DeadLockTest {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(8);
        Integer b = Integer.valueOf(8);
        Integer c = Integer.valueOf(128);
        Integer d = Integer.valueOf(128);
        for (int i = 0; i < 100; i++) {
            new Thread(new SyncAddRunnable(1, 2)).start();
            new Thread(new SyncAddRunnable(2, 1)).start();
        }
    }
}

class SyncAddRunnable implements Runnable {
    int a, b;

    public SyncAddRunnable(int a, int b) {
        this.a = a;
        this.b = b;
    }


    @Override
    public void run() {
        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(a + b);
            }
        }
    }
}