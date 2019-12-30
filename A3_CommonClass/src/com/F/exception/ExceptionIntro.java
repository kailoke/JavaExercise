package com.F.exception;

import org.junit.Test;

/**
 * 一、异常体系结构
 *  根类：java.lang.Throwable
 *      >class java.lang.Error:不处理
 *      >class java.lang.Exception
 *          >!checked exception,编译时异常
 *              > IOException
 *                  >FileNotFoundException
 *              > ClassNotFoundException
 *          >!unchecked exception,运行时异常
 *              >NullPointerException
 *              >ArrayIndexOutOfBoundsException
 *              >...
 *              >...
 *              !>RuntimeException
 *
 *
 *
 */
public class ExceptionIntro {

    // NullPointerException
    @Test
    public void testNullPointerException(){
        int[] arr = null;
        arr[0] = 1;
    }
}
