package Sort;
//归并排序，分治思想
import java.text.SimpleDateFormat;
import java.util.Date;

public class MergeSort {
    public static void main(String[] args) {
        /*int[] arr= {8,4,5,7,1,3,6,2};
        int temp[] = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);*/
        int[] arr = new int[800000];
        int[] temp = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr = simpleDateFormat.format(date1);
        System.out.println(datastr);
        mergeSort(arr, 0, arr.length - 1, temp);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr2 = simpleDateFormat2.format(date2);
        System.out.println(datastr2);
        //System.out.println(Arrays.toString(arr));
    }

    //分+合的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //到合并
            merge(arr, left, right, mid, temp);
        }
    }
    //合并的方法

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序 序列的初始索引
     * @param right 右边索引
     * @param mid   中间索引
     * @param temp
     */
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        int i = left; //初始化i，左边有序序列的初始索引
        int j = mid + 1; //初始化j，右边有序序列的初始索引
        int t = 0; //指向temp数组的当前索引
        //先把左右两边（有序）的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边的有序序列的当前元素小于等于右边的有序序列的当前元素
            //就将左边的当前元素拷贝到temp
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {//反之，将右边的有序序列的当前元素，填充到temp
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) {//左边的有序序列还有剩余元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {//左边的有序序列还有剩余元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //将temp数组的元素拷贝到arr
        //并不是每次都拷贝所有
        t = 0;
        int templeft = left;
        while (templeft <= right) {//第一次合并，templeft = 0；right = 1 第二次合并 templeft =2 right = 3 最后一次 templeft=0 right =7
            arr[templeft] = temp[t];
            t += 1;
            templeft += 1;
        }
    }
}
