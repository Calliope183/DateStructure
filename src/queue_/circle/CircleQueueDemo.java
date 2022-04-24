package queue_.circle;

import queue_.single.SingleQueue;

import java.util.Scanner;

/**
 * @author WuW
 * @date 2021/12/31 11:55
 * @Des  循环队列测试
 */
public class CircleQueueDemo {

    public static void main(String[] args) {


        CircleQueue queue = new CircleQueue(4);
        // 有一个预留位置，因此实际队列长度为size-1
        while (true){
            System.out.println("====循环队列菜单====");
            System.out.println("a（入队）");
            System.out.println("g（出队）");
            System.out.println("s（显示队列）");
            System.out.println("h（查看队首）");
            System.out.println("e（退出程序）");
            System.out.println("====循环队列菜单====");
            System.out.print("您要进行何种操作：");


            Scanner scanner = new Scanner(System.in);
            char c = scanner.next().charAt(0);
            switch (c){
                case 'a':
                    // 入队
                    System.out.print("请输入数据（整数）：");
                    int aData = scanner.nextInt();
                    try {
                        queue.addQueue(aData);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 'g':
                    // 出队
                    try {
                        int gData = queue.getQueue();
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println(queue.headQueue());
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 'e':
                    System.out.println("退出程序啦~~");
                    return;
                default:
                    System.out.println("请输入正确字符：");
            }
        }

    }


}
