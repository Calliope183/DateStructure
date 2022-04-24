package sort_;

import java.util.Arrays;

/**
 * @description:
 * @author: WuW
 * @create: 2022/1/10 22:21
 */
public class MergeSorting {
    public static void main(String[] args) {
        int[] array = new int[]{-9, 78, 0, 23, -123, 70};
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
        System.out.println(Arrays.toString(array));
    }


    public static void mergeSort(int[] arrays, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // left
            mergeSort(arrays, left, mid, temp);
            // right
            mergeSort(arrays, mid + 1, right, temp);
            merge(arrays, left, mid, right, temp);
        }
    }

    /**
     * @param arrays
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    public static void merge(int[] arrays, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        // minEle-->temp[]
        while (i <= mid && j <= right) {
            if (arrays[i] <= arrays[j]) {
                temp[t] = arrays[i];
                t++;
                i++;
            } else {
                temp[t] = arrays[j];
                t++;
                j++;
            }
        }

        // leftEle
        while (i <= mid) {
            temp[t] = arrays[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arrays[j];
            t++;
            j++;
        }

        // temp-->arrays
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arrays[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }

}
