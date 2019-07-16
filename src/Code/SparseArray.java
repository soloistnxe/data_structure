package Code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SparseArray {
    //创建一个原始的二维数组11*11
    //0:表示没有棋子，1表示黑子，2表示篮子
    public static void main(String[] args) {
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始的二维数组
        System.out.println("输出原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转稀疏数组
        //1.先遍历二维数组得到非0数据的个数
        int sum=0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        //2.创建稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //遍历二维数组，将非0的值存放到稀疏数组中
        int count =0;//用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:/sparseArray.txt");
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            for (int i = 0; i < sparseArr.length; i++) {
                for (int j = 0; j < sparseArr[i].length; j++) {
                    dataOutputStream.writeInt(sparseArr[i][j]);
                }
               // dataOutputStream.writeBytes("\r\n");
            }
            dataOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        //将稀疏数组恢复成原始的二维数组
        /*
        1.先读取稀疏数组的第一行，根据第一行的数据，创建原始数组的二维数组
        2.再读取稀疏数组的后几行数据
        * */
        List<Integer> sparr = new ArrayList<>();
        try{
            FileInputStream fileInputStream = new FileInputStream("D:/sparseArray.txt");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            int a;

            while ((a=dataInputStream.readInt())!=-1){
                sparr.add(a);
            }

        }catch (IOException  e){
            e.printStackTrace();
            System.out.println(sparr);
        }
        int [][] sparrseArr2 = new int[sparr.size()/3][3];
        int cot =0;
        for (int i = 0; i < sparr.size(); i++) {
            if(cot==3)
                cot=0;
            sparrseArr2[i/3][cot]=sparr.get(i);
            cot++;
        }
        int cheaaArr2[][] = new int[sparrseArr2[0][0]][sparrseArr2[0][1]];
        System.out.println(sparrseArr2[0][0]);
        //从稀疏数组的第2行开始遍历
        for (int i = 1; i < sparseArr.length; i++) {
            cheaaArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("恢复后的二维数组");
        for (int[] row : cheaaArr2) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
    
    
}
