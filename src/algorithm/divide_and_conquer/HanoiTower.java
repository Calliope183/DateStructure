package algorithm.divide_and_conquer;

import org.junit.jupiter.api.Test;

/**
 * @description: 汉诺塔
 * @author: WuW
 * @create: 2022/2/21 21:32
 */
public class HanoiTower {

    @Test
    public void test(){
        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔实现
     * @param number 圆盘个数
     * @param a 一塔
     * @param b 二塔
     * @param c 三塔
     */
    public void hanoiTower(int number, char a, char b, char c){
        if (number == 1) {
            System.out.println("第" + number + "个圆盘：" + a + " -> "+ c);
        } else {
            // 把除了最底下那个圆盘剩下的圆盘从a借助c移动到b
            hanoiTower(number - 1, a, c, b);
            // 把最底下的圆盘从a移动到c
            System.out.println("第" + number + "个圆盘：" + a + " -> "+ c);
            // 把b上圆盘借助a递归的移动到c
            hanoiTower(number - 1, b, a, c);
        }
    }

}
