package Sort;

import java.util.Arrays;

//堆排序
/*将待排序序列构造成一个大顶堆
此时，整个序列的最大值就是堆顶的根节点。
将其与末尾元素进行交换，此时末尾就为最大值。
然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
* */
public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9, 3, 56, 12, 67, 45};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int arr[]) {
        int temp = 0;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    //将一个数组（二叉树）,调整成一个大顶堆

    /**
     * 功能：完成将以i对应的非叶子节点的树调整为大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整,length是在逐渐减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i]; //先取出当前元素的值，保存在临时变量
        //开始调整
        //1.k = i*2+1 k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { //说明左子节点的值小于右子节点的值
                k++; //k指向右子节点
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//！！！i指向k，继续循环比较
            } else {
                break;
            }
        }
        //当for循环结束后，已经将i为父节点的树的最大值，放在了最顶端（局部）
        arr[i] = temp; //将temp放到调整后的位置
    }
}
