package tree_.heap_sort;


import java.util.Arrays;

/**
 * @description: 堆排序
 * @author: WuW
 * @create: 2022/1/22 19:43
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{4,6,8,5,9};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 堆排序
     * @param array 待排序的数组
     */
    public static void heapSort(int[] array){
        int temp;
        // 将数组构造成大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            toHeap(array, i, array.length);
        }


        for (int j = array.length - 1; j > 0; j--) {
            // 将堆顶元素与最后一个元素进行交换
            temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            // 重新调整使之成为大顶堆
            toHeap(array, 0, j);
        }
    }

    /**
     * 将以array[i]为结点的子树转换成大顶堆
     * @param array 待排序的数组
     * @param i 非叶子结点的数组下标
     * @param length 待排序的数组的长度
     */
    public static void toHeap(int[] array, int i, int length){
        int temp = array[i];
        for (int k = i * 2 + 1; k < length; k = i * 2 + 1) {
            if (k + 1 < length && array[k] < array[k + 1]){
                // 左子节点小于右子节点
                k++;// k为左右子节点中较小的数组下标
            }
            if (array[k] > temp){
                array[i] = array[k];
                array[k] = temp;
                i = k;// 此时i为原左右节点中较小的数组下标，以便继续往下循环
            } else {
                break;// 此时以array[i]为节点的子树已经是大顶堆
            }
        }
    }
}
