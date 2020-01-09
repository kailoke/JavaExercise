package A1_StringRelevant;

import org.junit.Test;

/**
 *  String强化练习
 */
public class A1_StringExercise {

    // 1.模拟trim()，去除字符串头尾空格 : 取非头尾空格的子串 String.substring(prefix,offset)
    @Test
    public void simulateTrim(){
        String str = "   H ello worl d   ,   ";

        int offset = 0;
        while(str.startsWith(" ",offset)){
            ++offset;
        }
        String str_start = str.substring(offset);
        System.out.print("str_start:" + str_start);
        System.out.println("****Test****");
        offset = str_start.length() - 1;
        while (str_start.charAt(offset) == ' '){
            --offset;
        }
        String str_end = str_start.substring(0,++offset);
        System.out.print("str_end:" + str_end);
        System.out.println("****Test****");
    }

    // 2.对字符串指定区间位置进行字符反转 : 反转字符数组 String.toCharArray()
    @Test
    public void simulateReverse(){
        String str = "abcdefg";
        int start = 1;
        int end = 5;

        char[] charArr = str.toCharArray();
        while (start < end) {
            char temp = charArr[start];
            charArr[start++] = charArr[end];
            charArr[end--] = temp;
        }
        System.out.println(new String(charArr));
    }

    // 3.获得substring在string中出现的次数 : indexOf()，内部暴力匹配
    @Test
    public void findTimes(){
        String str = "abkkcadkabkebfkabkskab";
        String s = "ab";

        int offset = 0;
        int count = 0;

        while (true){
            int index = str.indexOf(s, offset);
            if( index == -1){
                break;
            }
            offset = index + s.length();
            count++;
        }
        System.out.println("出现次数：" + count);
    }

    // 4.获得两个字符串中最大相同子串 : String.contains(String str)
    @Test
    public void getMostSimulation(){
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";

        int count = 0;  //长度递减次数，可用 sub.length()-1代替，循环削减sub长度从而削减sub_max
        int sub_start = 0;
        int sub_end = str2.length();
        int sub_max = str2.length();
        String sub = "";
        boolean flag = false;

        while (sub_start < sub_max) {
            System.out.println("目前sub最大长度:" + sub_max);
            while (sub_start <= sub_end - sub_max) {
                System.out.println("子过程，起始:" + sub_start + "\t结束:" + sub_max);
                sub = str2.substring(sub_start++, sub_max++);
                if (str1.contains(sub)) {
                    flag = true;
                    break;
                }
//                sub_start++;
//                sub_max++;
            }
            count++;
            if (flag) break;
//            sub_max = sub_end-count;
            sub_max = sub.length() - 1;
            sub_start = 0;
        }
        System.out.println(sub);
    }
}


