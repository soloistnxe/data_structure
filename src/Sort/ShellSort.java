package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

//希尔排序（插入排序的改良版）(又称缩小增量排序)
/*希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
  随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 * */
public class ShellSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);*/
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr = simpleDateFormat.format(date1);
        System.out.println(datastr);
        shellSort2(arr);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr2 = simpleDateFormat2.format(date2);
        System.out.println(datastr2);
    }

    //希尔排序，对有序序列在插入时采用交换法，交换次数过多，反而会使算法变慢
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共gap组，每组有个元素）,步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，就交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.printf("希尔排序第%d轮" + Arrays.toString(arr)+"\n", ++count);
        }
        /*//希尔排序的第一轮
        //因为第一轮排序是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，每组有2个元素）,步长5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，就交换
                if (arr[j] > arr[j + 5]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        System.out.println("希尔排序第1轮="+ Arrays.toString(arr));

    //希尔排序的第2轮
    //因为第一轮排序是将10个数据分成了5/2=2组
        for (int i = 2; i < arr.length; i++) {
        //遍历各组中所有的元素（共5组，每组有2个元素）,步长5
        for (int j = i - 2; j >= 0; j -= 2) {
            //如果当前元素大于加上步长后的那个元素，就交换
            if (arr[j] > arr[j + 2]) {
                int temp = arr[j];
                arr[j] = arr[j + 2];
                arr[j + 2] = temp;
            }
        }
    }

        System.out.println("希尔排序第2轮="+ Arrays.toString(arr));

        //希尔排序的第3轮
        //因为第一轮排序是将10个数据分成了2/2=1组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，每组有2个元素）,步长5
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，就交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("希尔排序第3轮="+ Arrays.toString(arr));*/
    }

    //对交换式希尔排序进行优化->移位法
    public static void shellSort2(int[] arr) {
        //增量gap，并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[i - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到了插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
