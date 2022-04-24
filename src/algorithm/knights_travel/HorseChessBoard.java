package algorithm.knights_travel;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: 马踏棋盘算法
 * @author: WuW
 * @create: 2022/3/2 11:03
 */
public class HorseChessBoard {

    private int X;
    private int Y;
    private boolean[] visited;
    private boolean finished;

    @Test
    public void test(){
        X = 8;
        Y = 8;
        visited = new boolean[X * Y];
        int[][] chessboard = new int[X][Y];
        long start = System.currentTimeMillis();
        travelChessBoard(chessboard, 0, 0, 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        for (int[] ints : chessboard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 骑士周游
     * @param chessboard 棋盘
     * @param row 表示马儿当前处于第几行
     * @param column 表示马儿当前处于第几列
     * @param step 马儿当前走了多少步
     */
    public void travelChessBoard(int[][] chessboard, int row, int column, int step){
        // 假设能走通
        chessboard[row][column] = step;
        visited[row * X + column] = true;
        // 获取下一个要走的位置
        ArrayList<Point> next = next(new Point(column, row));
        sort(next);
        while (!next.isEmpty()){
            Point point = next.remove(0);
            if (!visited[point.y * X + point.x]){
                travelChessBoard(chessboard, point.y, point.x, step + 1);
            }
        }
        // 说明没有走通
        if (step < X * Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 马儿下一个可以走的位置
     * @param current 马儿当前位置
     * @return 返回马克下一步可以走的位置的集合
     */
    public ArrayList<Point> next(Point current){
        ArrayList<Point> points = new ArrayList<>();
        Point point = new Point();
        if ((point.x = current.x - 2) >= 0 && (point.y = current.y - 1) >= 0){
            points.add(new Point(point));
        }// 5
        if ((point.x = current.x - 1) >= 0 && (point.y = current.y - 2) >= 0){
            points.add(new Point(point));
        }// 6
        if ((point.x = current.x + 1) < X && (point.y = current.y - 2) >= 0){
            points.add(new Point(point));
        }// 7
        if ((point.x = current.x + 2) < X && (point.y = current.y - 1) >= 0){
            points.add(new Point(point));
        }// 0
        if ((point.x = current.x + 2) < X && (point.y = current.y + 1) < Y){
            points.add(new Point(point));
        }// 1
        if ((point.x = current.x + 1) < X && (point.y = current.y + 2) < Y){
            points.add(new Point(point));
        }// 2
        if ((point.x = current.x - 1) >= 0 && (point.y = current.y + 2) < Y){
            points.add(new Point(point));
        }// 3
        if ((point.x = current.x - 2) >= 0 && (point.y = current.y + 1) < Y){
            points.add(new Point(point));
        }// 4
        return points;
    }

    public void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2){
                    return -1;
                } else if (count1 == count2){
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

}


