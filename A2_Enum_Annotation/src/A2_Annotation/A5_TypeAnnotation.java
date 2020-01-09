package A2_Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@MyAnnotation   // 类
public class A5_TypeAnnotation<U> {
    @MyAnnotation   // 属性
    private String name;
    public static void main(String[] args) {
        A5_TypeAnnotation<@MyAnnotation String> t1 = null;
        A5_TypeAnnotation<String> t2 = null;
        int a = (@MyAnnotation int) 2L;
        @MyAnnotation
        int b = 10;
    }
    // 泛型方法的泛型参数
    public static <@paraAnnotation T> void method(@MyAnnotation T t) {
    }
    // 参数列表 + 异常声明
    public static void test(@MyAnnotation String arg) throws @MyAnnotation Exception {
    }
}

// TYPE_USE 能写在使用类型的任何语句中
@Target(ElementType.TYPE_USE)
@interface MyAnnotation {
}

@Target(ElementType.TYPE_PARAMETER)
@interface paraAnnotation{
}
