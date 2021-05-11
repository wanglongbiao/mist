package com.wanglongbiao.mist.auth.ch03;

public class ReferenceCountingGC {
    private static final int _1MB = 1024 * 1024;
    private Object instance = null;
    private byte[] bigSize = new byte[10 * _1MB];

    public static void testGC() {
        ReferenceCountingGC obj1 = new ReferenceCountingGC();
        ReferenceCountingGC obj2 = new ReferenceCountingGC();
        obj1.instance = obj2;
        obj2.instance = obj1;
        obj1 = null;
        obj2 = null;
//        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }
}
