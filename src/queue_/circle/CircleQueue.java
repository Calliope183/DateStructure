package queue_.circle;

/**
 * @author WuW
 * @date 2021/12/31 11:48
 * @Des 循环队列
 */
public class CircleQueue {

    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    /**
     * 在构造器中初始化队列
     *
     * @param size
     */
    public CircleQueue(int size) {
        this.maxSize = size;
        this.arr = new int[maxSize];
    }

    /**
     * 判满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        arr[rear] = data;
        rear = (rear + 1) % maxSize;
    }


    /**
     * 出队
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队是空，不能出");
        }
        int data = arr[front];
        front = (front + 1) % maxSize;
        return data;
    }

    /**
     * 显示队列
     */
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队是空，无物显");
        }
        for (int i = front; i < front + ((rear + maxSize - front) % maxSize); i++) {
            System.out.printf("%d\t", arr[i % maxSize]);
        }
        System.out.println();
    }

    /**
     * 查看队头
     *
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队是空，无队首");
        }
        return arr[front];
    }


}
