package A1_StringRelevant;

import org.junit.Test;

/** String  StringBuffer    StringBuilder三者异同
 * 类声明，StringBuffer和StringBuilder比String多继承 AbstractStringBuilder
 * 一、String
 *  > 1. 不可变的字符序列 : final char[] value
 * 二、StringBuffer     JDK1.0
 *  > 1. 可变的字符序列 : char[] value && extends AbstractStringBuilder
 *  > 2. synchronized所有方法：线程安全，效率低   多线程使用
 * 三、StringBuilder`   JDK5.0
 *  > 1. 可变的字符序列 : char[] value && extends AbstractStringBuilder
 *  > 2. 线程不安全,效率高  非多线程问题使用
 *
 * 1.StringBuffer() 申请空间 = str.length + 16
 *  StringBuffer sb = new StringBuffer();       // new char["".length() + 16];
 *  StringBuffer sb = new StringBuffer("abc")   // new char["abc".length() + 16]
 *
 * 2.StringBuffer扩容 newCapacity()
 *      > 新建StringBuffer扩容为 (value.length << 1) + 2 ，同时将原数组复制到新数组中
 *
 * 3. new StringBuffer(int capacity) : 可提前一次性申请足够空间，减少扩容次数
 *
 * > 常用方法:
 *  增   append(String str)
 *  删   delete(int start,int end)
 *  改   setCharAt(int i,char ch) / replace(int start,int end,String replacement)
 *  查   indexOf(String str)
 *  插   insert(int offset,String str)
 *  长度  length()
 *  遍历  for(length(),charAt())
 *  反转  reverse() : 反转当前字符序列
 */

public class A2_StringBuilder_Buffer {
    @Test
    public void StringBufferTest(){
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0,'m');
        sb1.append("d");

        System.out.println(sb1);    // 默认toString()
        System.out.println(sb1.length());
    }

    @Test
    // append() 方法针对null值特殊优化，估计是为了保留"null"串持久化方便
    public void stringInterview() {
        String strNull = null;
        StringBuffer sb1 = new StringBuffer();
        sb1.append(strNull);     // if str==null appendNull()    特殊优化
        System.out.println(sb1.length());    // 4
        System.out.println(sb1);             // "null"

        String str = new String(strNull);    // NullPointer

        StringBuffer sb2 = new StringBuffer(strNull);   // NullPointer
        System.out.println(sb2);
    }

    @Test
    // 测试 字符串相关的修改 效率 : StringBuilder > StringBuffer >>>>>> String
    public void testEfficient() {
        //初始设置
        long startTime = 0L;
        long endTime = 0L;
        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        //开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text = text + i; }
        endTime = System.currentTimeMillis();
        long stringTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(i);
        }
        endTime = System.currentTimeMillis();
        long bufferTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(i);
        }
        endTime = System.currentTimeMillis();
        long builderTime = endTime - startTime;

        System.out.println("String的执行时间：" + stringTime);
        System.out.println("StringBuffer的执行时间：" + bufferTime);
        System.out.println("StringBuilder的执行时间：" + builderTime);
    }
}
