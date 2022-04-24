package algorithm.prim;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @description: 最小生成树之普利姆算法
 * @author: WuW
 * @create: 2022/2/25 18:15
 */
public class PrimAlgorithm {

    @Test
    public void test(){
        MinTree minTree = new MinTree();
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertx = data.length;
        int[][] edges = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        // 创建图
        MGraph graph = minTree.createGraph(vertx, data, edges);
        System.out.println("————————————图邻接矩阵——————————");
        minTree.showGraph(graph);
        System.out.println("————————————图邻接矩阵——————————");
        System.out.println("——————————prim算法结果—————————");
        minTree.prim(graph, 0);
        System.out.println("——————————prim算法结果—————————");
    }

}

class MinTree{

    /**
     * 创建图
     * @param vertx 顶点数
     * @param data 顶点值
     * @param edges 邻接表
     */
    public MGraph createGraph(int vertx, char[] data, int[][] edges){
        MGraph mGraph = new MGraph(vertx);
        for (int i = 0; i < vertx; i++) {
            mGraph.data[i] = data[i];
            System.arraycopy(edges[i], 0, mGraph.edges[i], 0, vertx);
        }
        return mGraph;
    }

    /**
     * 显示邻接矩阵
     * @param mGraph 图
     */
    public void showGraph(MGraph mGraph){
        for (int[] edge : mGraph.edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * prim算法生成最小生成树
     * @param mGraph 图
     * @param v 从第几个顶点开始
     */
    public void prim(MGraph mGraph, int v){
        int[] visited = new int[mGraph.numberOfVertx];
        visited[v] = 1;
        int h1 = -1,h2 = -1;
        int minWeight = 10000;
        // 共有numberOfVertx个顶点，则生成的最小生成树共有numberOfVertx-1条边
        for (int k = 1; k < mGraph.numberOfVertx; k++){
            // 遍历找到最小权值的边
            for (int i = 0; i < mGraph.numberOfVertx; i++) {// i表示被访问过的结点
                for (int j = 0; j < mGraph.numberOfVertx; j++) {// j表示未被访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && mGraph.edges[i][j] < minWeight){
                        minWeight = mGraph.edges[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }// 退出循环的时候表明已经找到了一条权值最小的边
            visited[h2] = 1;
            System.out.println("边：<"+mGraph.data[h1]+", "+mGraph.data[h2]+">    权值：["+minWeight+"]");
            minWeight = 10000;
        }
    }

}

class MGraph{
    int numberOfVertx;
    char[] data;
    int[][] edges;

    public MGraph(int numberOfVertx){
        this.numberOfVertx = numberOfVertx;
        data = new char[numberOfVertx];
        edges = new int[numberOfVertx][numberOfVertx];
    }
}
