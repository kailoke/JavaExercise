package a5_Optional;

import org.junit.Test;
import java.util.Optional;

/** Optional<T> 容器类，为了解决Java中空指针问题
 *  一、保存T类型的对象(值)，可以为null
 *  二、方法
 *   > 1. 实例化
 *      > empty(): Optional空值Optional.empty实例
 *      > of(Object o): Optional静态实例化，不允许null值
 *      > ofNullable(Object o): Optional静态实例化，允许null值
 *   > 2. 包含对象
 *      > boolean isPresent(): 是否存在非空的对象
 *      > void ifPresent(Consumer<? super T> c) 如果为真，则将对象作为参数执行Consumer
 *   > 3. 获取对象
 *      > T get(): 获取对象，若为空则 throw new NoValueException()
 *      > T orElse(T other): 获取对象，若为空则返回参数对象
 *      > T orElseGet(Supplier<T> s): 获取对象，则为空则返回Supplier返回的对象
 */
public class OptionalTest {

    @Test
    // 1. Optional实例化
    public void testOptionalNew(){
        Girl girl = new Girl("小丽");
//        girl = null;
        Optional<Girl> optionalGirl1 = Optional.of(girl);
        System.out.println("of():" + optionalGirl1);

        girl = null;
        Optional<Girl> optionalGirl2 = Optional.ofNullable(girl);
        System.out.println("ofNullable():" + optionalGirl2);

        Optional<Object> empty = Optional.empty();
        System.out.println("empty():" + empty);
    }

    @Test
    // 2. Optional使用场景
    public void testCase(){
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
