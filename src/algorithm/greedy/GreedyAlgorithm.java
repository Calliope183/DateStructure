package algorithm.greedy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @description: 贪心算法最佳应用
 * @author: WuW
 * @create: 2022/2/25 11:23
 */
public class GreedyAlgorithm {

    @Test
    public void test(){
        HashMap<String, HashSet<String>> map = new HashMap<>();

        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");

        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        map.put("k1", set1);
        map.put("k2", set2);
        map.put("k3", set3);
        map.put("k4", set4);
        map.put("k5", set5);

        HashSet<String> allCities = new HashSet<>();
        allCities.add("北京");
        allCities.add("上海");
        allCities.add("天津");
        allCities.add("广州");
        allCities.add("深圳");
        allCities.add("成都");
        allCities.add("杭州");
        allCities.add("大连");

        ArrayList<String> selects = new ArrayList<>();
        HashSet<String> tempSet = new HashSet<>();
        String maxKey;
        while (allCities.size() != 0){
            maxKey = null;
            for (String key : map.keySet()) {
                tempSet.clear();
                HashSet<String> areas = map.get(key);
                tempSet.addAll(areas);
                // 求出tempSet与allCities的交集，并将交集赋值给tempSet
                tempSet.retainAll(allCities);
                // 当前集合中包含的未覆盖地区的数量比maxKey指向的集合数目多
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > map.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if (maxKey != null){
                selects.add(maxKey);
                // 将maxKey指向的集合从所有集合中去掉
                allCities.removeAll(map.get(maxKey));
            }
        }
        System.out.println("选择的集合是：" + selects);
    }
}
