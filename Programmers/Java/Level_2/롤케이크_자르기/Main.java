package Level_2.롤케이크_자르기;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static int solution(int[] topping) {
        int answer = 0;

        HashMap<Integer, Integer> set1 = new HashMap<>();
        HashMap<Integer, Integer> set2 = new HashMap<>();

        for (int i = 0; i < topping.length ; i++) {
            set2.put(topping[i], set2.getOrDefault(topping[i], 0) + 1);
        }

        for (int i = 0; i < topping.length; i++) {
            set1.put(topping[i], set1.getOrDefault(topping[i], 0) + 1);
            set2.put(topping[i], set2.get(topping[i]) - 1);
            if(set2.get(topping[i]) == 0) set2.remove(topping[i]);

            if (set1.size() == set2.size()) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 2, 1, 3, 1, 4, 1, 2}));
    }
}
