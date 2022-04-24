package sort_;

import java.util.Arrays;

/**
 * @description: 基数排序
 * @author: WuW
 * @create: 2022/1/12 10:11
 */
public class RadixSorting {
    public static void main(String[] args) {
        int[] array = new int[]{9, 78, 1, 23, 123, 70};
        radixSort(array);
        System.out.println(Arrays.toString(array));
    }


    public static void radixSort(int[] array) {


        // 10个桶，空间换时间
        int[][] bucket = new int[10][array.length];
        // 桶中实际的个数
        int[] bucketElementCounts = new int[10];


        // 得到最大数的位数，以决定要循环多少轮
        int max = array[0];
        for (int k : array) {
            if (max < k) {
                max = k;
            }
        }
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            // 将数放入对应桶中
            for (int j = 0; j < array.length; j++) {
                int digitOfElement = array[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }

            // 将桶中的数依次放回源数组中，没放一个数组，
            int index = 0;
            for (int j = 0; j < bucketElementCounts.length; j++) {
                if (bucketElementCounts[j] != 0) {
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        array[index] = bucket[j][k];
                        index++;
                    }
                }
                // 记录桶中实际的个数置零
                bucketElementCounts[j] = 0;
            }

            System.out.println(Arrays.toString(array));
        }


    }
}
