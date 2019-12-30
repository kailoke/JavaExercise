package A8_Exception;

/** 异常:程序执行过程中发生不正常的情况，例外，导致程序无法继续运行
 *  一、包类结构:
 *      Throwable
 *        ---- Error:Java虚拟机无法解决的严重问题，一般不由程序员处理
 *        ---- Exception:编程错误或偶然外因导致的一般性问题，需要处理，目前仅针对此进行处理
 *  二、异常(Exception)分类：
 *      > 编译时异常(checked)    除Error类 && RuntimeException及其子类外的所有异常类
 *        > 程序员必须处理编译时异常，将异常捕捉转化为运行时异常
 *      > 运行时异常(Unchecked)  Error类 && RuntimeException及其子类
 *        > 程序员可以不用处理运行时异常，java虚拟机将自动捕获
 *  三、解决方式
 *      > 1. try-catch-finally抓抛模型：捕获到方法内调用方法返回的异常后，调用者进行处理
 *      > 2. throws + 异常类型 的方法声明：调用者不处理，将其内部可能产生或捕获的异常抛给更上层的其他调用者进行处理
 *  四、异常“对象”
 *      > 抛出(throw)异常
 *           java程序执行过程中如果出现异常，会生成一个"异常类对象"，该异常对象将被提交给Java运行时系统
 *        > 1. 自动生成：JAVA虚拟机检测到异常，并在当前代码中没有找到相应的处理程序
 *                    就在后台自动创建一个对应异常类的实例对象并抛出
*         > 2. 手动创建：Exception exception = new Exception();异常对象不抛出不会对程序产生任何影响
 *      > 异常对象的方法
 *        > 1. String getMessage() : 获取Throwable异常根父类的 detailMessage字符串
 *        > 2. StackTraceElement[] printStackTrace() : 获得Throwable内部静态类SentinelHolder的StackTraceElement[]
 *  五、自定异常类
 *      > 1. 必须继承自现有异常类，一般继承RuntimeException
 *      > 2. 必须提供 serialVersionUID，因Throwable implements Serializable，子类需要提供不同的UID
 */
public class A1_Exception {
    public static void main(String[] args) {
        Exception exception = new Exception("123");
        exception.getStackTrace();
    }
}
