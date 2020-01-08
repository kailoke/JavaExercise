package a6_OtherStream;

import org.junit.Test;
import java.io.*;

/**
 * 1.标准输入流、标准输出流 (字节流)
 *  > InputStream System.in   标准输入流，默认从键盘输入
 *  > PrintStream System.out  标准输出流，默认从控制台输出
 *  > System 类 setIn(InputStream)/setOut(OutputStream) 修改 in/out 指向
 */


public class A1_SystemIO {
    // 1. System 标准输入流 标准输出流
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            String data = null;

            while (true){
                System.out.print("请输入:");
                try {
                    data = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data) || "退出".equals(data)) {
                    System.out.println("程序结束");
                    break;
                }
                System.out.println("程序输出:" + data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    // 实现输出 a=100,b=100
    public void overridePrintStreamMethod() {
        int a = 10,  b= 10;
        method(a,b);
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }
    // PrintStream匿名实现类，重写println()
    private static void method(int a,int b) {
        PrintStream ps = new PrintStream(System.out) {
            @Override
            public void println(String x) {
                if ("a=10".equals(x)) {
                    x = "a=100";
                }else if ("b=10".equals(x)) {
                    x = "b=100";
                }
                // 调用父类println()
                super.println(x);
            }
        };
        System.setOut(ps);
    }
}
