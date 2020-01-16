package A4_Map;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

/** HashMap 底层实现
 * 一、JDK7.0     new HashMap()
 *  >1.实例化默认构造长度 = DEFAULT_INITIAL_CAPACITY(`16`):Entry<K,V>[] table
 *  >2.key对象的Hash值 = hashCode()，之后使用Hash值按照某种散列算法得出位置
 *  >3.此桶为空则直接添加
 *  >4.此桶有其他元素(或链表形式的元素)，则比较添加对象key和链表中元素的key的hash值
 *     > Hash值不同使用链表添加对象（JDK7头插，JDK8尾插)
 *     > Hash值相同则调用添加对象.equals()逐一对比
 *          > 任一equals() == true，则此key的value更新为当前对象的value
 *          > 全部equals() == false，当前key-value对象添加成功
 *  > HashMap底层: Entry数组 + Entry链表
 *  > 扩容：当 size() > threshold == length * loadFactor 进行2倍扩容，并将原数组中的数据重新计算放入
 *
 * 二、JDK8.0     HashMap
 *  >1.new HashMap():底层不再直接创建长度==16的数组；首次调用put()，底层创建长度==16的数组(LazyLoad)
 *  >2.底层数组是 Node[] implements Entry[]，子节点可以是 Node，也可以是 TreeNode ( 树化时转变)
 *  >3.HashMap底层: Node数组 + 链表 + 红黑树
 *      > 当桶的元素以 链表形式存在数据个数 > TREEIFY_THRESHOLD(8) && 数组长度 > MIN_TREEIFY_CAPACITY(64)
 *          ↑此索引位置上的所有数据改为使用红黑树存储。                                 ↑ 否则扩容
 *  >4.JDK8.0重新设计HashMap为了增加其查找速度，使HashSet中的元素散列 + 有序查找(树)
 */


public class A2_HashMap {
    @Test
    public void test(){
        HashMap map = new HashMap<>();
        map.put(1,1);
        map.get(1);
    }
}
