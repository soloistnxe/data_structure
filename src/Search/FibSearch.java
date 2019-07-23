package Search;

import java.util.Arrays;

public class FibSearch {
    public static int maxsize = 20;

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr,1234));
    }

    //因为后面mid=left+F（k-1）-1需要使用到斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxsize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxsize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 编写斐波那契查找算法
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int f[] = fib();//获取到斐波那契数列
        //获取到斐波那契数组的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]值可能大于数组的长度，因此使用Arrays构造一个新的数组，并指向a[]
        //不足的部分会使用0填充
        int []temp = Arrays.copyOf(a,f[k]);
        //实际上需要使用a数组最后的数填充temp
        for(int i =high+1;i<temp.length;i++){
            temp[i]=a[high];
        }
        //使用while循环来处理，找到我们的key
        while(low<=high){//只要满足这个条件，就可以找
            mid = low+f[k-1]-1;
            if(key<temp[mid]){ //向左查找
                high = mid -1;
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            }else if(key>temp[mid]){//向右查找
                low=mid+1;
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                k-=2;
            }else { //找到
                //需要确定，返回的是哪个下标
                if(mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
