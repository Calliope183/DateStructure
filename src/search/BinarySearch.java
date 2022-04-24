package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 二分查找，要求数组有序
 * @author: WuW
 * @create: 2022/1/15 10:33
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arrays = new int[]{1, 2, 2 ,6 ,8};

        int index = binarySearch(arrays, 0);
        System.out.println("binarySearch索引下标为" + index);

        int index01 = binarySearch01(arrays, 0, arrays.length-1, 0);
        System.out.println("binarySearch01索引下标为" + index01);

        List<Integer> list = binarySearch02(arrays, 0, arrays.length - 1, 2);
        System.out.println(list);
    }

    /**
     * 二分查找法
     * @param arrays 待查找数组
     * @param ele 待查找元素
     * @return 返回找到的第一个匹配元素下标
     */
    public static int binarySearch(int[] arrays, int ele){
        int low = 0;
        int high = arrays.length - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if (arrays[mid] == ele){
                return mid;
            } else if(ele < arrays[mid]){
                // 待查找元素在左边区域
                high = mid - 1;
            } else {
                // 待查找元素在右边区域
                low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 二分查找法——递归
     * @param arrays 待查找数组
     * @param left 左边索引
     * @param right 右边索引
     * @param ele 待查找元素
     * @return 返回找到的索引
     */
    public static int binarySearch01(int[] arrays, int left, int right, int ele){

        // 没找到时，递归退出条件
        if (left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arrays[mid];
        if (ele > midValue){
            // 待查找元素在右边区域
            return binarySearch01(arrays, mid + 1, right, ele);
        } else if (ele < midValue){
            // 待查找元素在左边区域
            return binarySearch01(arrays, left, mid - 1, ele);
        } else {
            // 找到了
            return mid;
        }
    }

    /**
     * 二分法改进：返回所有找到的索引
     * @param arrays 待查找数组
     * @param left 左索引
     * @param right 右索引
     * @param ele 待查找元素
     * @return 返回所有符合条件的集合
     */
    public static List<Integer> binarySearch02(int[] arrays, int left, int right, int ele){
        // 没找到时，递归退出条件
        if (left > right){
            return null;
        }
        int mid = (left + right) / 2;
        int midValue = arrays[mid];
        if (ele > midValue){
            // 待查找元素在右边区域
            return binarySearch02(arrays, mid + 1, right, ele);
        } else if (ele < midValue){
            // 待查找元素在左边区域
            return binarySearch02(arrays, left, mid - 1, ele);
        } else {
            // 找到了
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            // 向左看
            while(!(temp < 0 || arrays[temp] != ele)){
                list.add(temp);
                temp--;
            }
            // 向右看
            while(!(temp > arrays.length - 1 || arrays[temp] != ele)){
                list.add(temp);
                temp++;
            }
            list.add(mid);
            return list;
        }
    }
}
