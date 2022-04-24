package linkedlist_.singlecirclelist;

/**
 * @description: 单向循环链表操作
 * @author: WuW
 * @create: 2022/1/1 18:34
 */
public class SingleCircleList {

    private Node current;
    private Node head;

    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * 追加元素
     */
    public void addEle(Node newNode){
        current.setNext(newNode);
        newNode.setNext(head);
        current = newNode;
    }

    /**
     * 删除节点
     * @param deleteNode
     */
    public void deleteEle(Node deleteNode){
        Node temp = head;
        // temp.getNext() != current说明链表里只剩两个小孩了
        while (temp.getNext() != current) {
            temp = temp.getNext();
        }
        temp.setNext(temp.getNext().getNext());
        current = current.getNext();
    }

    /**
     * 显示所有节点
     */
    public void showAll(){
        if (head == null){
            System.out.println("没有小孩玩游戏！");
            return;
        }
        Node temp = head;
        while(temp.getNext() != head){
            System.out.print(temp +"\t");
            temp = temp.getNext();
        }
        System.out.print(temp +"\t");
        System.out.println();
    }

}
