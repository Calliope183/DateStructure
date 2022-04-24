package queue_.single;

/**
 * @author WuW
 * @date 2021/12/31 11:07
 * @Des 数组实现单向队列
 */
public class SingleQueue {


    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    /**
     * 在构造器中初始化队列
     *
     * @param size
     */
    public SingleQueue(int size) {
        this.maxSize = size;
        this.front = -1;
        this.rear = -1;
        this.arr = new int[maxSize];
    }

    /**
     * 判满
     *
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }


    /**
     * 入队
     *
     * @param data
     */
    public void addQueue(int data) {
        if (isFull()) {
            throw new RuntimeException("队已满，不能进");
        }
        arr[++rear] = data;
    }


    /**
     * 出队
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队是空，不能出");
        }
        int data = arr[front + 1];
        front++;
        return data;
    }

    /**
     * 显示队列
     */
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队是空，无物显");
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }

    /**
     * 查看队头
     * @return
     */
    public int headQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队是空，无队首");
        }
        return arr[front + 1];
    }


}
