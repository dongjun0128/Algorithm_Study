package Level_3.보석쇼핑;

import java.util.HashSet;

public class Main {

    static HashSet<String> set = new HashSet<>();

    public static int[] solution(String[] gems) {
        int[] answer = new int[2];

        for (int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }

        int left = 0;
        int right = 0;
        HashSet<String> bag = new HashSet<>();

        while (true) {
            bag.add(gems[right++]);
            if (bag.size() == set.size()) {
                break;
            }
        }



        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}));
    }
}
