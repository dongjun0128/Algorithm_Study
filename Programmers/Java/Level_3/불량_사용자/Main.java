package Level_3.불량_사용자;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    static HashMap<String, Integer> map = new HashMap<>();

    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        int[] factorial = new int[user_id.length + 1];
        factorial[0] = 1;

        for (int i = 1; i < factorial.length; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        for (int i = 0; i < banned_id.length; i++) {
            map.put(banned_id[i], map.getOrDefault(banned_id[i], 0) + 1);
        }

        for (String ban : map.keySet()) {
            List<boolean[]> wordList = new ArrayList<>();
            for (int i = 0; i < user_id.length; i++) {
                String user = user_id[i];

                boolean[] word = new boolean[user.length()];
                wordList.add(word);
            }

            for (int i = 0; i < ban.length(); i++) {
                for (int j = 0; j < user_id.length; j++) {
                    if (wordList.get(j).length == ban.length()) {
                        if (ban.charAt(i) != '*') {
                            if (ban.charAt(i) == user_id[j].charAt(i)) {
                                wordList.get(j)[i] = true;
                            }
                        }
                    }
                }
            }


            for (int j = 0; j < user_id.length; j++) {
                boolean flag = true;
                if (wordList.get(j).length == ban.length()) {
                    for (int k = 0; k < ban.length(); k++) {
                        if (ban.charAt(k) != '*') {
                            if(wordList.get(j)[k] == true) {

                            }
                            else {
                                flag = false;
                            }
                        }

                        if (ban.charAt(k) == '*') {
                            if(wordList.get(j)[k] == false) {

                            }
                            else {
                                flag = false;
                            }
                        }
                    }
                } else {
                    flag = false;
                }

                if (flag == true) {
                    System.out.println("ban : " + ban);
                    System.out.println("user : " + user_id[j]);
                }
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
        //System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
    }
}
