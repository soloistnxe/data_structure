package UsefulCode;

//kmp算法解决字符串匹配
public class Kmp {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        int index = kmpSearch(str1, str2, next);
        System.out.println(index);
    }

    /**
     * @param str1 原字符串
     * @param str2 子串
     * @param next 子串的部分匹配表
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理str1.charAt(i)！=str2.charAt(j)时,调整j的大小
            //kmp算法的核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到子串的部分匹配值
    public static int[] kmpNext(String dest) {
        //创建一个next数组，保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1，部分匹配值为0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i)！=dest.charAt(j)，需要从next[j-1]获取新的j
            //直到发现有下面这个条件成立时退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //当这个条件满足，部分匹配值就+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
