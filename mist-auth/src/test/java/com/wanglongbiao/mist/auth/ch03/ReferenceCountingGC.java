package com.wanglongbiao.mist.auth.ch03;

import java.util.concurrent.TimeUnit;

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
         * jdk 16:
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
        /**
         * jdk 8:
         * main start
         * 1
         * [GC (Allocation Failure) [PSYoungGen: 6573K->992K(9216K)] 6573K->1738K(19456K), 0.0019632 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * 2
         * 3
         * 4
         * 5
         * main end
         * Heap
         *  PSYoungGen      total 9216K, used 8808K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         *   eden space 8192K, 95% used [0x00000000ff600000,0x00000000ffda1f00,0x00000000ffe00000)
         *   from space 1024K, 96% used [0x00000000ffe00000,0x00000000ffef83e8,0x00000000fff00000)
         *   to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
         *  ParOldGen       total 10240K, used 4841K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         *   object space 10240K, 47% used [0x00000000fec00000,0x00000000ff0ba528,0x00000000ff600000)
         *  Metaspace       used 4040K, capacity 4572K, committed 4864K, reserved 1056768K
         *   class space    used 442K, capacity 460K, committed 512K, reserved 1048576K
         *
         * Process finished with exit code 0
         */
    }

    public static void main(String[] args) throws InterruptedException {
//        ReferenceCountingGC.testGC();
        System.out.println("main start");
        ReferenceCountingGC.testAllocation();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("main end");
    }
}
