package UsefulCode;

//动态规划解决背包问题
public class Knapsack {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4; //背包的容量
        int n = val.length; //物品的个数
        //创建二位数组，表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品的情况，定义一个二维数组
        int path[][] = new int[n + 1][m + 1];
        //初始化第一行和第一列,这里可以不去处理，因为默认为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        for (int i = 1; i < v.length; i++) { //不处理第一行,i是从1开始
            for (int j = 1; j < v[0].length; j++) {//不处理第一列,j是从1开始
                //公式
                if (w[i - 1] > j) { //因为程序中i是从1开始，因此w[i] val[i] 都要调整为w[i-1],val[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放的路径，不能直接用max函数
                    if (v[i - 1][j] > val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }

                }
            }
        }
        //输出一下
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        //输出背包问题的结果
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) { //从path最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个物品放入背包\n", i);
                j -= w[i - 1]; // 调整背包容量
            }
            i--;
        }


    }
}
