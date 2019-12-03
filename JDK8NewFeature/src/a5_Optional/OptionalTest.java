package a5_Optional;

import org.junit.Test;

import java.util.Optional;

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
