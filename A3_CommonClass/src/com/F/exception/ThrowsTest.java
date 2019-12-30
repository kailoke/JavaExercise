package com.F.exception;

import org.hamcrest.Condition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ThrowsTest {
    public static void main(String[] args) {
        ThrowsTest t = new ThrowsTest();
        try {
            t.method2();
        }catch (Exception e){
            System.out.println("异常：" + e);
        }
    }
    public void method2()throws Exception{
        method1();
    }


    public void method1() throws IOException {
        File file = new File("./hello.txt");
        FileInputStream fis = new FileInputStream(file);
        fis.close();
    }
}
