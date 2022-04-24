package algorithm.dijkstra;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @description: 迪杰斯特拉算法求最短路径
 * @author: WuW
 * @create: 2022/2/27 10:20
 */
public class DijkstraAlgorithm {

    @Test
    public void test() {
        char[] vertx = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] edges = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        Graph graph = new Graph(vertx, edges);
        VisitedGroup visitedGroup = new VisitedGroup(graph.vertx.length, 6);
        dijkstra(6, graph, visitedGroup);
    }

    public void dijkstra(int index, Graph graph, VisitedGroup visitedGroup) {
        update(index, graph, visitedGroup);
        for (int i = 0; i < graph.vertx.length; i++) {
            int nextVertx = visitedGroup.nextVertx();
            update(nextVertx, graph, visitedGroup);
        }
        showRes(index, graph, visitedGroup);
    }

    /**
     * 显示结果——最短路径
     * @param index 表明从哪个顶点出发
     * @param visitedGroup 访问数组
     */
    public void showRes(int index, Graph graph, VisitedGroup visitedGroup){
        char[] vertx = graph.vertx;
        for (int i = 0; i < visitedGroup.dis.length; i++) {
            System.out.println(vertx[index] +"到" + vertx[i] + "的最短距离为" + visitedGroup.dis[i]);
        }
    }

    /**
     * 更新顶点index到周围各个顶点的最短距离
     * @param index 顶点
     * @param graph 图
     * @param visitedGroup 访问数组
     */
    public void update(int index, Graph graph, VisitedGroup visitedGroup){
        for (int i = 0; i < graph.vertx.length; i++) {
            // 从出发顶点到index顶点的距离 + index顶点到i顶点的距离
            int len = visitedGroup.getDis(index) + graph.edges[index][i];
            if (len < visitedGroup.getDis(i)){
                visitedGroup.updateDis(i, len);
                visitedGroup.updatePre(i, index);
            }
        }
    }

}

class VisitedGroup {
    // 访问过的顶点
    boolean[] visitedVertx;
    // 最短距离(某顶点到对应下标顶点的最短距离)
    int[] dis;
    // 前驱结点
    int[] preVisited;

    public VisitedGroup(int vertx, int index) {
        visitedVertx = new boolean[vertx];
        visitedVertx[index] = true;
        dis = new int[vertx];
        Arrays.fill(dis, 65535);
        dis[index] = 0;// 自身到自身的距离为0
        preVisited = new int[vertx];
    }

    /**
     * 判断顶点是否被访问过
     *
     * @param index 要判断的顶点
     * @return 访问过返回true，否则返回false
     */
    public boolean isVisited(int index) {
        return visitedVertx[index];
    }

    /**
     * 更新距离
     *
     * @param index 要更新的顶点
     * @param len   要更新的距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新前驱
     *
     * @param index 表明哪个顶点
     * @param pre   前驱
     */
    public void updatePre(int index, int pre) {
        preVisited[index] = pre;
    }

    /**
     * 获取最短距离
     * @param index 表明是哪个顶点
     * @return 返回指定顶点到index对应顶点的最短距离
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 获取下一个应该访问的顶点
     * @return 返回下一个顶点
     */
    public int nextVertx(){
        int min = 65535;
        for (int i = 0; i < visitedVertx.length; i++) {
            if (!visitedVertx[i] && dis[i] < min){
                visitedVertx[i] = true;
                return i;
            }
        }
        return 0;
    }

}

class Graph {
    char[] vertx;
    int[][] edges;

    public Graph(char[] vertx, int[][] edges) {
        this.vertx = vertx;
        this.edges = edges;
    }
}
