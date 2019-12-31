package A1_StringRelevant;

public class A1_StringInterview {
    String str = new String("good");
    char[] ch = { 't', 'e', 's', 't' };
                    // 地址传递
    public void change(String str, char ch[]) {
        // 修改的形参的指向，原实参指向没该表
        str = "test ok";
        ch[0] = 'b'; }
    public static void main(String[] args) {
        A1_StringInterview ex = new A1_StringInterview();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");
        System.out.println(ex.ch);
        // good and best
    }
}
