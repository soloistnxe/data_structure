package Sort;
//插入排序
/*插入排序（Insertion Sorting）的基本思想是：把n个待排序的元素看成为一个有序表和一个无序表，
  开始时有序表中只包含一个元素，无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，
  把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
 * */
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        /*int[] arr = {101, 34, 119, 1,20,35};
        insertSort(arr);*/
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr = simpleDateFormat.format(date1);
        System.out.println(datastr);
        insertSort(arr);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr2 = simpleDateFormat2.format(date2);
        System.out.println(datastr2);
        //System.out.println(Arrays.toString(arr));
    }

    //插入排序
    public static void insertSort(int[] arr) {
        //第一轮
        //定义一个待插入的数
        int insertval = 0;
        int index;
        for (int i = 1; i < arr.length; i++) {
            insertval = arr[i];
            index = i - 1;//即arr[1]的前面这个数的下标
            //给 insertval找到插入的位置
            //insertval <arr[index] 待插入的数还没有找到插入位置
            //就需要将arr[index] 后移
            while (index >= 0 && insertval < arr[index]) {//保证在给insertval找插入位置时不越界
                arr[index + 1] = arr[index];
                index--;
            }
            //当退出while循环时，说明插入的位置找到，index+1
            arr[index + 1] = insertval;
        }
    }
}
