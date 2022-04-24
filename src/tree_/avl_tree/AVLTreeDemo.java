package tree_.avl_tree;

import org.junit.jupiter.api.Test;

/**
 * @description: 平衡二叉树
 * @author: WuW
 * @create: 2022/2/16 11:28
 */
public class AVLTreeDemo {

    @Test
    public void test(){
        int[] arrays = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        avlTree.toBinaryTree(arrays);
        avlTree.infixOrder();
        Node root = AVLTree.getRoot();
        System.out.println(root.treeHigh());
        System.out.println(root.leftTreeHigh());
        System.out.println(root.rightTreeHigh());
    }

}

class AVLTree {

    // 根节点
    private static Node root;

    public static Node getRoot() {
        return root;
    }

    /**
     * 将数组中的值转换成二叉排序树
     *
     * @param arrays 要作为结点数据的数组
     */
    public void toBinaryTree(int[] arrays) {
        for (int array : arrays) {
            add(new Node(array));
        }
    }

    /**
     * 向二叉排序树中添加结点
     *
     * @param node 要添加的结点
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
    }

    /**
     * 方法重载，中序遍历
     */
    public void infixOrder() {
        infixOrder(root);
    }

    /**
     * 二叉树中序遍历
     *
     * @param node 根节点
     */
    public void infixOrder(Node node) {
        if (node == null) {
            System.out.println("树空，不能遍历");
            return;
        }
        if (node.left != null) {
            infixOrder(node.left);
        }
        System.out.println(node);
        if (node.right != null) {
            infixOrder(node.right);
        }
    }

    /**
     * 查找结点
     *
     * @param value 要查找的值
     * @return 返回找到的结点
     */
    public Node searchNode(int value) {
        if (root == null) {
            System.out.println("树空");
            return null;
        }
        if (root.data == value) {
            return root;
        }
        return root.searchNode(value);
    }

    /**
     * 查找value对应结点的父节点
     *
     * @param value 要查找的值
     * @return 返回找到的父亲节点
     */
    public Node searchParent(int value) {
        if (root == null) {
            System.out.println("树空");
            return null;
        }
        if (searchNode(value) == null) {
            System.out.println("结点不存在");
            return null;
        }
        if (root.data == value) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 删除节点
     *
     * @param value 要删除节点的值
     * @return 返回已删除的结点
     */
    public Node deleteNode(int value) {
        if (root == null) {
            System.out.println("树空");
            return null;
        }
        // 此二叉树只有一个根节点，且要删除的结点就是根节点
        if (root.left == null && root.right == null && root.data == value) {
            Node temp = root;
            root = null;
            return temp;
        }
        Node node = searchNode(value);
        if (node == null) {
            System.out.println("此节点不存在");
            return null;
        }
        Node parent = searchParent(value);
        // 第一种情况：删除的结点是叶子结点，则找到其父节点直接删除
        if (node.left == null && node.right == null) {
            if (parent.left != null && value == parent.left.data) {
                parent.left = null;
            } else if (parent.right != null && value == parent.right.data) {
                parent.right = null;
            }
            return node;
        } else if (node.left != null && node.right != null) {
            // 第三种情况：删除的结点有两个子树，则在其右子树中找到值最小的结点删除，并将其值赋给要删除的那个结点，或者左子树中找最大Node temp = null;
            Node temp = null;
            Node minNode = searchMinNode(node.right);
            temp = minNode;
            deleteNode(minNode.data);
            node.data = temp.data;
            return temp;
        } else {
            // 第二种情况：删除的结点有一个子树，则将其子树直接连接到其父节点上
            if (node.left != null) {
                // 要删除的结点只有左子树
                if (parent != null) {
                    if (parent.left.data == value) {
                        // 要删除的结点原是左边节点
                        parent.left = node.left;
                    } else {
                        // 要删除的结点原是右边节点
                        parent.right = node.left;
                    }
                } else {
                    root = node.left;
                }
            } else {
                // 只有右子树
                if (parent != null) {
                    if (parent.left.data == value) {
                        parent.left = node.right;
                    } else {
                        parent.right = node.right;
                    }
                } else {
                    root = node.right;
                }
            }
            return node;
        }
    }

    /**
     * 右子树中值最小的结点
     * @param node 根节点
     * @return 返回以node跟根节点的子树中的值最小的结点
     */
    public Node searchMinNode(Node node){
        if (node == null){
            return null;
        }
        if (node.left == null){
            return node;
        }
        return searchMinNode(node.left);
    }

    /**
     * 左子树中值最大的结点
     * @param node 左子树的根节点
     * @return 返回以node为根节点中值最大的值
     */
    public Node searchMaxNode(Node node){
        if (node == null){
            return null;
        }
        if (node.right == null){
            return node;
        }
        return searchMaxNode(node.right);
    }
}


class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }

    /**
     * 添加结点
     * @param node 要添加的结点
     */
    public void add(Node node){
        if (node == null){
            System.out.println("传入结点为null，不能添加");
            return;
        }
        if (node.data < this.data){
            if (this.left == null){
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null){
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        // 判断是否需要左旋转(不是平衡二叉树)
        if (rightTreeHigh() - leftTreeHigh() > 1){
            // 双旋转
            if (right != null && right.leftTreeHigh() > right.rightTreeHigh()){
                right.rightRotate();
            }
            // 右子树比左子树高
            leftRotate();
            return;
        }
        if (leftTreeHigh() - rightTreeHigh() > 1){
            if (left != null && left.rightTreeHigh() > left.leftTreeHigh()){
                left.leftRotate();
            }
            rightRotate();
        }
    }

    /**
     * 左旋转
     */
    private void leftRotate(){
        Node newNode = new Node(data);
        newNode.left = left;
        newNode.right = right.left;
        data = right.data;
        right = right.right;
        left = newNode;
    }

    /**
     * 右旋转
     */
    private void rightRotate(){
        Node newNode = new Node(data);
        newNode.right = right;
        newNode.left = left.right;
        data = right.data;
        left = left.left;
        right = newNode;
    }





    /**
     * 左子树高度
     * @return
     */
    public int leftTreeHigh(){
        if (left == null){
            return 0;
        }
        return left.treeHigh();
    }

    /**
     * 右子树高度
     * @return
     */
    public int rightTreeHigh(){
        if (right == null){
            return 0;
        }
        return right.treeHigh();
    }

    /**
     * 树高度
     * @return 返回以node为根节点的树的高度
     */
    public int treeHigh(){
        // 返回两颗子树最大的高度
        return Math.max(left == null ? 0 : left.treeHigh(),
                right == null ? 0 : right.treeHigh()) + 1;
    }

    /**
     * 寻找结点
     * @param value 要寻找结点的值
     * @return 返回找到的结点，没找到返回null
     */
    public Node searchNode(int value){
        if (this.data == value){
            return this;
        }
        if (value < this.data){
            if (this.left != null){
                return this.left.searchNode(value);
            } else {
                System.out.println("没找到");
                return null;
            }
        } else {
            if (this.right != null){
                return this.right.searchNode(value);
            } else {
                System.out.println("没找到");
                return null;
            }
        }
    }


    /**
     * 查找父亲节点
     * @param value 要查找的值
     * @return 返回找到的父节点
     */
    public Node searchParent(int value){
        if ((this.left != null && value == this.left.data) || (this.right != null && value == this.right.data)){
            return this;
        }
        if (this.left != null && value < this.data){
            return this.left.searchParent(value);
        }
        if (this.right != null && value > this.data){
            return this.right.searchParent(value);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}

class abc{
    @Test
    void test(){
        char[] c = {'a'};
        System.out.println(c);
    }
}
