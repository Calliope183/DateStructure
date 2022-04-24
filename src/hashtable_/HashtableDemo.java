package hashtable_;

import java.util.Scanner;

/**
 * @description: hashtable实现
 * @author: WuW
 * @create: 2022/1/15 16:18
 */
public class HashtableDemo {
    public static void main(String[] args) {


        Hashtable_ hashtable = new Hashtable_(5);

        Scanner input = new Scanner(System.in);

        while(true){
            System.out.println("—————菜单———");
            System.out.println("a——添加元素");
            System.out.println("f——查找元素");
            System.out.println("l——显示所有");
            System.out.println("e——退出系统");
            System.out.println("—————菜单———");
            char c = input.next().charAt(0);
            switch (c){
                case 'a':
                    System.out.print("请输入id：");
                    int id = input.nextInt();
                    Node node = new Node(id);
                    hashtable.add(node);
                    System.out.println("添加成功");
                    break;
                case 'f':
                    System.out.print("请输入要查找的id：");
                    int findId = input.nextInt();
                    hashtable.findById(findId);
                    break;
                case 'l':
                    hashtable.list();
                    break;
                case 'e':
                    System.out.println("退出系统");
                    return;
                default:
                    System.out.println("无效字符！");
            }

        }

    }

}

class Hashtable_{
    int size;
    LinkedList[] hashtable;

    public Hashtable_(int size){
        this.size = size;
        hashtable = new LinkedList[size];
    }

    /**
     * 添加元素
     * @param node 要添加的结点
     */
    public void add(Node node){
        int index = getIndex(node.id);
        if (hashtable[index] == null){
            hashtable[index] = new LinkedList();
        }
        hashtable[index].add(node);
    }

    /**
     * 遍历hashtable
     */
    public void list(){
        for (int i = 0; i < hashtable.length ; i++) {
            if (hashtable[i] != null){
                System.out.printf("第%d个链表", i);
                hashtable[i].list();
                System.out.println();
            }
        }
    }

    /**
     * 根据id查找结点
     * @param id 待查找的id
     */
    public void findById(int id){
        int index = getIndex(id);
        Node byId = hashtable[index].findById(id);
        if (byId == null){
            System.out.println("没找到！");
        } else{
            System.out.println(byId + "在Hashtable中的第" + index + "处");
        }
    }

    /**
     * 散列函数：取模
     * @param id
     * @return
     */
    public int getIndex(int id){
        return id % size;
    }

}

/**
 * 链表结点
 */
class Node{
    int id;
    Node next;

    public Node(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "hashtable_.Node{" +
                "id=" + id +
                '}';
    }
}

/**
 * 链表
 */
class LinkedList{

    Node head;

    /**
     * 添加结点
     * @param node 要添加的结点
     */
    public void add(Node node){
        if (head == null){
            head = node;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
        node.next = null;
    }

    /**
     * 遍历链表
     */
    public void list(){
        if (head == null){
            System.out.println("链表为空！");
            return;
        }

        Node temp = head;
        while(temp != null){
            System.out.print(temp + "\t");
            temp = temp.next;
        }
    }

    /**
     * 根据id查找结点
     * @param id 待查找的id
     * @return 返回找到的Node结点
     */
    public Node findById(int id){
        Node temp = head;
        if (head == null){
            return null;
        }
        while(temp != null){
            if (temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

}
