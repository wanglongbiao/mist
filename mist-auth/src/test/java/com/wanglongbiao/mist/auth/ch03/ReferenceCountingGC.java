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

    /**
     * -verbose:gc -XX:+PrintGCDetails -Xms20m -Xmx20m -Xmn10m
     */
    public static void testAllocation() {
        System.out.println("1");
        byte[] allocation1 = new byte[2 * _1MB];
        System.out.println("2");
        byte[] allocation2 = new byte[2 * _1MB];
        System.out.println("3");
        byte[] allocation3 = new byte[2 * _1MB];
        System.out.println("4");
        byte[] allocation4 = new byte[4 * _1MB];
        System.out.println("5");
        /**
         * [0.002s][info][gc] Using G1
         * [0.144s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 10M->7M(20M) 3.451ms
         * 1
         * [0.147s][info][gc] GC(1) Pause Young (Concurrent Start) (G1 Humongous Allocation) 7M->7M(20M) 1.747ms
         * [0.147s][info][gc] GC(2) Concurrent Cycle
         * 2
         * 3
         * [0.148s][info][gc] GC(3) Pause Young (Normal) (G1 Humongous Allocation) 13M->13M(20M) 0.248ms
         * [0.151s][info][gc] GC(4) Pause Full (G1 Humongous Allocation) 13M->13M(20M) 3.005ms
         * [0.151s][info][gc] GC(2) Concurrent Cycle 4.206ms
         * 4
         * [0.152s][info][gc] GC(5) Pause Young (Concurrent Start) (G1 Humongous Allocation) 16M->16M(20M) 0.256ms
         * [0.152s][info][gc] GC(6) Concurrent Cycle
         * [0.152s][info][gc] GC(7) Pause Young (Normal) (G1 Humongous Allocation) 16M->16M(20M) 0.115ms
         * [0.154s][info][gc] GC(8) Pause Full (G1 Humongous Allocation) 16M->16M(20M) 1.671ms
         * [0.155s][info][gc] GC(9) Pause Full (G1 Humongous Allocation) 16M->16M(20M) 1.618ms
         * [0.155s][info][gc] GC(6) Concurrent Cycle 3.683ms
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         */
    }

    public static void main(String[] args) {
//        ReferenceCountingGC.testGC();
        System.out.println("m1");
        ReferenceCountingGC.testAllocation();
        System.out.println("m2");
    }
}
