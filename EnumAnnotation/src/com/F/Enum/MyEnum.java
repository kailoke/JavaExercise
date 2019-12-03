package com.F.Enum;

/** 一、枚举类：类的对象是有限个的，且确定的具体对象
 *     > 一组常量 强烈建议使用 枚举类
 *     > 如果枚举类中只有一个对象，可以作为单例模式的一种实现方式
 *  二、定义枚举类
 *  方式一：JDK5.0前自定义枚举类
 *      > final field
 *      > private constructor
 *      > final Object
 *      > getters/toString
 *  方式二：JDK5.0后使用enum关键字定义枚举类
 *
 */
public class MyEnum {
    public static void main(String[] args) {
        Season season1 = Season.SPRING;
        System.out.println(season1);
    }
}


class Season{
    // 1.声明Season类final field，常量属性不允许被修改
    private final String seasonName;
    private final String seasonDesc;
    // 2.私有化构造器
    private Season(String name,String desc) {
        this.seasonName = name;
        this.seasonDesc = desc;
    }
    // 3.提供枚举类的多个对象
    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","白雪皑皑");
    // 4.获取枚举类对象的属性，toString 方法
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
