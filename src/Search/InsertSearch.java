package Search;
//插值查找，对于数组元素的值较为接近的数组查找效率高，对于一般情况，建议使用二分查找
import java.util.Arrays;
//差值查找算法
public class InsertSearch {
    static int count = 0;

    public static void main(String[] args) {
        int arr[] = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i+1;
        }
        System.out.println(Arrays.toString(arr));
        int index = insertSearch(arr, 0, arr.length - 1, 87);
        System.out.println(index);
        System.out.println("查找次数:"+count);
    }

    //编写插值查找算法

    /**
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return 如果找到返回对应的下标，没找到就返回-1
     */
    public static int insertSearch(int arr[], int left, int right, int findVal) {
       count++;
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1])
            return -1;
        //求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal)
            return insertSearch(arr, mid + 1, right, findVal);
        else if (findVal < midVal)
            return insertSearch(arr, left, mid - 1, findVal);
        else
            return mid;
    }
}
