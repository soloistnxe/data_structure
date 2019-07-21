package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

//冒泡排序O（n*n）
/*冒泡排序（Bubble Sorting）的基本思想是：通过对待
  排序序列从前向后（从下标较小的元素开始）,依次比较
  相邻元素的值，若发现逆序则交换，使值较大
  的元素逐渐从前移向后部，就象水底下的气泡一样逐渐
  向上冒。
  因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下
  来没有进行过交换，就说明序列有序，因此要在排序过程中设置
  一个标志flag判断元素是否进行过交换。从而减少不必要的比较。(这里说的优化，可以在冒泡排序写好后，在进行)

* */
public class BubbleSort {
    public static void main(String[] args) {
        //int arr[] = {3, 9, -1, 4, 20};
        //第一趟排序就是将最大的数排在最后

        //测试一下冒泡排序的速度，给80000个数据
        //创建一个80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr = simpleDateFormat.format(date1);
        System.out.println(datastr);
        BubbleSort(arr);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr2 = simpleDateFormat2.format(date2);
        System.out.println(datastr2);
    }

    //将冒泡排序封装成一个方法
    public static int[] BubbleSort(int[] arr) {
        int temp = 0; //临时变量，用来交换时用
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int j = 0; j < arr.length - 1; j++) {
            //如果前面的数比后面的大就交换
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = true;
                }
            }
            if (flag == false) {//在一趟排序中，一次交换都没有发生
                break;
            } else {
                flag = false; //重置flag，进行下次判断
            }

        }
        return arr;
    }

}
