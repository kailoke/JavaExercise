package com.F.threadtest;

public class StringTest {
    public static void main(String[] args) {
        String str = "hello";
        String str2 = new String(str);

        String str1 = str.intern();
        String str3 = str2.intern();
        System.out.println(str == str1);
        System.out.println(str == str3);
    }
}
