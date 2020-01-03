package A4_InstanceSort;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/** java实现对象排序
 * 一、自然排序： implements java.lang.Comparable，@Override compareTo(Object obj)
 *  > 1. 实现对继承类每个对象进行整体排序，默认从小到大排序
 *  > 2. this > 形参，return 正数，this < 形参，return 负数，this = 形参，return 0
 *  > 3. 实现Comparable接口的对象列表(和数组)可通过Collections.sort() 或者 Arrays.sort() 自动排序
 *  > 4. 实现Comparable几口的对象可用作"有序映射"中的`键`或"有序集合"中的`元素`，无需指定比较器
 *  > 5. 建议 o1.compareTo(o2)==0 的boolean返回值和 o1.equals(o2) 一致
 *
 * 二、定制排序： java.util.Comparator<T>，@Override int compare(T t1,T, t2)
 *  > 1. Comparator显示指定时，比较方法均不再尝试使用对象内部的compareTo()
 *  > 2. o1 > o2，return 正数，o1 < o2，return 负数，o1 = o2，return 0
 *  > 3. Collections.sort(Comparator com) || Arrays.sort(Comparator com)
 *  > 4. 其他声明需要使用Comparator com 的方法
 */
public class A1_InstanceSort {
    @Test
    // 1. implements comparable
    public void comparable() {
        // Array
        String[] arr = new String[]{"CC","GG","AA","BB","KK"};
        Arrays.sort(arr);
        System.out.println("Arrays[String].sort() : " + Arrays.toString(arr));

        // Collection
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("联想",42));
        goods.add(new Goods("戴尔",35));
        goods.add(new Goods("小米",38));
        goods.add(new Goods("华为",28));
        Collections.sort(goods);
        System.out.println("ArrayList<Goods>.sort()\n: " + goods);
    }

    @Test
    // 2. Comparator com
    public void comparator() {
        Goods[] goods = new Goods[4];
        goods[0] = new Goods("War and Peace", 100);
        goods[1] = new Goods("Childhood", 80);
        goods[2] = new Goods("Scarlet and Black", 140);
        goods[3] = new Goods("Notre Dame de Paris", 120);
        Arrays.sort(goods, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println("Arrays<Goods>.sort(goods,Comparator): \n" + Arrays.toString(goods));
    }
}

// 按商品价格，从小到大排序
class Goods implements Comparable{
    private String name;
    private int price;

    Goods(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods){
            Goods goods = (Goods)o;
            return Integer.compare(this.price, goods.price);
        }
        throw new RuntimeException("need type" + this.getClass() + "but " + o.getClass() + "were given");
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                "}\n";
    }
}
