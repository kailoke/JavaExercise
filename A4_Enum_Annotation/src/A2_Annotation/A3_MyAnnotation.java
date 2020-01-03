package A2_Annotation;

import java.lang.annotation.*;

/**
 * 一、自定义注解:
 * > 1.注解声明关键字 @interface，自定义注解自动继承java.lang.annotation.Annotation 接口
 * > 2.Annotation成员变量：以无参数方法的形式来声明
 *      > 方法名:成员名字，返回值：成员的类型，称为配置参数。例：String value();
 *      > 类型只能是：八中基本数据类型、String、Class、enum、Annotation及以上类型的数组
 *      > 使用 default 关键在在定义成员变量时为其制定初始值。例：String value() default xxx;
 *      > 如果只有一个参数成员，建议使用参数名为 value
 * > 3.使用定义的注解若含有配置参数，那么使用时必须指定参数，除非它有默认值。
 *      > 格式： "参数名 = 参数值"
 *      > 如果只有一个参数成员，且名称为value，可以省略 "参数名 ="
 * > 4.没有成员定义的Annotation称为`标记`，包含成员变量的Annotation称为 元数据Annotation
 * > 5.自定义注解必须配上注解的信息处理流程（使用反射）。
 */

@Repeatable(A5_MyAnnotations.class) // 可重复注解
@Inherited
@Target({ElementType.TYPE_PARAMETER,ElementType.TYPE,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface A3_MyAnnotation {
    // 成员变量以无参数方法的形式来声明，返回值(类型) 变量名() default xxx;
    String value() default "hi";
}
