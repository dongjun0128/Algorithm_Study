package Level_3.해시;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class 베스트앨범 {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        Map<String, Integer> genersHashMap = new HashMap<>();


        // 1. 가장 많이 재생된 순서로 정렬
        for (int i = 0; i < genres.length; i++) {
            genersHashMap.put(genres[i], genersHashMap.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(genersHashMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        // 2. 가장 많이 재생된 종류 중 많이 재생된 노래 찾기

        ArrayList<Integer> answerList = new ArrayList<>();

        for (int j = entryList.size() - 1; j >= 0; j--) {
            Map<Integer, Integer> numberHashMap = new HashMap<>();

            // 가장 많이 재생된 종류만 numberHashMap에 추가
            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(entryList.get(j).getKey())) {
                    numberHashMap.put(i, plays[i]);
                }
            }

            // 정렬
            List<Map.Entry<Integer, Integer>> numberList = new LinkedList<>(numberHashMap.entrySet());
            numberList.sort(Map.Entry.comparingByValue());

            int index = numberList.size();

            if(index == 1){
                answerList.add(numberList.get(index-1).getKey());
            }
            else{
                if(numberList.get(index-1).getValue() == numberList.get(index-2).getValue()){
                    answerList.add(Math.min(numberList.get(index-1).getKey(),numberList.get(index-2).getKey()));
                    answerList.add(Math.max(numberList.get(index-1).getKey(),numberList.get(index-2).getKey()));
                }
                else {
                    answerList.add(numberList.get(index - 1).getKey());
                    answerList.add(numberList.get(index - 2).getKey());
                }
            }
        }

        answer = new int[answerList.size()];

        for(int i = 0 ; i<answerList.size();i++){
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    @Test
    void 정답() {
        Assert.assertEquals(new int[]{4, 1, 3, 0}, solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}));
    }
}
