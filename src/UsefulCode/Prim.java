package UsefulCode;

import java.util.Arrays;

public class Prim {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        //创建graph对象
        MGraph mGraph = new MGraph(verxs);
        //创建一个Mintree
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        minTree.show(mGraph);
        //测试prim算法
        minTree.prim(mGraph, 1);
    }
}

//创建最小生成树
class MinTree {
    //创建图的邻接矩阵

    /**
     * @param graph  图对象
     * @param verxs  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的方法
    public void show(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }
    //编写一个prim算法得到最小生成树

    /**
     * @param graph 图
     * @param v     从图的第几个顶点开始生成'A'->0.....
     */
    public void prim(MGraph graph, int v) {
        int[] visited = new int[graph.vertx]; //标记已经顶点是否被访问过
        //visited默认访问值都是0，表示没有访问过
        //把当前这个节点标记为已访问
        visited[v] = 1;
        int h1 = -1; //h1和h2记录两个顶点的下标
        int h2 = -1;
        int minweight = 10000;//将minweight 初始化成一个大数，后面在遍历过程中会被替换
        for (int k = 1; k < graph.vertx; k++) {//因为有graph。vertxs个顶点，prim算法后有vertxs-1条边
            //这个是确定每一次生成的子图 ，和哪个结点的距离最近
            for (int i = 0; i < graph.vertx; i++) { //i结点表示被访问过的结点
                for (int j = 0; j < graph.vertx; j++) { //j结点表示还没有访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minweight) {
                        //替换minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                        minweight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小的
            System.out.println("边" + graph.data[h1] + "," + graph.data[h2] + ">权值:" + minweight);
            //将当前这个找到的节点标记为访问过
            visited[h2] = 1;
            minweight = 10000;
        }
    }
}

class MGraph {
    int vertx; //表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，就是邻接矩阵

    public MGraph(int vertx) {
        this.vertx = vertx;
        data = new char[vertx];
        weight = new int[vertx][vertx];
    }
}