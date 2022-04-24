package stack_.arraystack;

import stack_.arraystack.ArrayStack;

/**
 * @description: 数组实现栈测试
 * @author: WuW
 * @create: 2022/1/2 13:45
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        try {
            arrayStack.push(1);
            arrayStack.push(2);
            arrayStack.push(3);
//            arrayStack.push(4);
            arrayStack.display();
            int top1 = arrayStack.pop();
            int top2 = arrayStack.pop();
            arrayStack.display();
            int top3 = arrayStack.pop();
//            arrayStack.pop();
            arrayStack.display();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
