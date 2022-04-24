package tree_.array_binary_tree;

/**
 * @description: 顺序存储二叉树
 * @author: WuW
 * @create: 2022/1/18 20:40
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {

        int[] array = new int[]{1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        System.out.println("前序遍历");
        arrayBinaryTree.prefixOrder();
        System.out.println();
        System.out.println("中序遍历");
        arrayBinaryTree.infixOrder();
        System.out.println();
        System.out.println("后序遍历");
        arrayBinaryTree.suffixOrder();

    }

}


class ArrayBinaryTree{
    public int[] array;

    public ArrayBinaryTree(int[] array){
        this.array = array;
    }

    /**
     * 方法重载
     */
    public void prefixOrder(){
        this.prefixOrder(0);
    }
    public void infixOrder(){
        this.infixOrder(0);
    }
    public void suffixOrder(){
        this.suffixOrder(0);
    }

    /**
     * 前序遍历
     * @param index 当前元素的索引
     */
    public void prefixOrder(int index){
        if (array == null || array.length == 0){
            System.out.println("树空！");
            return;
        }
        System.out.print(array[index] + "\t");
        if ((index * 2 + 1) < array.length){
            prefixOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < array.length){
            prefixOrder(index * 2 + 2);
        }
    }

    /**
     * 中序遍历
     * @param index 当前元素的索引
     */
    public void infixOrder(int index){
        if (array == null || array.length == 0){
            System.out.println("树空！");
            return;
        }
        if ((index * 2 + 1) < array.length){
            infixOrder(index * 2 + 1);
        }
        System.out.print(array[index] + "\t");
        if ((index * 2 + 2) < array.length){
            infixOrder(index * 2 + 2);
        }
    }

    /**
     * 中序遍历
     * @param index 当前元素的索引
     */
    public void suffixOrder(int index){
        if (array == null || array.length == 0){
            System.out.println("树空！");
            return;
        }
        if ((index * 2 + 1) < array.length){
            suffixOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < array.length){
            suffixOrder(index * 2 + 2);
        }
        System.out.print(array[index] + "\t");
    }

}