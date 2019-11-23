package com.F.StringTest;

import org.junit.Test;

/** String  StringBuffer    StringBuilder三者异同
 * String   不可变的字符序列
 *  > final char[] value
 * StringBuffer 可变的字符序列 1.0since    synchronized  线程安全，效率低 ;    多线程使用
 *  > char[] value  extends AbstractStringBuilder
 * StringBuilder可变的字符序列 5.0新增                   线程不安全,效率高;    非多线程问题使用
 *  > char[] value  extends AbstractStringBuilder

 *  String str = new String();          // new char[0]
 *  String str1 = new String("abc");    // new char[]{'a','b','c'}
 *
 *  StringBuffer sb = new StringBuffer();   // new char[16];  <---super(capacity = 16)
 *  sb.append(char c);
 *  1.StringBuffer默认预留16的字节待添加
 *  StringBuffer sb = new StringBuffer("abc")   // new char["abc".length() + 16]
 *  2.StringBuffer扩容
 *      > 新建StringBuffer扩容为 value.length << 1 + 2 ，同时将原数组复制到新数组中
 *  3. new StringBuffer(int capacity)
 *
 *  增   append()
 *  删   delete()
 *  改   setCharAt() / replace()
 *  查   indexOf()
 *  插   insert()
 *  长度  length()
 *  遍历  for(length(),charAt())
 */

public class StringBufferBuilderTest {
    @Test
    public void StringBufferTest(){
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0,'m');
        System.out.println(sb1);    // 默认toString()
        System.out.println(sb1.length());

    }
}
