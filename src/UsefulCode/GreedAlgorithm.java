package UsefulCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//贪心算法
public class GreedAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入map
        HashMap<String, HashSet<String>> boardcast = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入map
        boardcast.put("k1", hashSet1);
        boardcast.put("k2", hashSet2);
        boardcast.put("k3", hashSet3);
        boardcast.put("k4", hashSet4);
        boardcast.put("k5", hashSet5);

        HashSet<String> all = new HashSet<>();
        all.add("北京");
        all.add("上海");
        all.add("天津");
        all.add("广州");
        all.add("深圳");
        all.add("成都");
        all.add("杭州");
        all.add("大连");

        //创建Arrlist，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();
        //定义一个临时的集合，在遍历过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        String maxkey = null;
        while (all.size() != 0) {
            //每一次要maxkey置空
            maxkey = null;
            //遍历broadcasts,取出对应key
            for (String key : boardcast.keySet()) {
                //当前这个能够覆盖的地区
                tempSet.clear();
                HashSet<String> areas = boardcast.get(key);
                tempSet.addAll(areas);
                //求出tempSet和all集合的交集
                tempSet.retainAll(all);
                //如果当前这个集合包含的未覆盖地区的数量，比maxkey指向的集合地区还多
                if (tempSet.size() > 0 && (maxkey == null || tempSet.size() > boardcast.get(maxkey).size())) {
                    maxkey = key;
                }
            }
            //maxkey!=null,就应该
            if (maxkey != null) {
                selects.add(maxkey);
                //将maxkey从all中去掉
                all.removeAll(boardcast.get(maxkey));
            }
        }
        System.out.println("结果" + selects);

    }
}
