package com.F.exception;

/**
 * 手动抛出异常
 * throw 异常对象
 */
public class ThrowTest {
    int age;

    public static void main(String[] args) {
        ThrowTest tt = new ThrowTest();
        try {
            tt.throwO(0);
        }catch (Exception e){
            System.out.println(e);
        }
//        tt.throwO(10);
    }

    public void throwO(int age){
        if(age>0){
            this.age = age;
        }else {
            throw new MyException("不能输入负数");
        }

    }
}
