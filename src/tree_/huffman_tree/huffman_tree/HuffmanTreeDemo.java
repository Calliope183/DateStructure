package tree_.huffman_tree.huffman_tree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 赫夫曼树的创建于遍历
 * @author: WuW
 * @create: 2022/2/8 16:29
 */
public class HuffmanTreeDemo {

    public static void main(String[] args) {
        int[] arrays = {3, 2, 5, 1, 6, 9};
        Node root = HuffmanTree.huffmanTree(arrays);
        new HuffmanTree().preOrder(root);
    }

}

class HuffmanTree{

    /**
     * 赫夫曼树的构建
     * @param arrays 要构建的赫夫曼树的权值
     * @return 返回赫夫曼树的根节点
     */
    public static Node huffmanTree(int[] arrays){
        // 将数组中节点的权值构建成节点，并加入到List集合中
        List<Node> nodeList = new ArrayList<>();
        for (int value : arrays) {
            nodeList.add(new Node(value));
        }
        // 最后的node集合中只有根节点
        while (nodeList.size() > 1){
            // 将各结点按照升序排列
            Collections.sort(nodeList);
            // 构建新的二叉树
            Node node = new Node(nodeList.get(0).data + nodeList.get(1).data);
            node.left = nodeList.get(0);
            node.right = nodeList.get(1);
            // 将最小的两个节点删除
            nodeList.remove(0);
            nodeList.remove(0);
            // 将新的二叉树的根节点加入到node集合中
            nodeList.add(node);
        }
        // 返回赫夫曼树的根节点
        return nodeList.get(0);
    }

    /**
     * 树的前序遍历
     * @param curNode 当前节点
     */
    public void preOrder(Node curNode){
        if (curNode == null){
            System.out.println("当前赫夫曼树为null；");
            return;
        }
        System.out.print(curNode.data + "\t");
        if (curNode.left != null){
            preOrder(curNode.left);
        }
        if (curNode.right != null){
            preOrder(curNode.right);
        }
    }

}


/**
 * 赫夫曼树的结点结构，实现Comparator接口是为了方便结点比较
 */
class Node implements Comparable<Node>{
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        return this.data - o.data;
    }
}
