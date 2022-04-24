package recursion;

/**
 * @description: 利用递归回溯解决八皇后问题
 * @author: WuW
 * @create: 2022/1/4 20:54
 */
public class Queen8 {

    // 定义max表示共有多少个皇后
    int max = 9;
    // 定义数组用于存放位置的结果
    int[] array = new int[max];

    static int count;
    public static void main(String[] args) {
        new Queen8().check(0);
        System.out.println("共有"+count+"种解法!");
    }

    /**
     * 放置第n个皇后
     *
     * @param n
     */
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            // 第一次循环的时候，先将第n个皇后放在第一个位置
            array[n] = i;
            // 如果可以放置的话，接着放下一个皇后
            if (judge(n)) {
                check(n + 1);
            }
            // 如果放置不了的话，则将其放置到下一列
            // 若本行中所有列都放不了，则会回退到上一个皇后放的位置，因为每次的check中都会有for循环
        }
    }

    /**
     * 判断第n个皇后的位置是否与已经放置好的前n-1个皇后的位置冲突
     *
     * @param n
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {

            // 不用判断是否在同一行，因为i就代表的是行，每次必然不同
            // array[i] == array[n]：是否在同一列
            // Math.abs(n - i) == Math.abs(array[n] - array[i])：是否在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印最后结果
     */
    private void print() {
        count++;
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

}
