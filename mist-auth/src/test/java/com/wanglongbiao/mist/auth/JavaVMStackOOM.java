package com.wanglongbiao.mist.auth;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * -Xss2m
 * cpu 100% 功耗+50w 挺费电的
 */
public class JavaVMStackOOM {
    public static void main(String[] args) {
        new JavaVMStackOOM().stackLeakByThread();
    }

    @SneakyThrows
    void dontStop() {
        TimeUnit.DAYS.sleep(1);
//        while (true) {
//        }
    }

    void stackLeakByThread() {
        while (true) {
            new Thread(this::dontStop).start();
        }
    }
}
