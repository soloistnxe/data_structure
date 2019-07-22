package Sort;
//基数排序
/*第1轮排序  [按照个位排序]:
说明： 事先准备10个数组(10个桶)， 0-9 分别对应 位数的 0-9
(1) 将 各个数，按照个位大小 放入到 对应的 各个数组中
(2)  然后从 0-9 个数组/桶，依次，按照加入元素的先后顺序取出

第2轮排序  [按照十位排序]

(1) 将 各个数，按照十位大小 放入到 对应的 各个数组中
(2)  然后从 0-9 个数组/桶，依次，按照加入元素的先后顺序取出

第3轮排序  [按照百位排序]

(1) 将 各个数，按照百位大小 放入到 对应的 各个数组中
(2)  然后从 0-9 个数组/桶，依次，按照加入元素的先后顺序取出

基数排序会耗费很多内存空间，可能会有内存不足的错误
* */
import java.text.SimpleDateFormat;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
        /*int arr[] = {53, 3456, 542, 748, 14, 214};
        radixSort(arr);*/
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr = simpleDateFormat.format(date1);
        System.out.println(datastr);
        radixSort(arr);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr2 = simpleDateFormat2.format(date2);
        System.out.println(datastr2);
    }

    //基数排序方法
    public static void radixSort(int[] arr) {
        //1.得到数组中最大数的位数
        int max = arr[0];//假设第一个数最大
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        //得到最大数的位数
        int maxLength = (max + "").length();
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，定义一个一维数组来记录每个桶的每次放入的数据个数
        int[] bucketElecount = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素的对应位进行排序
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElecount[digitOfElement]] = arr[j];
                bucketElecount[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入到原数组
            for (int k = 0; k < bucketElecount.length; k++) {
                //如果桶中有数据，才放入原数组
                if (bucketElecount[k] != 0) {
                    //循环该桶即第k个桶（即第k个一维数组），放入
                    for (int l = 0; l < bucketElecount[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将桶清0
                bucketElecount[k] = 0;
            }
            //System.out.printf("第%d轮的结果"+Arrays.toString(arr)+"\n",i+1);
        }

        /*//第一轮排序针对每个元素的各位进行排序处理

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含十个一维数组，
        //2.为了防止在放入数的时候，数据溢出，则每一个一维数组，大小定位arr.length
        //3.基数排序是空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，定义一个一维数组来记录每个桶的每次放入的数据个数
        int[] bucketElecount = new int[10];
        for(int j=0;j<arr.length;j++){
            //取出每个元素的个位的值
            int digitOfElement = arr[j]%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElecount[digitOfElement]] = arr[j];
            bucketElecount[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
        int index =0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for(int k=0;k<bucketElecount.length;k++){
            //如果桶中有数据，才放入原数组
            if(bucketElecount[k]!=0){
                //循环该桶即第k个桶（即第k个一维数组），放入
                for(int l=0;l<bucketElecount[k];l++){
                    //取出元素放入到arr
                    arr[index++]= bucket[k][l];
                }
            }
            //第一轮处理后，需要将桶清0
            bucketElecount[k]=0;
        }
        System.out.println(Arrays.toString(arr));

        //第二轮，取10位数字
        for(int j=0;j<arr.length;j++){
            //取出每个元素的个位的值
            int digitOfElement = arr[j]/ 10 %10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElecount[digitOfElement]] = arr[j];
            bucketElecount[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
         index =0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for(int k=0;k<bucketElecount.length;k++){
            //如果桶中有数据，才放入原数组
            if(bucketElecount[k]!=0){
                //循环该桶即第k个桶（即第k个一维数组），放入
                for(int l=0;l<bucketElecount[k];l++){
                    //取出元素放入到arr
                    arr[index++]= bucket[k][l];
                }
            }
            //第一轮处理后，需要将桶清0
            bucketElecount[k]=0;
        }
        System.out.println(Arrays.toString(arr));


        for(int j=0;j<arr.length;j++){
            //取出每个元素的个位的值
            int digitOfElement = arr[j]/ 100 %10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElecount[digitOfElement]] = arr[j];
            bucketElecount[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
        index =0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for(int k=0;k<bucketElecount.length;k++){
            //如果桶中有数据，才放入原数组
            if(bucketElecount[k]!=0){
                //循环该桶即第k个桶（即第k个一维数组），放入
                for(int l=0;l<bucketElecount[k];l++){
                    //取出元素放入到arr
                    arr[index++]= bucket[k][l];
                }
            }
            //第一轮处理后，需要将桶清0
            bucketElecount[k]=0;
        }
        System.out.println(Arrays.toString(arr));*/
    }


}
