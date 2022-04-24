package linkedlist_.singlecirclelist;

/**
 * @description: 单向循环链表节点结构体定义
 * @author: WuW
 * @create: 2022/1/1 18:33
 */
public class Node {

    private String name;
    private Node next;




    public Node(String name, Node next) {
        this.name = name;
        this.next = next;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "name=" + name;
    }
}
