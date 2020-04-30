package A1_ClassElements;

public class A4_Final {
    public static void main(String[] args) {
        // 1.值传递
        int a = 10;
        final int b = a;
        a = 15;
        System.out.println(b);  // b == 10

        // 2.地址值传递
        String s1 = "hello";
        final String s2;
        s2 = "hello";
        s1 = "world";
        System.out.println(s2); // s2 = "hello"
    }
}
