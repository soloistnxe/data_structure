package UsefulCode;

//暴力匹配法解决字符串匹配问题
public class VoidlenMath {
    public static void main(String[] args) {
        String str1 = "你的，啊啊 你好，哦哦哦，啦啦啦啦";
        String str2 = "你好";
        int index = violence(str1, str2);
        System.out.println(index);
    }

    //暴力匹配算法实现
    public static int violence(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1len = s1.length;
        int s2len = s2.length;
        int i = 0;//i索引指向s1
        int j = 0;//j索引指向s2
        while (i < s1len && j < s2len) {//保证匹配时不越界
            if (s1[i] == s2[j]) {
                i++;
                j++;

            } else { //没有匹配成功
                i = i - (j - 1);
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2len) {
            return i - j;
        } else
            return -1;
    }
}
