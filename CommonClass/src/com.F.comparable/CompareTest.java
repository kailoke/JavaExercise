package com.F.comparable;

import java.util.Arrays;

/** java对象排序
 * 自然排序：实现 java.lang.Comparable 接口,重写compareTo()
 *  > this>形参，则返回正整数
 *  > this<形参，则返回负整数
 *  > this=形参，则返回 0
 *
 *
 *
 * 定制排序：java.util.Comparator 接口
 *
 *
 */
public class CompareTest {

    public static void main(String[] args) {
        String[] arr = new String[]{"CC","GG","AA","BB","KK"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        Goods[] goods = new Goods[4];
        goods[0] = new Goods("联想",42);
        goods[1] = new Goods("戴尔",35);
        goods[2] = new Goods("小米",38);
        goods[3] = new Goods("华为",28);

        Arrays.sort(goods);
        System.out.println(Arrays.toString(goods));


    }
}

class Goods implements Comparable{
    private String name;
    private int price;

    Goods(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods){
            Goods goods = (Goods)o;
            return Integer.compare(this.price, goods.price);
        }
        throw new RuntimeException("传入的数据类型不是" + this.getClass());
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                "}\n";
    }
}
