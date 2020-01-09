package A1_Enum;

/** 枚举类 : 内部值对象不可变的数据结构
 * 一、适用情况
 *  > 类的对象是有限个的，且确定的具体对象    !final!
 *  > 一组常量 强烈建议使用 枚举类           !final!
 *  > 如果枚举类中只有一个对象，可以作为单例模式的一种实现方式(感觉不实用)
 *
 * 二、枚举类的属性，对象的创建注意 final 和 枚举值直接使用了构造器
 *  > 枚举类的`对象的属性`应使用 private final，不应允许被改动
 *  > private final的属性应在构造器中赋值，每个类对象赋值创建后即不可修改其内部属性
 *  > 若枚举类显示定义了有参构造，则枚举值也必须对应传入参数
 *
 * 三、自定义枚举类
 *  > 1. private constructor，保证不能在类的外部创建其对象
 *  > 2. public static final 枚举类的实例，通过类访问静态属性
 *  > 3. private final 实例变量，构造器中初始化
 */


public class A1_MyEnum {
    public static void main(String[] args) {
        MySeason mySeason1 = MySeason.SPRING;
        MySeason mySeason2 = MySeason.SUMMER;
        MySeason mySeason3 = MySeason.AUTUMN;
        MySeason mySeason4 = MySeason.WINTER;
        System.out.println(mySeason1);
        System.out.println(mySeason2);
        System.out.println(mySeason3);
        System.out.println(mySeason4);
    }
}

// 自定义枚举类
class MySeason {
    // 3.声明Season类final field，常量属性不允许被修改
    private final String seasonName;
    private final String seasonDesc;
    // 1.私有化构造器
    private MySeason(String name, String desc) {
        this.seasonName = name;
        this.seasonDesc = desc;
    }
    // 2.提供枚举类的多个对象
    public static final MySeason SPRING = new MySeason("春天","春暖花开");
    public static final MySeason SUMMER = new MySeason("夏天","夏日炎炎");
    public static final MySeason AUTUMN = new MySeason("秋天","秋高气爽");
    public static final MySeason WINTER = new MySeason("冬天","白雪皑皑");

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
