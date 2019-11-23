package com.F.exception;

/**
 * 自定义异常类
 * 1.继承 Exception(编译时异常) 或 RuntimeException(运行时异常)
 * 2.序列号
 * 3.提供构造器,至少重载有String msg 的构造器
 */
public class MyException extends RuntimeException{
    static final long serialVersionUID = -3727137721748219L;

    public MyException(){

    }

    public MyException(String msg){
        super(msg);
    }

}
