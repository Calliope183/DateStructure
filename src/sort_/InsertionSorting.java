package sort_;

import java.util.Arrays;

/**
 * @description: 插入排序
 * @author: WuW
 * @create: 2022/1/5 19:58
 */
public class InsertionSorting {
    public static void main(String[] args) {
        int[] array = new int[]{-23, 200, -200, 26, 10};
//        myInsertionSorting(array);
        selectSorting(array);
        System.out.println(Arrays.toString(array));
    }

    private static void myInsertionSorting(int[] array) {

        int index = 1;
        while (index < array.length) {
            int disorderHead = array[index];
            // 判断是否需要进行插入，如果他大于有序表中的最后一个数，直接进行下一轮插入
            if (disorderHead > array[index - 1]) {
                index++;
                continue;
            }
            // 如果待插入的数小于有序表中的第一个数，则将其放到第一个位置，其他的元素后移
            if (disorderHead < array[0]) {
                for (int j = index; j > 0; j--) {
                    array[j] = array[j - 1];
                }
                array[0] = disorderHead;
                index++;
                continue;
            }

            // 如果不是上述两种情况，则循环查找合适的位置并将其后元素向后移
            for (int i = 0; i < index; i++) {
                if (disorderHead > array[i] && disorderHead <= array[i + 1]) {
                    // 从i+1往后的元素往后移动
                    for (int j = index; j >= i + 1; j--) {
                        array[j] = array[j - 1];
                    }
                    array[i + 1] = disorderHead;
                    break;
                }
            }

            index++;
        }// while循环结束


    }


    private static void selectSorting(int[] array) {
        int index = 1;
        while (index < array.length) {
            int insertValue = array[index];// 待插入的数
            int insertIndex = index - 1;// 合适的位置
            // 为待插入的数找到合适的位置，从待插入数之前往前寻找
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != index){
                array[insertIndex + 1] = insertValue;
            }
            index++;
        }// while循环完成
    }

}
