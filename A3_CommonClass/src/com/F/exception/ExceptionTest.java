package com.F.exception;

import org.junit.Test;

/**
 * 异常处理：抓抛模型
 * 过程一：程序在执行过程中，一旦出现异常，就会在异常代码处生成一个对应异常类的对象
 *          并将此对象抛出，一旦抛出对象后，其后的代码则不再执行
 *
 * 过程二： (1)try-catch-finally, catch和finally都会出现异常，内部可再嵌套异常出来
 *         (2)throws
 *
 * finally必须放置：数据库连接，输入输出流，网络编程socket等资源，JVM是不能自动回收的
 * 此时资源的释放需要由finally来执行
 */
public class ExceptionTest {
    @Test
    public void testNullPointerException(){
        int[] arr = new int[3];
        try{    // 相当于方法，内部创建的变量具有作用域
            arr[4] = 1;
            return; // 发生异常时不会执行此代码
        }catch (Exception e){
            System.out.println(e.getMessage());
            arr[4] = 1;
//            e.printStackTrace();
        }finally{   // finally一定会在try-catch结构结束前执行 catch中出现异常，finally也一定会被执行
            System.out.println("123");
        }
    }
}
