package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//选择排序
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
