package A2_Annotation;

/**
 * @author Administrator
 * @version 1.0
 * @see Math
 */
public class A1_DocAnnotation {
    /**
     * 程序的主方法，程序的入口
     * @param args String[] 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("Nothing");
    }
    /**
     * 求圆面积的方法
     * @param radius double 半径值
     * @return double 圆的面积
     */
    public static double getArea(double radius){
        return Math.PI * radius * radius; }
}
