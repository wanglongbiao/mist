package com.wanglongbiao.mist.auth.ch02;

/**
 * 堆上可能有多个 "java" 字符串实例，而 intern 只记录第一个实例的引用
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);// true

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);// false
        System.out.println(str2.intern().equals(str2));// true
    }
}
