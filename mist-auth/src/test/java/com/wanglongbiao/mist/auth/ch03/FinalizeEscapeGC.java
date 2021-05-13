package com.wanglongbiao.mist.auth.ch03;

import lombok.SneakyThrows;

public class FinalizeEscapeGC {
    public static FinalizeEscapeGC hook = null;

    @SneakyThrows
    public static void main(String[] args) {
        hook = new FinalizeEscapeGC();
        hook = null;
        System.gc();
        Thread.sleep(1000);
        if (hook != null) {// ?
            hook.isAlive();
        } else {
            System.out.println("no, I'm dead!");
        }

        // second time
        hook = null;
        System.gc();
        Thread.sleep(1000);
        if (hook != null) {
            hook.isAlive();
        } else {
            System.out.println("no, I'm dead at last!");
        }
    }

    void isAlive() {
        System.out.println("yes, I'm still alive.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed.");
        FinalizeEscapeGC.hook = this;
    }
}
