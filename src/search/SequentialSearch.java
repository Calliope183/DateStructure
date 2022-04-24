package search;

import org.junit.jupiter.api.Test;

/**
 * @description: 顺序查找，返回第一个索引
 * @author: WuW
 * @create: 2022/1/15 10:24
 */
public class SequentialSearch {


    public static void main(String[] args) {
        int[] arrays = new int[]{23, 34, 45, 34, 78};
        int index = sequentialSearch(arrays, 34);
        System.out.println(index);
    }

    /**
     * 顺序查找
     * @param arrays 待查找的数组
     * @param ele 待查找元素
     * @return 查找到的元素索引下标，没找到返回-1
     */
    public static int sequentialSearch(int[] arrays, int ele){

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == ele){
                return i;
            }
        }
        return -1;

    }

}
