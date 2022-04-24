package linkedlist_.doublelinkedlist;

/**
 * @description: 双向链表
 * @author: WuW
 * @create: 2022/1/1 17:17
 */
public class DoubleLinkedList {

    // 头结点
    private Node head = new Node("", null, null);

    /**
     * 获取头结点
     *
     * @return
     */
    public Node getHead() {
        return head;
    }

    /**
     * 添加元素（链表末尾）
     */
    public void addEle(Node newNode) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.pre = temp;
    }

    /**
     * 删除节点
     *
     * @param name
     * @return
     */
    public Node deleteEle(String name) {
        if(head.next == null){
            return null;
        }
        Node temp = head.next;
        while (true) {
            if(temp == null){
                throw new RuntimeException("没有此英雄哦！");
            }
            if (temp.name.equals(name)) {
                break;
            }
            temp = temp.next;
        }
        temp.pre.next = temp.next;
        if(temp.next != null){
            temp.next.pre = temp.pre;
        }
        return temp;
    }


    public void showAllEle() {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp + "\t");
        }
    }


}
