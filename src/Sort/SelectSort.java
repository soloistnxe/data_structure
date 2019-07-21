package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

//选择排序
/*选择排序（select sorting）也是一种简单的排序方法。它的基本思想是：
 第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，
 第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…,
 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列。
 * */
public class SelectSort {
    public static void main(String[] args) {
        /*int[] arr = {101, 34, 119, 1, -1, 90, 123};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));*/
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr = simpleDateFormat.format(date1);
        System.out.println(datastr);
        selectSort(arr);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr2 = simpleDateFormat2.format(date2);
        System.out.println(datastr2);
    }

    //选择排序
    public static void selectSort(int[] arr) {
        int index;
        int min;
        //时间复杂度O（n^2）
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { //说明假定的最小值并不是最小
                    min = arr[j];//重置min
                    index = j;//重置index
                }
            }
            //将最小值放在arr
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = min;
            }
        }
    }
}
