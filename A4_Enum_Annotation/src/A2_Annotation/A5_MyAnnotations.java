package A2_Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JDK8以前，注解内使用 注解数组 来实现 一个注解的重复修饰
 */

@Target({ElementType.TYPE_PARAMETER,ElementType.TYPE,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface A5_MyAnnotations {
    A3_MyAnnotation[] value();
}
