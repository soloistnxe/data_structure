package Sort;
//快速排序
/*
通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
* */
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {-9, 0, 0, 23, -567, 70,900,2,41,18,69};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr = simpleDateFormat.format(date1);
        System.out.println(datastr);
        quickSort(arr, 0, arr.length - 1);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr2 = simpleDateFormat2.format(date2);
        System.out.println(datastr2);
        //System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int arr[], int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        //pivot中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量，作为交换用
        //while循环的目的是让比pivot值小的放到左边，比pivot大的放到右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot的值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot的值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>r 说明pivot的左边全部小于等于pivot，右边全部大于等于pivot
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            /*这两个if是为了重复元素出现的情况，例如-9, 0, 0, 23, -567, 70，中值为 0 左指针为1时停下，右指针为4停下，随后交换，
              交换后右指针指向的值为0 和中值相等，右指针就不会移动了，造成死循环，所以需要右指针前移一个
            * */
            //如果交换完后，发现arr[l] == pivot
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完后，发现arr[r] == pivot
            if (arr[r] == pivot) {
                l++;
            }
        }
        //如果l==r，必须l++，r--，否则出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        //想左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
