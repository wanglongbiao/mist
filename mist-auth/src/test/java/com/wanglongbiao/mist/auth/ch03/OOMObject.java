package com.wanglongbiao.mist.auth.ch03;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OOMObject {
    public static void main(String[] args) {
        fillHeap(2000);
    }

    /**
     * -Xms100m -Xmx100m -XX:+UseSerialGC
     * @param num
     */
    @SneakyThrows
    private static void fillHeap(int num) {
        ArrayList<Test1> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(new Test1());
            Thread.sleep(500);
        }
        System.gc();
    }
}

class Test1{
    public byte[] placeholder= new byte[64*1024];
}
