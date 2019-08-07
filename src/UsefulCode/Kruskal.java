package UsefulCode;

import java.util.Arrays;

//克鲁斯卡尔算法
public class Kruskal {
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点集合
    private int[][] matrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE; //INF表示两个顶点不能连通

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        Kruskal kruskal = new Kruskal(vertexs, matrix);
        kruskal.print();
        Edata[] edges = kruskal.getEdges();
        kruskal.sortEdges(edges);
        System.out.println(Arrays.toString(edges));
        kruskal.kruskal();
    }

    //构造器
    public Kruskal(char[] vertexs, int[][] matrix) {
        int vlen = vertexs.length;
        //初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int i1 = 0; i1 < vlen; i1++) {
                this.matrix[i][i1] = matrix[i][i1];
            }
        }
        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void print() {
        System.out.println("邻接矩阵为");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void kruskal() {
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树" 中的每个顶点在最小生成树中的终点
        //创建结果数组, 保存最后的最小生成树
        Edata[] rets = new Edata[edgeNum];
        //获取图中 所有的边的集合 ， 一共有12边
        Edata[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + " 共" + edges.length); //12

        //按照边的权值大小进行排序(从小到大)
        sortEdges(edges);
        //遍历edges 数组，将边添加到最小生成树中时，判断是准备加入的边否形成了回路，如果没有，就加入 rets, 否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点(起点)
            int p1 = getPosition(edges[i].start); //p1=4
            //获取到第i条边的第2个顶点
            int p2 = getPosition(edges[i].end); //p2 = 5

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1); //m = 4
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2); // n = 5
            //是否构成回路
            if (m != n) { //没有构成回路
                ends[m] = n; // 设置m 在"已有最小生成树"中的终点 <E,F> [0,0,0,0,5,0,0,0,0,0,0,0]
                rets[index++] = edges[i]; //有一条边加入到rets数组
            }
        }
        //<E,F> <C,D> <D,E> <B,F> <E,G> <A,B>。
        //统计并打印 "最小生成树", 输出  rets
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }
    //对边进行排序处理

    /**
     * 对边进行排序
     *
     * @param edges
     * @param
     */
    private void sortEdges(Edata[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) { //交换
                    Edata tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * @param ch 传入的顶点值
     * @return 返回这个顶点对应的下标，如果找不到返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，放到Edata数组中，后面需要遍历该数组
     * 是通过邻接矩阵来获取
     * EData[] 形式 [['A','B', 12], ['B','F',7], .....]
     *
     * @return
     */
    private Edata[] getEdges() {
        int index = 0;
        Edata[] edges = new Edata[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Edata(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能: 获取下标为i的顶点的终点(), 用于后面判断两个顶点的终点是否相同
     *
     * @param ends 数组就是记录了各个顶点对应的终点是哪个,ends 数组是在遍历过程中，逐步形成
     * @param i    表示传入的顶点对应的下标
     * @return 返回的就是 下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

//创建一个类，它的对象实例表示一条边
class Edata {
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    public Edata(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edata{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}