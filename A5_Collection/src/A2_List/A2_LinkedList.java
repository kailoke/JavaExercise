package A2_List;

/** LinkedList 带头尾指针的双向链表，内部没有声明数组
 * 一、新增方法
 *  > 从Collection继承的方法 + LinkedList集合增加头部(first指针)\尾部(last指针) CRD 方法
 *
 * 二、LinkedList 源码分析
 *  > 1. 头结点指针 : Node<E> first    尾结点指针：Node<E> last
 *  > 2. 数组内部类 Node<E>，作为LinkedList中保存数据的基本结构：E item \ Node<E> prev \ Node<e> next
 *  > 3. LinkedList list = new LinkedList() 初始化构造时，声明 first && last = null
 *  > 4. 实际上双向链表的应用，注意当前\相邻节点的prev && next 更新、size更新、头结点更新(头插)、尾结点更新
 */
public class A2_LinkedList {
    public static void main(String[] args) {

    }
}
