package search;

import java.util.Arrays;

/**
 * @description: 斐波那契查找
 * @author: WuW
 * @create: 2022/1/15 10:58
 */
public class FibonacciSearch {

    public static void main(String[] args) {

        int[] arrays = new int[]{1, 34, 123, 334, 556, 1234};
        int index = fibonacciSearch(arrays, 1234);
        System.out.println(index);

    }

    /**
     * 斐波那契查找
     * @param arrays 待查找数组
     * @param ele 待查找元素
     * @return 返回查找到的第一个元素索引下标
     */
    public static int fibonacciSearch(int[] arrays, int ele){
        int low = 0;
        int high = arrays.length - 1;
        int k = 0;
        int mid = 0;
        int[] f = fib(20);
        // 在斐波那契数组中找到一个大于等于数组长度的数
        while(high > f[k] - 1){
            k++;
        }

        // 构建一个长度为f[k]的数组
        int[] temp = Arrays.copyOf(arrays, f[k]);
        // 多余的位数由arrays中最高位的数填充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arrays[high];
        }

        while (low <= high){
            mid = low + f[k - 1] - 1;
            if (ele < temp[mid]){
                // 数在左边
                high = mid - 1;
                k --;
            } else if (ele > temp[mid]){
                // 数在右边
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high){
                    return mid;
                } else {
                    return high;
                }
            }
        }

        return -1;
    }

    /**
     * 获取fibonacci数组
     * @param size 数组长度
     * @return
     */
    public static int[] fib(int size){
        int[] f = new int[size];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i-1] + f[i-2];
        }

        return f;
    }


}
