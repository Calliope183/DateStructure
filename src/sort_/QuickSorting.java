package sort_;

import java.util.Arrays;

/**
 * @description: 快速排序
 * @author: WuW
 * @create: 2022/1/6 21:30
 */
public class QuickSorting {
    public static void main(String[] args) {
        int[] array = new int[]{-9, 78, 0, 23, -123, 70};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }


    /**
     * 快速排序
     * @param array
     * @param left  左下标
     * @param right 右下标
     */
    public static void quickSort(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int pivot = array[(left + right) / 2];
        int temp = 0;
        // while循环，将比pivot小的值放左边，比pivot大的值放右边
        while (l < r) {
            // 从左边开始找到第一个比pivot大的数
            while (array[l] < pivot) {
                l += 1;
            }
            // 从右边开始找到第一个比pivot小的数
            while (array[r] > pivot) {
                r -= 1;
            }
            //此时说明pivot左边的数据都小于pivot，右边的数据都大于pivot
            if (l >= r) {
                break;
            }

            // 交换
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            // 如果交换之后,array[l] == pivot，r前移，
            if (array[l] == pivot) {
                r -= 1;
            }
            if (array[r] == pivot) {
                l += 1;
            }
        }

        // 如果l==r，则必须进行处理，负责会发生栈溢出
        if(l == r){
            l += 1;
            r -= 1;
        }

        // 递归左边
        if (left < r){
            quickSort(array, left, r);
        }
        // 递归右边
        if (l < right){
            quickSort(array, l, right);
        }
    }
}
