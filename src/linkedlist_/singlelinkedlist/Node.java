package linkedlist_.singlelinkedlist;

/**
 * @author WuW
 * @date 2021/12/31 12:20
 * @Des
 */
public class Node {

    String name;
    Node nextNode;

    public Node(String name, Node nextNode) {
        this.name = name;
        this.nextNode = nextNode;
    }


    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", nextNode=" + nextNode +
                '}';
    }
}
