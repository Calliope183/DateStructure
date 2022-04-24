package algorithm.kruskal;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @description: 克鲁斯卡尔算法
 * @author: WuW
 * @create: 2022/2/26 14:46
 */
public class KruskalAlgorithm {

    @Test
    public void test(){
        MinTree minTree = new MinTree();
        int INF = Integer.MAX_VALUE;
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertx = data.length;
        int[][] edges = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7,INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF,3, 0, 4, INF, INF},
                {INF, INF, 5, 4 , 0, 2, 8},
                {16, 7 ,6 , INF, 2, 0 , 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        MGraph graph = minTree.createGraph(vertx, data, edges);
        minTree.kruskal(graph);
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
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i < vertx; i++) {
            for (int j = i + 1; j < vertx; j++) {
                if (edges[i][j] != INF){
                    mGraph.numberOfEdges += 1;
                }
            }
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
     * 按照边权值对边进行排序
     * @param eData 边对象集合
     */
    public void sortEdges(EData[] eData){
        for (int i = 0; i < eData.length - 1; i++) {
            for (int j = 0; j < eData.length - 1; j++) {
                if (eData[j].weight > eData[j + 1].weight){
                    EData temp = eData[j];
                    eData[j] = eData[j + 1];
                    eData[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 获取顶点c对应的下标
     * @param c 顶点
     * @return 返回顶点c在顶点集合data中的数组下标
     */
    public int getPosition(char c, char[] data){
        for (int i = 0; i < data.length; i++) {
            if (data[i] == c){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取边集合
     * @param mGraph 图
     * @return 返回图对应的边集合对象
     */
    public EData[] getEData(MGraph mGraph){
        int[][] edges = mGraph.edges;
        char[] data = mGraph.data;
        int INF = Integer.MAX_VALUE;
        int index = 0;
        EData[] eData = new EData[mGraph.numberOfEdges];
        // 扫描上半部分，将边权重不等于INF的边加进eData中
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges[i].length; j++) {
                if (edges[i][j] != INF){
                    eData[index++] = new EData(data[i], data[j], edges[i][j]);
                }
            }
        }
        return eData;
    }

    /**
     * 获取顶点i对应的最大顶点
     * @param ends
     * @param i
     * @return
     */
    public int getEnd(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    /**
     * 克鲁斯卡尔算法
     * @param mGraph 图
     */
    public void kruskal(MGraph mGraph){
        int index = 0;
        int numberOfEdges = mGraph.numberOfEdges;
        char[] data = mGraph.data;

        // 用来保存各个顶点对应的最大终点
        int[] ends = new int[numberOfEdges];
        // 创建结果数组，保存最后的最小生成树
        EData[] res = new EData[numberOfEdges];
        EData[] edges = getEData(mGraph);
        // 边排序
        sortEdges(edges);
        for (EData edge : edges) {
            // 边起点在数组中的下标
            int startPosition = getPosition(edge.start, data);
            // 边终点在数组中的下标
            int endPosition = getPosition(edge.end, data);
            // 边起点的最大终点，若数组中对应位置为0，则边的最大终点为自己
            int end1 = getEnd(ends, startPosition);
            // 边终点的最大起点
            int end2 = getEnd(ends, endPosition);
            // 若边起点与边终点的最大终点不同，说明添加这条边之后不会构成回路，则设置此起点的最大终点为边终点
            if (end1 != end2) {
                ends[end1] = end2;
                res[index++] = edge;
            }
        }
        System.out.println(Arrays.toString(res));
    }

}

class EData{
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}

class MGraph{
    private final int INF = Integer.MAX_VALUE;
    int numberOfEdges;
    char[] data;
    int[][] edges;

    public MGraph(int numberOfVertx){
        data = new char[numberOfVertx];
        edges = new int[numberOfVertx][numberOfVertx];
    }

}
