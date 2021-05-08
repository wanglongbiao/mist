package com.wanglongbiao.mist.auth;

import lombok.SneakyThrows;

/**
 * -Xss136k
 */
public class JavaVMStackSOF {
    private int length = 1;

    public void stackLeak() {
        length++;
        long a,b,c,d;
        stackLeak();
    }

    @SneakyThrows
    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("length " + oom.length);
//            throw e;
        }
    }
}
