package algorithm.binary_search;

import org.junit.jupiter.api.Test;

/**
 * @description: 二分查找算法
 * @author: WuW
 * @create: 2022/2/21 20:16
 */
public class BinarySearch {

    @Test
    public void test(){
        int[] arrays = {1, 3, 5, 6, 7, 8};
        int index = binarySearchNoRecursion(arrays, 10);
        System.out.println(index);
    }

    /**
     * 二分查找递归实现
     * @param low 低处的索引
     * @param high 高处索引
     * @param arrays 查找数组
     * @param target 要查找的元素
     * @return 返回target在arrays中的索引
     */
    public int binarySearchRecursion(int low, int high, int[] arrays, int target){

        int mid = (low + high) / 2;
        if (mid > arrays.length - 1 || mid < 0 || low > high) return -1;
        if (target == arrays[mid]) {
            return mid;
        } else if (target < arrays[mid]){
            high = mid - 1;
            return binarySearchRecursion(low, high, arrays, target);
        } else {
            low = mid + 1;
            return binarySearchRecursion(low, high, arrays, target);
        }
    }

    /**
     * 递归二分查找方法重载
     * @param arrays 查找数组
     * @param target 要查找的元素
     * @return 返回target在arrays中的索引
     */
    public int binarySearchRecursion(int[] arrays, int target){
        return binarySearchRecursion(0, arrays.length - 1, arrays, target);
    }

    /**
     * 二分查找非递归
     * @param arrays 查找数组
     * @param target 待查找数据
     * @return 返回target在arrays中的索引
     */
    public int binarySearchNoRecursion(int[] arrays, int target){
        int low = 0;
        int high = arrays.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if (target == arrays[mid]){
                return mid;
            } else if (target > arrays[mid]){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
