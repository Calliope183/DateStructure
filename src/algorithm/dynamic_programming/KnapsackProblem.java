package algorithm.dynamic_programming;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @description: 背包问题
 * @author: WuW
 * @create: 2022/2/21 21:48
 */
public class KnapsackProblem {

    @Test
    public void test(){
        int[] weight = {1, 4, 3};
        int[] value = {1500, 3000, 2000};
        int capacity = 4;
        knapsackProblem(weight, value, capacity);
    }

    public void knapsackProblem(int[] weight, int[] value, int capacity){
        int[][] v = new int[weight.length + 1][capacity + 1];
        int[][] path = new int[weight.length + 1][capacity + 1];
        // 第一行第一列置零
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                // 若当前要放的物品的重量大于背包容量
                if (weight[i - 1] > j){
                    v[i][j] = v[i - 1][j];
                } else {
                    int currentValue = 0;
                    // 若背包容量大于等于将要放入的物品的重量
                    if (j - weight[i - 1] > 0){
                        // 有剩余空间
                        currentValue = value[i - 1] + v[i - 1][j - weight[i - 1]];
                    } else {
                        // 没有剩余空间
                        currentValue = value[i - 1];
                    }
                    if (currentValue > v[i - 1][j]){
                        v[i][j] = currentValue;
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        System.out.println("存放情况：");
        for (int[] rows : v) {
            System.out.println(Arrays.toString(rows));
        }

        System.out.println("path：");
        for (int[] rows : path) {
            System.out.println(Arrays.toString(rows));
        }

        System.out.println("存放方法：");
        int i = path.length - 1;
        int j = path[0].length - 1;
        while(i > 0 && j > 0){
            if (path[i][j] == 1){
                System.out.println("第"+ i +"个商品被放入背包");
                j -= weight[i - 1];
            }
            i--;
        }

    }

}
