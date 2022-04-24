package tree_.threaded_binary_tree;

/**
 * @description: 线索二叉树
 * @author: WuW
 * @create: 2022/1/19 15:50
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        Node node1 = new Node(1, "貂蝉");
        Node node2 = new Node(3, "王昭君");
        Node node3 = new Node(6, "甄姬");
        Node node4 = new Node(8, "安琪拉");
        Node node5 = new Node(10, "安琪拉");
        Node node6 = new Node(14, "安琪拉");

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(node1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        threadedBinaryTree.infixThreadedTree();
//        System.out.println("node1.left"+node1.left);
//        System.out.println("node1.right"+node1.right);
//        System.out.println("node2"+node2.left);
//        System.out.println("node2"+node2.right);
//        System.out.println("node3"+node3.left);
//        System.out.println("node3"+node3.right);
//        System.out.println("node4"+node4.left);
//        System.out.println("node4"+node4.right);
//        System.out.println("node5"+node5.left);
//        System.out.println("node5"+node5.right);
//        System.out.println("node6"+node6.left);
//        System.out.println("node6"+node6.right);

        threadedBinaryTree.listInfixThreadedTree();

    }
}

class ThreadedBinaryTree{

    private Node root; // 根节点
    private Node pre;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 重载方法
     */
    public void infixThreadedTree(){
        infixThreadedTree(root);
    }

    /**
     * 中序线索二叉树
     * @param curNode 要线索化的结点
     */
    public void infixThreadedTree(Node curNode){

        if (curNode == null){
            return;
        }

        // 向左递归
        infixThreadedTree(curNode.left);

        // 如果左指针域为空，则让其左指针域指向前驱
        if (curNode.left == null){
            curNode.left = pre;
            curNode.setLeftType(1);
        }
        // 如果当前节点的前驱结点的右节点为空，则让其前驱结点的右指针域指向当前节点
        if (pre!= null && pre.right == null){
            pre.right = curNode;
            pre.setRightType(1);
        }
        // 让pre指针指向当前节点
        pre = curNode;

        // 向右递归
        infixThreadedTree(curNode.right);

    }

    /**
     * 遍历中序线索二叉树
     */
    public void listInfixThreadedTree(){

        Node curNode = root;
        while (curNode != null){

            // 找到第一个左指针域为空的结点
            while(curNode.getLeftType() == 0){
                curNode = curNode.left;
            }
            // 输出当前节点
            System.out.println(curNode);
            // 如果当前节点的右指针域指向的是他的后继结点，则直接输出
            while(curNode.getRightType() == 1){
                curNode = curNode.right;
                System.out.println(curNode);
            }
            curNode = curNode.right;
        }

    }

}

class Node{

    private int id;
    private String name;
    Node left;  // 左节点
    Node right; // 右节点
    // 左右结点指针域的类型，0为左子树或右子树，1为前驱或后继
    private int leftType = 0;
    private int rightType = 0;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }

}
