package tree_.binary_tree;

/**
 * @description: 二叉树基本操作
 * @author: WuW
 * @create: 2022/1/16 17:12
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        Node node1 = new Node(1, "貂蝉");
        Node node2 = new Node(2, "王昭君");
        Node node3 = new Node(3, "甄姬");
        Node node4 = new Node(4, "安琪拉");
        Node node5 = new Node(5, "安琪拉");

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(node1);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node4.left = node5;

        System.out.println("……………………前序遍历……………………");
        binaryTree.prefixOrder(binaryTree.getRoot());
        System.out.println();
        System.out.println("……………………中序遍历………………………");
        binaryTree.infixOrder(binaryTree.getRoot());
        System.out.println();
        System.out.println("……………………后序遍历………………………");
        binaryTree.suffixOrder(binaryTree.getRoot());
        System.out.println();


        System.out.println("……………………前序查找………………………");
        System.out.println(binaryTree.prefixSearch(binaryTree.getRoot(), 3));
        System.out.println();

        System.out.println("……………………中序查找………………………");
        System.out.println(binaryTree.infixSearch(binaryTree.getRoot(), 3));
        System.out.println();

        System.out.println("……………………后序查找………………………");
        System.out.println(binaryTree.suffixSearch(binaryTree.getRoot(), 3));
        System.out.println();

        System.out.println("……………………删除后………………………");
        binaryTree.deleteNode(binaryTree.getRoot(), 4);
        System.out.println("先序遍历");
        binaryTree.prefixOrder(binaryTree.getRoot());
        System.out.println("中序遍历");
        binaryTree.infixOrder(binaryTree.getRoot());
        System.out.println("后序遍历");
        binaryTree.suffixOrder(binaryTree.getRoot());
        System.out.println();




    }

}

class BinaryTree{

    private Node root; // 根节点

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 前序遍历
     * @param curNode 当前节点
     * 输出前序遍历序列
     */
    public void prefixOrder(Node curNode){
        if (root == null){
            System.out.println("树空！！");
            return;
        }
        System.out.println(curNode);
        if (curNode.left != null){
            prefixOrder(curNode.left);
        }
        if (curNode.right != null){
            prefixOrder(curNode.right);
        }
    }

    /**
     * 中序遍历
     * @param curNode 当前节点
     * 输出中序遍历序列
     */
    public void infixOrder(Node curNode){
        if (root == null){
            System.out.println("树空！！");
            return;
        }
        if (curNode.left != null){
            infixOrder(curNode.left);
        }
        System.out.println(curNode);
        if (curNode.right != null){
            infixOrder(curNode.right);
        }
    }

    /**
     * 后序遍历
     * @param curNode 当前节点
     * 输出后序遍历序列
     */
    public void suffixOrder(Node curNode){
        if (root == null){
            System.out.println("树空！！");
            return;
        }
        if (curNode.left != null){
            suffixOrder(curNode.left);
        }
        if (curNode.right != null){
            suffixOrder(curNode.right);
        }
        System.out.println(curNode);
    }

    /**
     * 前序查找
     * @param curNode 当前节点
     * @param id 要查找的结点id
     * @return 返回找到的结点
     */
    public Node prefixSearch(Node curNode, int id){
        if (root == null){
            System.out.println("树空！！！");
            return null;
        }
        Node res = null;
        if (curNode.getId() == id){
            res =  curNode;
        }
        if (curNode.left != null){
            res =  prefixSearch(curNode.left, id);
        }
        // 如果在左子树中找到要查的结点，则直接返回
        if (res != null){
            return res;
        }
        if (curNode.right != null){
            res =  prefixSearch(curNode.right, id);
        }

        return res;

    }

    /**
     * 中序查找
     * @param curNode 当前节点
     * @param id 要查找的结点id
     * @return 返回找到的结点
     */
    public Node infixSearch(Node curNode, int id){
        if (root == null){
            System.out.println("树空！！！");
            return null;
        }
        Node res = null;
        if (curNode.left != null){
            res =  infixSearch(curNode.left, id);
        }
        if (res != null){
            return res;
        }
        if (curNode.getId() == id){
            res =  curNode;
        }
        if (curNode.right != null){
            res =  infixSearch(curNode.right, id);
        }
        return res;

    }

    /**
     * 后序查找
     * @param curNode 当前节点
     * @param id 要查找的结点id
     * @return 返回找到的结点
     */
    public Node suffixSearch(Node curNode, int id){
        if (root == null){
            System.out.println("树空！！！");
            return null;
        }
        Node res = null;
        if (curNode.left != null){
            res =  suffixSearch(curNode.left, id);
        }
        if (res != null){
            return res;
        }
        if (curNode.right != null){
            res =  suffixSearch(curNode.right, id);
        }
        if (curNode.getId() == id){
            res =  curNode;
        }
        return res;

    }

    /**
     * 删除节点
     * @param curNode 当前节点
     * @param id 要删除结点的id
     * @return 返回要删除的结点
     */
    public Node deleteNode(Node curNode, int id){

        Node deleteNode;

        // 树为空
        if (root == null){
            System.out.println("树空");
            return null;
        }

        // 只有根节点的情况下，则直接删除根节点
        if (root.left == null && root.right == null){
            deleteNode = root;
            root = null;
            return deleteNode;
        }

        // 如果要删除的结点为当前节点的左节点，则删除
        if (curNode.left != null){
            if (curNode.left.getId() == id){
                deleteNode = curNode.left;
                curNode.left = null;
                return deleteNode;
            }
            // 否则继续向左查找删除
            deleteNode(curNode.left, id);
        }
        // 要删除的结点是右节点
        if (curNode.right != null){
            if (curNode.right.getId() == id){
                deleteNode = curNode.right;
                curNode.right = null;
                return deleteNode;
            }
            // 接续向右子树查找删除
            deleteNode(curNode.right, id);
        }

        return null;
    }


}

class Node{

    private int id;
    private String name;
    Node left;  // 左节点
    Node right; // 右节点

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
                '}';
    }
}
