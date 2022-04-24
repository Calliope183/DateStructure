package linkedlist_.doublelinkedlist;

/**
 * @description: 双向链表节点
 * @author: WuW
 * @create: 2022/1/1 17:17
 */
public class Node {

    public String name;
    public Node next;
    public Node pre;

    public Node(String name, Node next, Node pre){
        this.name = name;
        this.next = next;
        this.pre = pre;
    }


    @Override
    public String toString() {
        return "name=" + name;
    }
}
