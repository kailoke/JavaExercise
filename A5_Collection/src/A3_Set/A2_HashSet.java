package A3_Set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/** HashSet：本质上是HashMap
 * 一、HashSet对象相等的规则
 *  > 两个对象的 HashCode()返回值相等 && equals() == true
 *  > 存放于Set容器中的类一定要重写equals()和hashCode(Object obj)方法，以保证相等性
 *
 * 二、无序性 != 随机性
 *  > HashSet按Hash算法来存储集合中的元素，因此具有很好的存取、查找、删除性能
 *
 * 三、不可重复性
 *  > Set中能存放已经存在的对象
 *
 * 四、add()过程    以HashSet为例
 *  > 默认构造底层数组长度 == 16 (2<<3 == 2^(3+1))
 *  > 调用对象的 hashCode() 得到该对象的 HashCode 值
 *  > 根据HashCode值按照某种散列函数算法得出在HashSet底层数组中的存储位置
 *  > 位置上为空元素则直接添加
 *  > 位置上有其他元素(或链表形式的元素)，则比较添加对象和链表元素的hash值
 *      > Hash值不同使用链表添加对象（JDK7从上添加，JDK8从下添加）
 *      > Hash值相同则调用添加对象的equals方法逐一对比:任一true则不添加，全部false则添加
 *  > 当 size() > threshold == length * loadFactor 则对底层数组进行2倍扩容，并将原数组中的数据重新计算放入
 *
 * 五、重写hashCode()的基本原则
 *  > 确定性：同一个对象多次调用 hashCode() 返回相同值
 *  > 对等性：若两个对象的 equals() 返回true，则两个对象的 hashCode() 返回值也相等
 *  > 共用性：对象中用作 equals() 比较的Field，都用来计算 hashCode()
 *  > 同步行：改写 equals() 时一般需要同时 改写 hashCode()
 *
 * 六、JDK hashCode() 方法选用数组 31 的原因：
 *  > 1. 31是素数。减少hash值冲突的概率
 *  > 2. 31是是尽量大的系统，减少hash值冲突
 *  > 3. 31占用5bits，相乘造成数据溢出的概率较小
 *  > 4. 31 可以由 i*31 == (i<<5)-1 来表示，很多虚拟机都有做相关优化，算法效率高
 */
public class A2_HashSet {

    // 1.HashSet 测试
    @Test
    public void testHashSet(){
        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("BB");
        set.add(129);
        System.out.println("added 6,set.size() : " + set.size());

        for (Object o : set) {
            System.out.println(o);
        }
    }
}
