package linkedlist_.doublelinkedlist;


import java.util.Scanner;

/**
 * @description: 双向链表测试
 * @author: WuW
 * @create: 2022/1/1 17:23
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        while(true){
            System.out.println("=====双向链表====");
            System.out.println("a(add)");
            System.out.println("s(showAll)");
            System.out.println("d(delete)");
            System.out.println("e(exit)");
            System.out.println("=====双向链表====");
            System.out.print("请输入字符：");
            Scanner scanner = new Scanner(System.in);
            char c = scanner.next().charAt(0);
            switch (c) {
                case 'a':
                    Node node1 = new Node("张飞", null, null);
                    Node node2 = new Node("关羽", null, null);
                    Node node3 = new Node("刘备", null, null);
                    Node node4 = new Node("曹操", null, null);
                    doubleLinkedList.addEle(node1);
                    doubleLinkedList.addEle(node2);
                    doubleLinkedList.addEle(node3);
                    doubleLinkedList.addEle(node4);
                    break;
                case 's':
                    doubleLinkedList.showAllEle();
                    break;
                case 'd':
                    try {
                        Node node = doubleLinkedList.deleteEle("赵云");
                        System.out.println(node.name);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 'e':
                    System.out.println("退出程序！");
                    return;
                default:
                    System.out.println("输入有误!");
            }
        }


    }
}
