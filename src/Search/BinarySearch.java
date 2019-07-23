package Search;

import java.util.ArrayList;
import java.util.List;

//注意二分查找，数组必须是有序的
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        /*int index = binarySearch(arr, 0, arr.length - 1, 88);
        System.out.println(index);*/
        List resindex = binarySearch2(arr, 0, arr.length, 1000);
        System.out.println(resindex);
    }
    //二分查找

    /**
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findval 要查找的数
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findval) {
        //当left>right时没有找递归结束
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        int midval = arr[mid];
        if (findval > midval) { //向右递归
            return binarySearch(arr, mid + 1, right, findval);
        } else if (findval < midval) {//向左递归
            return binarySearch(arr, left, mid - 1, findval);
        } else {
            return mid;
        }
    }

    //但一个数组中有多个带查找的值时，全部找到
    /*
    思路1.在找到mid值，不要马上返回，向mid索引值的左边扫面，将所有满足的下标加入到一个集合
    2.向mid索引值的右边扫描，将所有满足的下标加到集合
    3.返回arrlist
    * */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findval) {
        //当left>right时没有找递归结束
        if (left > right)
            return new ArrayList<>();
        int mid = (left + right) / 2;
        int midval = arr[mid];
        if (findval > midval) { //向右递归
            return binarySearch2(arr, mid + 1, right, findval);
        } else if (findval < midval) {//向左递归
            return binarySearch2(arr, left, mid - 1, findval);
        } else {
            ArrayList<Integer> resindex = new ArrayList<>();
            //向mid索引值的左边扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findval) {//退出
                    break;
                }
                //否则就把temp放入到集合中
                resindex.add(temp);
                temp--;
            }
            resindex.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findval) {//退出
                    break;
                }
                //否则就把temp放入到集合中
                resindex.add(temp);
                temp++;
            }
            return resindex;
        }
    }
}
