package a8_StaticProxy;

public class Manager implements Star{

    private Singer singer;

    Manager(Singer singer) {
        this.singer = singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public void sing() {
        System.out.println("经理人准备工作...");
        singer.sing();
        System.out.println("经理人收钱...");
    }
}
