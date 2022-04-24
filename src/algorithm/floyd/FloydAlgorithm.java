package algorithm.floyd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @description: 弗洛伊德算法
 * @author: WuW
 * @create: 2022/2/28 10:23
 */
public class FloydAlgorithm {

    @Test
    public void test(){
        char[] vertx = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] edges = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        Graph graph = new Graph(vertx, edges);
        floyd(graph);
        show(graph);
    }
    
    public void floyd(Graph graph){
        int[][] edges = graph.getEdges();
        int[][] res = graph.getRes();
        int[][] mid = graph.getMid();
        char[] vertx = graph.getVertx();
        int len = vertx.length;
        for (int k = 0; k < len; k++) { // 中间顶点
            for (int i = 0; i < len; i++) { // 起点
                for (int j = 0; j < len; j++) { // 终点
                    if (res[i][k] + res[k][j] < res[i][j]){
                        res[i][j] = res[i][k] + res[k][j];
                        res[j][i] = res[i][k] + res[k][j];
                        mid[i][j] = k;
                        mid[j][i] = k;
                    }
                }
            }
        }

    }

    /**
     * 显示结果
     * @param graph 图（包括土的基本信息和每个顶点到其他顶点的最短距离）
     */
    public void show(Graph graph){
        for (int i = 0; i < graph.getVertx().length; i++) {
            for (int j = i + 1; j < graph.getVertx().length; j++) {
                System.out.println(graph.getVertx()[i] + "到" + graph.getVertx()[j]
                        + "的最短距离为" + graph.getRes()[i][j] +
                        "(通过"+graph.getVertx()[graph.getMid()[i][j]]+")"
                );
            }
        }
    }

}
class Graph{
    private char[] vertx;
    private int[][] edges;
    private int[][] mid;
    private int[][] res;

    public char[] getVertx() {
        return vertx;
    }

    public int[][] getEdges() {
        return edges;
    }

    public int[][] getMid() {
        return mid;
    }

    public int[][] getRes() {
        return res;
    }

    public Graph(char[] vertx, int[][] edges){

        this.vertx = vertx;
        this.edges = edges;
        this.mid = new int[vertx.length][vertx.length];
        this.res = new int[vertx.length][vertx.length];
        for (int i = 0; i < vertx.length; i++) {
            Arrays.fill(mid[i], i);
        }
        int n = 65535;
        this.res = Arrays.copyOf(edges, edges.length);
    }
}
