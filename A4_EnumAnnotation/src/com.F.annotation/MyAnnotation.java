package com.F.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/** 一、自定义注解
 * 1.注解声明关键字 @interface
 * 2.内部定义成员，默认使用 value
 * 3.使用 default 指定默认值
 * 4.若没有成员，则表示为标识作用
 * 5.必须配上注解的信息处理流程（使用反射）。
 *
 *  二、元数据：对现有数据进行修饰的数据
 *  三、元注解：对被声明的注解进行解释说明的注解
 *      > Retention(保留)指定Annotation的生命周期
 *          > RetentionPolicy.SOURCE    编译时舍弃
 *          > RetentionPolicy.CLASS     (默认行为)编译后保留，VM运行时不加载
 *          > RetentionPolicy.RUNTIME   编译后保留，VM运行时加载到内存
 *      > Target        指定Annotation能修饰的程序元素(不指明时默认所有程序元素可修饰)
 *          >ElementType枚举类
 *          > TYPE(类、接口、Enum) FIELD METHOD PARAMETER CONSTRUCTOR LOCAL_VARIABLE
 *      > Documented    被修饰的Annotation将被javadoc工具提取成文档显示
 *      > Inherited     被修饰的Annotation将具有继承性
 *          >如果某个类使用了被Inherited修饰的Annotation，则起子类自动具有该注解
 */
/*
    JDK8 新特性：重复注解
*/
//@Repeatable(目标注解的数组类.class)
//MyAnnotation的Target和Retention 和MyAnnotations相同

/*
    JDK8 新特性：类型注解
*/
// @Target
//  > TYPE_PARAMETER    该注解能写在类型变量的声明语句中（泛型）
//  > TYPE_USE          该注解能写在使用类型的任何语句中

@Target({ElementType.TYPE_PARAMETER,ElementType.TYPE})
@SuppressWarnings("unused")
public @interface MyAnnotation {
    // 成员变量以无参数方法的形式来声明
    String value();
}
