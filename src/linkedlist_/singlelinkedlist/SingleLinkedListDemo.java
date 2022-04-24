package linkedlist_.singlelinkedlist;

import java.util.Scanner;

/**
 * @author WuW
 * @date 2021/12/31 12:40
 * @Des
 */
public class SingleLinkedListDemo {

    public static SingleLinkedList linkedList = new SingleLinkedList();

    public static void main(String[] args) {

        while(true){
            System.out.println("=====单向链表====");
            System.out.println("a(add)");
            System.out.println("s(showAll)");
            System.out.println("i(insertInPosition)");
            System.out.println("d(delete)");
            System.out.println("e(exit)");
            System.out.println("=====单向链表====");
            System.out.print("请输入字符：");
            Scanner scanner = new Scanner(System.in);
            char c = scanner.next().charAt(0);
            switch (c) {
                case 'a':
                    Node person1 = new Node("刘玄德", null);
                    Node person2 = new Node("张翼德", null);
                    Node person3 = new Node("关云长", null);
                    linkedList.addElement(person1);
                    linkedList.addElement(person2);
                    linkedList.addElement(person3);
                    break;
                case 's':
                    linkedList.showAllElements();
                    break;
                case 'i':
                    linkedList.insertInPosition(2, new Node("曹孟德", null));
                    break;
                case 'd':
                    linkedList.deleteElement("曹孟德");
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
