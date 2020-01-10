package A7_Exception;

/**
 * 自定义异常类
 * 一、继承 Exception(编译时异常) 或 RuntimeException(运行时异常)
 *  > 1. 必须继承自现有异常类，一般继承RuntimeException
 *  > 2. 必须提供 serialVersionUID，因Throwable implements Serializable，子类需要提供不同的UID
 *  > 3. 提供默认构造器，至少提供有 Class(String msg)的构造器
 *                     ↑↑ 调用 super(String) 以存储throw时的自定义消息
 */
public class A3_MyException extends RuntimeException{
    static final long serialVersionUID = -3727137721748219L;

    public A3_MyException(){

    }

    public A3_MyException(String msg){
        super(msg);
    }

}
