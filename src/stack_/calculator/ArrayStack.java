package stack_.calculator;

/**
 * @description: 用数组实现栈
 * @author: WuW
 * @create: 2022/1/2 13:37
 */
public class ArrayStack {

    int maxSize;
    int top;
    int bottom;
    String[] stackArray;

    public ArrayStack(int maxSize){
        stackArray =  new String[maxSize];
        this.maxSize = maxSize;
        top = -1;
        bottom = -1;
    }

    /**
     * 判满
     * @return
     */
    public boolean isFull(){
        return top == maxSize-1;
    }

    /**
     * 判空
     * @return
     */
    public boolean isEmpty(){
        return top == bottom;
    }

    /**
     * 入栈
     */
    public void push(String number){
        if (isFull()){
            throw new RuntimeException("栈已满！入栈失败！");
        }
        top++;
        stackArray[top] = number;
    }

    /**
     * 出栈
     * @return
     */
    public String pop(){
        if(isEmpty()){
            throw new RuntimeException("栈是空！出栈失败！");
        }
        String number = stackArray[top];
        stackArray[top] = "";
        top--;
        return number;
    }


    /**
     * 显示栈中所有数据
     */
    public void display(){
        for(int i = top ; i >= 0 ; i--){
            System.out.print(stackArray[i] + "\t");
        }
        System.out.println();
    }


    /**
     * 获取栈顶元素
     * @return
     */
    public String getTop(){
        return stackArray[top];
    }

}
