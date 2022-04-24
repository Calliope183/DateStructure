package search;

/**
 * @description: 插值查找，要求数组有序
 * @author: WuW
 * @create: 2022/1/15 10:49
 * 对于数据量较大，关键字分布较均匀的查找表，插值查找速度由于二分查找
 * 关键字分布不均匀时，不一定比二分效率高
 */
public class InterpolationSearch {


    public static void main(String[] args) {
        int[] arrays = new int[]{1, 2, 5 ,6 ,8};
        int index = interpolationSearch(arrays, 8);
        System.out.println("索引为" + index);
    }

    /**
     * 插值查找，二分查找的优化
     * @param arrays 待查找数组
     * @param ele 待查找元素
     * @return 返回找到的第一个元素索引
     */
    public static int interpolationSearch(int[] arrays, int ele){

        // 必须进行判断，否则可能会出现mid越界的现象
        if (ele > arrays[arrays.length - 1] || ele < arrays[0]){
            return -1;
        }
        int low = 0;
        int high = arrays.length - 1;
        while(low <= high){
            // 二分查找优化，主要解决查找的元素是有序数组的第一个或最后一个
            int mid = low + (high - low) * ((ele - arrays[low]) / (arrays[high] - arrays[low]));
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
}
