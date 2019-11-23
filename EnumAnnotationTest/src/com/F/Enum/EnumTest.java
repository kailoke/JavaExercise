package com.F.Enum;

import java.time.format.FormatStyle;

/** enum 关键词定义 枚举类
 * 一、!枚举类的根类是 java.lang.Enum    ;  非Object类
 *
 * 二、Enum类常用方法
 *    > toString()    返回对象名
 *    > values()      返回枚举类对象数组列表
 *    > valueOf(str)  返回于str相同名的对象，若没有此str符合对象则抛出Exception
 *
 * 三、枚举类实现接口的情况
 *  > 公共实现抽象方法
 *  > 枚举类对象声明语句分别实现抽象方法
 */

public class EnumTest {
    public static void main(String[] args) {
        Season1 season = Season1.SPRING;
        System.out.println(season.getClass().getSuperclass());
        System.out.println(season);

        // values() 返回对象的数组列表
        Season1[] values = Season1.values();
        for (Season1 value : values) {
            System.out.println(value);
            value.show();
        }
        // foreach ( 类 i:变量)
        FormatStyle[] values1 = FormatStyle.values();
        for (FormatStyle sf: values1) {
            System.out.println(sf);
        }

        // valueOf()
        System.out.println("*************************");
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);
    }
}

interface Info{
    void show();
}

// enum 类中的常量均为本类对象
enum Season1 implements Info{
    /** 1.提供枚举类的具体对象
     *  > 忽略左侧 声明关键词 类名，忽略右侧 new 构造器词
     *  > 直接声明 对象名(构造参数),对象名(构造参数),对象名(构造参数); ;结束对象句声明
     */
    SPRING("春天","春暖花开"){
        @Override
        public void show() {
            System.out.println("2002年的第一场雪");
        }
    },
    SUMMER("夏天","夏日炎炎") {
        @Override
        public void show() {
            System.out.println("宁夏");
        }
    },
    AUTUMN("秋天","秋高气爽") {
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天","白雪皑皑") {
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };

    // 2.声明Season类final field，常量属性不允许被修改
    private final String seasonName;
    private final String seasonDesc;
    // 3.私有化构造器
    Season1(String name, String desc) {
        this.seasonName = name;
        this.seasonDesc = desc;
    }

    // 4.获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }

    // toString方法在Enum类中定义为输出对象名
//    @Override
//    public String toString() {
//        return "Season{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//
//    }
}
