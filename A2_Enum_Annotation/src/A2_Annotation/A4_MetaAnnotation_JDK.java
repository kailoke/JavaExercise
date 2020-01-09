package A2_Annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/** 元注解：对现有的注解(结构)进行解释说明的注解，仅能修饰注解
 * 一、JDK5.0提供的4个标准meta-annotation:
 *  > 1. @Retention : 指明修饰的Annotation的生命周期
 *      > RetentionPolicy下的枚举值:
 *      > RetentionPolicy.SOURCE    保留至源文件中，编译时舍弃
 *      > RetentionPolicy.CLASS     保留至字节码文件中，VM运行时不加载。Retention默认值
 *      > RetentionPolicy.RUNTIME   VM运行时加载到内存。程序可以通过反射获取注释
 *  > 2. @Target : 指定修饰的Annotation能修饰的程序元素(不指明时默认所有程序元素可修饰)
 *      > ElementType枚举类下的枚举值:
 *      > TYPE(类、接口、Enum) FIELD(属性) METHOD(方法) PARAMETER(参数) CONSTRUCTOR(构造器) LOCAL_VARIABLE(局部变量)
 *  > 3. @Documented : 被修饰的Annotation将被javadoc工具提取成文档显示
 *  > 4. @Inherited : 被修饰的Annotation将具有继承性
 *      > 若某个父类被具有Inherited的Annotation修饰，则其子类自动具有父类的注解
 */
public class A4_MetaAnnotation_JDK {
    public static void main(String[] args) {
        // Inherited 继承性测试， Student extends Person[@MyAnnotation("hello")]
        Class<Student> clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        System.out.println(Arrays.toString(annotations));
    }
}
