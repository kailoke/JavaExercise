package a3_Optional;

import org.junit.Test;
import java.util.Optional;

/** Optional<T> 容器类，为了解决Java空指针异常引入的类，通过检查空值的方式来防止代码污染
 * 一、内部保存T类型的对象(值)，代表这个值存在；可以为null，代表这个值不存在
 * 二、作用：减少空指针异常，当发生空指针时提供 orElse(T) orElseGet(Supplier s) 方法
 * 三、方法
 *  > 1. 实例化
 *      > of(Object o): Optional静态实例化，不允许null值
 *      > ofNullable(Object o): Optional静态实例化，允许null值
 *      > empty(): Optional空值Optional.empty实例
 *  > 2. 包含对象
 *      > boolean isPresent(): 是否存在非null的对象
 *      > void ifPresent(Consumer<? super T> c) 如果为真，则将对象作为参数执行Consumer
 *  > 3. 获取对象
 *      > T get(): 获取对象，若为null则 throw new NoValueException()
 *      > T orElse(T other): 获取对象，若为null则返回实参
 *      > T orElseGet(Supplier<T extends X> exceptionSupplier): 获取对象，若为null则返回Supplier实现提供的异常
 */

public class A1_Optional {

    @Test
    // 一、Optional实例化
    public void optionalConstructors(){
        Girl girl = new Girl("小丽");
//        girl = null;
        // 1. Optional.of(T t)
        Optional<Girl> optionalGirl1 = Optional.of(girl);
        System.out.println("of():" + optionalGirl1);

        // 2. Optional.ofNullable(T t)
        girl = null;
        Optional<Girl> optionalGirl2 = Optional.ofNullable(girl);
        System.out.println("ofNullable():" + optionalGirl2);

        // 3. Optional.Empty()
        Optional<Object> empty = Optional.empty();
        System.out.println("empty():" + empty);
    }

    @Test
    // 二、 Optional使用场景:搭配 orElse() orElseGet() 函数处理 空指针的情况
    public void useOptional(){
        Boy boy = null;
        System.out.println(getGirlName(boy));
        boy = new Boy();
        System.out.println(getGirlName(boy));
        boy.setGirl(new Girl("小红"));
        System.out.println(getGirlName(boy));
    }
    private String getGirlName(Boy boy){
        Optional<Object> optionalBoy = Optional.ofNullable(boy);
        Boy boy1 = (Boy) optionalBoy.orElse(new Boy(new Girl("小花")));
        Girl girl1 = Optional.ofNullable(boy1.getGirl()).orElse(new Girl("小白"));
        return girl1.getName();
    }
}
