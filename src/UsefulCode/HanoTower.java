package UsefulCode;

//分治法解决汉诺塔问题
public class HanoTower {
    public static void main(String[] args) {

        HanoiTower(21, 'A', 'B', 'C');
    }

    public static void HanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {//如果只有一个盘
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //如果n>=2 总可以看作是两个盘1，最下边的一个盘 2.上面的所有盘
            //1.先把最上面的所有盘A->B，移动过程会使用到c
            HanoiTower(num - 1, a, c, b);
            //2.把最下面的盘从A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.把B塔所有盘移动到C,移动过程使用到A
            HanoiTower(num - 1, b, a, c);

        }
    }
}
