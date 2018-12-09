
import java.util.*;

class testTreeMap {
    public static void main(String[] args) {
        System.out.println("the main");
        byValue cmp = new byValue();
        Map<String, Integer> map = new TreeMap<String, Integer>();
        map.put("de",10);
        map.put("ab", 20);
        map.put("a",5);

        for (Map.Entry<String,Integer> pair: map.entrySet()) {
            System.out.println(pair.getKey()+":"+pair.getValue());
        }
    }
}

class byValue implements Comparator<Map.Entry<String,Integer>> {
    public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
        if (e1.getValue() < e2.getValue()){
            return 1;
        } else if (e1.getValue() == e2.getValue()) {
            return 0;
        } else {
            return -1;
        }
    }
}