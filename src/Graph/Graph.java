package Graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numsofedges;//边的数目
    //定义一个数组，记录某个节点是否被访问过
    private boolean[] isVisted;
    public Graph(int n){
        //初始化矩阵和ArrayList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numsofedges = 0;
        isVisted = new boolean[n];
    }
    //得到第一个邻接节点的下标
    //如果存在就返回对应的下标，否则返回-1
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }
    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for(int j = v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }
    //深度优先遍历
    public void dfs(boolean[] isVisted,int i){
        //首先访问该节点
        System.out.print(getValueByIndex(i)+"->");
        //将该节点设置为已经访问过
        isVisted[i] = true;
        //查找i的第一个邻接点
        int w =getFirstNeighbor(i);
        while (w!=-1){//说明右邻接节点
            if(!isVisted[w]){
                dfs(isVisted,w);
            }
            //如果w节点已经被访问过
            w= getNextNeighbor(i,w);
        }
    }
    //对dfs进行重载,遍历所有的节点并进行dfs
    public void dfs(){
        //遍历所有的节点，进行dfs
        for (int i = 0; i < getNumsofVertex(); i++) {
            if(!isVisted[i]){
                dfs(isVisted,i);
            }
        }
    }
    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisted,int i){
        int u;//队列的头结点对应的下标
        int w;//邻接节点的下标
        //队列，节点访问的顺序
        LinkedList linkedList = new LinkedList();
        //访问节点,输出节点的信息
        System.out.print(getValueByIndex(i)+"->");
        //标记为乙访问
        isVisted[i] = true;
        //将节点加入队列
        linkedList.addLast(i);
        while (! linkedList.isEmpty()){
            //取出队列的头节点下标
            u= (Integer) linkedList.removeFirst();
            //得到第一个邻接点的下标
            w = getFirstNeighbor(u);
            while (w!=-1){//找到
                //是否访问过
                if(!isVisted[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    //标记已经访问
                    isVisted[w]= true;
                    //入队列
                    linkedList.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接点
                w=getNextNeighbor(u,w);//体现出广度优先
            }

        }
    }
    //遍历所有节点，都进行广度优先搜索
    public void bfs(){
        for (int i = 0; i < getNumsofVertex(); i++) {
            if(!isVisted[i]){
                bfs(isVisted,i);
            }
        }
    }
    //图中常用的方法
    //返回节点的个数
    public int getNumsofVertex(){
        return vertexList.size();
    }
    public int getNumsofedges(){
        return numsofedges;
    }
    //返回节点i对应的数据\
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }
    //插入节点
    public void InsertVertex (String vertex){
        vertexList.add(vertex);
    }
    //添加边

    /**
     *
     * @param v1 表示点的下标
     * @param v2 表示第二个定点对应的下标
     * @param weight 两点之间的距离
     */
    public void InsertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numsofedges++;
    }
    public static void main(String[] args) {
        //测试图是否创建
        int n =8;
        String VertexValue[]={"1","2","3","4","5","6","7","8"};
        Graph graph = new Graph(n);
        //循环添加顶点
        for(String vertex: VertexValue){
            graph.InsertVertex(vertex);
        }
        System.out.println();
        //添加边
        graph.InsertEdge(0, 1, 1);
        graph.InsertEdge(0, 2, 1);
        graph.InsertEdge(1, 3, 1);
        graph.InsertEdge(1, 4, 1);
        graph.InsertEdge(3, 7, 1);
        graph.InsertEdge(4, 7, 1);
        graph.InsertEdge(2, 5, 1);
        graph.InsertEdge(2, 6, 1);
        graph.InsertEdge(5, 6, 1);
        graph.showGraph();
        //测试dfs
       // System.out.println("深度优先");
        graph.dfs();
        //System.out.println("广度优先");
        //graph.bfs();
    }
}
