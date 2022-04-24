package sort_;

import java.util.Arrays;

/**
 * @description: 希尔排序
 * @author: WuW
 * @create: 2022/1/5 21:39
 */
public class ShellSorting {

    public static void main(String[] args) {
        int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSortingChange(array);
        shellSortingSlide(array);
        System.out.println(Arrays.toString(array));
    }


    /**
     * 希尔排序交换法
     * @param array
     */
    public static void shellSortingChange(int[] array) {

        int count = 0;
        // 每次取步长为(上次的步长除以2)，当步长等于1的时候整个数组就已经分成一组了
        // 这时候再进行最后一次排序就可得到最终的结果
        for (int step = array.length / 2; step > 0; step /= 2) {
            // 对每组进行排序，共有数组(长度/步长)组
            for (int i = step; i < array.length; i++) {
                // 每组中的元素之间相隔(步长)个单位
                for (int j = i - step; j >= 0; j -= step) {
                    System.out.println("test.........."+array[j] +"?"+ array[j + step]);
                    if (array[j] > array[j + step]) {
                        int temp = array[j];
                        array[j] = array[j + step];
                        array[j + step] = temp;
                    }
                }
            }
            System.out.println("第" + (++count) + "轮循环之后：" + Arrays.toString(array));
        }
    }


    /**
     * 希尔排序移位法
     * 底层算法：增量 + 直接插入排序
     * @param array
     */
    public static void shellSortingSlide(int[] array){
        for (int step = array.length / 2; step > 0; step /= 2) {
            for (int i = step; i < array.length; i++) {
                int j = i;
                int temp = array[j];
                while(j - step >= 0 && temp < array[j - step]){
                    array[j] = array[j - step];
                    j = j - step;
                }
                array[j] = temp;
            }
        }
    }


}
