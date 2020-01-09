package A1_Enum;

import java.time.format.FormatStyle;

/** enum 关键词定义 枚举类
 * 一、根父类：java.lang.Enum，`自动`继承，因为enum定义的类不能再继承其他类，可实现接口
 * 二、构造器：枚举类的构造器只能用private 权限修饰符
 * 三、所有实例：必须在枚举类中显示列出( ,分隔   ;结尾)。 `自动`修饰符 public static final
 *             枚举类对象的声明 必须在 枚举类的第一行
 * 四、实现：和class一样，enum可以实现多个接口
 *  > 1. 若每个枚举值都调用相同的实现方法，则让枚举类统一实现该方法即可
 *  > 2. 可在枚举值的声明后，使用 { } 声明此枚举值单独重写的方法
 *
 * 五、Enum类常用方法
 *    > values()      返回枚举类型的对象数组
 *    > valueOf(str)  返回与str匹配的枚举类对象，若没有此str符合对象则抛出IllegalArgumentException
 *
 * 三、枚举类实现接口的情况
 *  > 公共实现抽象方法
 *  > 枚举类对象声明语句分别实现抽象方法
 */

public class A2_Enum {
    public static void main(String[] args) {
        Season season = Season.SPRING;
        // 枚举值 其实是 匿名内部类的实现
        System.out.println("枚举类对象类型：" + season.getClass());
        System.out.println("枚举类对象的父类：" + season.getClass().getSuperclass());
        System.out.println("枚举类对象父类的父类：" + season.getClass().getSuperclass().getSuperclass());
        System.out.println("Season<SPRING>.toString() : " + season);

        // 2. 枚举类的遍历
        // values() 返回对象的数组列表
        Season[] values1 = Season.values();
        for (Season value : values1) {
            System.out.println(value);
            value.show();
        }

        // foreach (java.time.FormatStyle)
        FormatStyle[] values2 = FormatStyle.values();
        for (FormatStyle fs: values2) {
            System.out.print(fs + "\t");
        }

        // valueOf()
        System.out.println("\n*************************");
        Season winter = Season.valueOf("WINTER");
        winter.show();
    }
}

interface Info{
    void show();
}

// enum 关键字创建枚举类
enum Season implements Info{
    /** 3.提供枚举类的具体对象
     *  > 忽略左侧 声明关键词 类名，忽略右侧 new 构造器词 : 对象名(构造器参数){重写方法}
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
    WINTER("冬天","白雪皑皑");
//    {
//        @Override
//        public void show() {
//            System.out.println("大约在冬季");
//        }
//    };

    // 2.声明Season类final field，常量属性不允许被修改
    private final String seasonName;
    private final String seasonDesc;
    // 1.私有化构造器，自动private
    Season(String name, String desc) {
        this.seasonName = name;
        this.seasonDesc = desc;
    }

    // 统一实现
    @Override
    public void show() {
        System.out.println("Enum类统一实现");
    }
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';

    }
}
