package graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 图
 * @author: WuW
 * @create: 2022/2/20 20:55
 */
public class GraphDemo {

    @Test
    public void test(){


        int n = 5;
        Graph graph = new Graph(8);
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (String vertex : vertexes) {
            graph.addVertex(vertex);
        }
        graph.addEdge(0,1,1);
        graph.addEdge(0,2,1);
        graph.addEdge(1,3,1);
        graph.addEdge(1,4,1);
        graph.addEdge(3,7,1);
        graph.addEdge(4,7,1);
        graph.addEdge(2,5,1);
        graph.addEdge(2,6,1);
        graph.addEdge(5,6,1);

        System.out.println("----------显示邻接矩阵-------");
        graph.showGraph();
        System.out.println("----------显示邻接矩阵-------");
        System.out.println("----------深度优先遍历-------");
        graph.dfs();
        System.out.println("----------深度优先遍历-------");
        System.out.println("----------广度优先遍历-------");
        graph.bfs();
        System.out.println("----------广度优先遍历-------");
    }

}

class Graph{
    private List<String> vertexList;
    private int[][] edges;
    private int numsOfEdges;
    private boolean[] isVisited;// 访问标志位，若被访问过则值为true

    public Graph(int n){
        vertexList = new ArrayList<>();
        edges = new int[n][n];
    }

    /**
     * 添加顶点
     * @param vertex 要添加的结点的值
     */
    public void addVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 顶点1
     * @param v2 顶点2
     * @param weight 权重，若边没有权重，则1表示有直连边，0表示无直连边
     */
    public void addEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numsOfEdges ++;
    }

    /**
     * 获取顶点个数
     * @return 返回顶点个数
     */
    public int getNumsOfVertex(){
        return vertexList.size();
    }

    /**
     * 获取边条数
     * @return 返回边条数
     */
    public int getNumsOfEdges(){
        return numsOfEdges;
    }

    /**
     * 返回边权值
     * @param v1 顶点1
     * @param v2 顶点2
     * @return 返回点点1到顶点2边的权值
     */
    public int getWeightByVertex(int v1, int v2){
        return edges[v1][v2];
    }

    /**
     * 获取索引对应的数据
     * @param index 索引
     * @return 返回索引对应的顶点值
     */
    public String getValueByIndex(int index){
        return vertexList.get(index);
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph(){
        for (int[] edge : edges) {
            for (int weight : edge) {
                System.out.print(weight + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 获取第一个邻接顶点
     * @param v 顶点v
     * @return 返回顶点v的第一个邻接顶点
     */
    public int getFirstNeighbor(int v){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[v][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取下一个邻接顶点
     * @param v1 顶点v1
     * @param v2 上一个邻接顶点
     * @return 返回顶点v1的下一个邻接顶点
     */
    public int getNextNeighbor(int v1, int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 图的深度优先遍历
     */
    public void dfs(int i){
        System.out.print(getValueByIndex(i) + " -> ");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1){
            if (!isVisited[w]){
                dfs(w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 方法重载，深度遍历访问所有顶点
     */
    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumsOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(i);
            }
        }
        System.out.println();
    }

    public void bfs(int v){
        LinkedList<Integer> visitedList = new LinkedList<>();
        int u;// 队列头结点
        // 先访问此节点
        System.out.print(getValueByIndex(v) + " -> ");
        // 标记为已访问
        isVisited[v] = true;
        visitedList.addLast(v);
        while (!visitedList.isEmpty()){
            u = visitedList.removeFirst();
            int neighbor = getFirstNeighbor(u);
            while (neighbor != -1){
                if (!isVisited[neighbor]){
                    System.out.print(getValueByIndex(neighbor) + " -> ");
                    isVisited[neighbor] = true;
                    visitedList.addLast(neighbor);
                }
                neighbor = getNextNeighbor(u, neighbor);
            }
        }
    }

    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumsOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(i);
            }
        }
        System.out.println();
    }

}
