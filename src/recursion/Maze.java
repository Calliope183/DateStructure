package recursion;

import java.util.Arrays;

/**
 * @description: 利用递归回溯解决迷宫问题
 * @author: WuW
 * @create: 2022/1/4 19:44
 */
public class Maze {

    public static void main(String[] args) {

        // 使用数组模拟地图
        int[][] map = new int[7][8];
        // 初始化数组
        Arrays.fill(map[0], 1);
        Arrays.fill(map[6], 1);
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][7] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] = 1;
        setWay(map, 1 , 1);

        for (int[] ints : map) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }

    }


    public static boolean setWay(int[][] map, int i, int j) {
        if (map[5][6] == 2) {
            return true;
        } else if(map[i][j] == 0){
            map[i][j] = 2;// 假定能走通
            // 使用回溯判断是否能走通
            if(setWay(map, i+1, j)){// 下
                return true;
            } else if (setWay(map, i, j+1)){// 右
                return true;
            } else if (setWay(map, i -1, j)){// 上
                return true;
            } else if (setWay(map, i, j -1)){// 左
                return true;
            } else {
                map[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }
    }


}
