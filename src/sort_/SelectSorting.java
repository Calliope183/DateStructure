package sort_;

import java.util.Arrays;

/**
 * @description: 选择排序
 * @author: WuW
 * @create: 2022/1/5 19:31
 */
public class SelectSorting {
    public static void main(String[] args) {
        int[] array = new int[]{-23, 200, -200, 26, 10};
        selectSorting(array);
        System.out.println(Arrays.toString(array));

    }


    private static void selectSorting(int[] array){

        int index = 0;

        while (index < array.length) {

            // 寻找最小值
            int minValue = array[index];
            int minIndex = index;
            for (int i = index + 1; i < array.length; i++) {
                if (minValue > array[i]){
                    minValue = array[i];
                    minIndex = i;
                }
            }

            if(minIndex != index){
                array[minIndex] = array[index];
                array[index] = minValue;
            }
            index++;

        }// while循环完


    }
}
