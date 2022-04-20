package com.wanglongbiao.mist.auth.ch03;

import java.util.concurrent.TimeUnit;

/**
 * 默认参数
 * C:\Users\Administrator>jinfo -flags 70604
 * VM Flags:
 * -XX:CICompilerCount=4
 * -XX:InitialHeapSize=268435456
 * -XX:MaxHeapSize=4294967296（4.0 GB 整）
 * -XX:MaxNewSize=1431306240（1365.0 MB）
 * -XX:MinHeapDeltaBytes=524288
 * -XX:NewSize=89128960（85.0 MB）
 * -XX:OldSize=179306496（171.0 MB）
 * -XX:+UseCompressedClassPointers
 * -XX:+UseCompressedOops
 * -XX:-UseLargePagesIndividualAllocation
 * -XX:+UseParallelGC
 */

/**
 * g1 默认参数
 * C:\Users\Administrator>jinfo -flags 70712
 * VM Flags:
 * -XX:CICompilerCount=4
 * -XX:ConcGCThreads=2
 * -XX:G1HeapRegionSize=1048576 -XX:InitialHeapSize=268435456
 * -XX:MarkStackSize=4194304
 * -XX:MaxHeapSize=4294967296
 * -XX:MaxNewSize=2576351232
 * -XX:MinHeapDeltaBytes=1048576
 * -XX:+UseCompressedClassPointers
 * -XX:+UseCompressedOops
 * -XX:+UseG1GC
 * -XX:-UseLargePagesIndividualAllocation
 */
public class G1Testing {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("g1 testing");
        TimeUnit.DAYS.sleep(1);
    }
}
