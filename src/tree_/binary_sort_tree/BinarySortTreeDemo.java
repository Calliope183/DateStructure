package tree_.binary_sort_tree;

import org.junit.jupiter.api.Test;

/**
 * @description: 二叉排序树
 * @author: WuW
 * @create: 2022/2/13 10:25
 */
public class BinarySortTreeDemo {
    
    @Test
    public void test(){
        // 测试将数组转换成二叉排序树
        int[] arrays = {1, 34, 7, 2, 3, 10, 12, 5, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.toBinaryTree(arrays);
        System.out.println("-----------infixOrder()");
        binarySortTree.infixOrder();
        System.out.println("-----------infixOrder()");

        // 测试删除节点
        Node deleteNode = binarySortTree.deleteNode(5);
        System.out.println("删除" + deleteNode + "后：-------------------");
        binarySortTree.infixOrder();

    }

}

class BinarySortTree{
    // 根节点
    private static Node root;

    public static Node getRoot() {
        return root;
    }

    /**
     * 将数组中的值转换成二叉排序树
     * @param arrays 要作为结点数据的数组
     */
    public void toBinaryTree(int[] arrays){
        for (int array : arrays) {
            add(new Node(array));
        }
    }

    /**
     * 向二叉排序树中添加结点
     * @param node 要添加的结点
     */
    public void add(Node node){
        if (root == null){
            root = node;
            return;
        }
        root.add(node);
    }

    /**
     * 方法重载，中序遍历
     */
    public void infixOrder(){
        infixOrder(root);
    }

    /**
     * 二叉树中序遍历
     * @param node 根节点
     */
    public void infixOrder(Node node){
        if (node == null){
            System.out.println("树空，不能遍历");
            return;
        }
        if (node.left != null){
            infixOrder(node.left);
        }
        System.out.println(node);
        if (node.right != null){
            infixOrder(node.right);
        }
    }

    /**
     * 查找结点
     * @param value 要查找的值
     * @return 返回找到的结点
     */
    public Node searchNode(int value){
        if (root == null){
            System.out.println("树空");
            return null;
        }
        if (root.data == value){
            return root;
        }
        return root.searchNode(value);
    }

    /**
     * 查找value对应结点的父节点
     * @param value 要查找的值
     * @return 返回找到的父亲节点
     */
    public Node searchParent(int value){
        if (root == null){
            System.out.println("树空");
            return null;
        }
        if (searchNode(value) == null){
            System.out.println("结点不存在");
            return null;
        }
        if (root.data == value){
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 删除节点
     * @param value 要删除节点的值
     * @return 返回已删除的结点
     */
    public Node deleteNode(int value){
        if (root == null){
            System.out.println("树空");
            return null;
        }
        // 此二叉树只有一个根节点，且要删除的结点就是根节点
        if (root.left == null && root.right == null && root.data == value){
            Node temp = root;
            root = null;
            return temp;
        }
        Node node = searchNode(value);
        if (node == null){
            System.out.println("此节点不存在");
            return null;
        }
        Node parent = searchParent(value);
        // 第一种情况：删除的结点是叶子结点，则找到其父节点直接删除
        if (node.left == null && node.right == null){
            if (parent.left != null && value == parent.left.data){
                parent.left = null;
            } else if (parent.right != null && value == parent.right.data){
                parent.right = null;
            }
            return node;
        } else if (node.left != null && node.right != null){
            // 第三种情况：删除的结点有两个子树，则在其右子树中找到值最小的结点删除，并将其值赋给要删除的那个结点，或者左子树中找最大的
            Node temp = null;
            Node minNode = searchMinNode(node.right);
            temp = minNode;
            deleteNode(minNode.data);
            node.data = temp.data;
            return temp;
        } else {
            // 第二种情况：删除的结点有一个子树，则将其子树直接连接到其父节点上
            if (node.left != null){
                // 要删除的结点只有左子树
                if (parent != null){
                    if (parent.left.data == value){
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
                if (parent != null){
                    if (parent.left.data == value){
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
