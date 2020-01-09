package A2_Annotation;

/** JDK8 元注解新特性
 * 五、重复注解 ： 同一个注解可多次修饰一个结构
 *  > 使用方法 ： @Repeatable(包含基本注解数组的注解.class)
 *  > 要求: 基本注解 和 包含基本注解数组类的注解的 Retention && Target一致
*/
public class A3_AnnotationTest {
    public static void main(String[] args) {
    }
}

// 三、注解，含有成员变量，类似方法的使用需要指定参数列表
@A3_MyAnnotation("hello")
class Person{
    private String name;
    private int age;

    // 五.1(注解数组)  < JDK8 : 使用{注解数组}作为参数 实现一个注解重复使用多个参数
    @A5_MyAnnotations({@A3_MyAnnotation("1"),@A3_MyAnnotation("1")})
    public Person() {
    }
    // 五.2(重复注解)
    @A3_MyAnnotation("1")
    @A3_MyAnnotation("2")
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// 继承测试
class Student extends Person{
    public Student() {
    }

    public Student(String name, int age) {
        super(name, age);
    }
}