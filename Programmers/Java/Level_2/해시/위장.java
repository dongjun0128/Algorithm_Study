package Level_2.해시;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Set;

public class 위장 {
    public int solution(String[][] clothes) {
        int answer = 1;
        int totalClothes = clothes.length;
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (String[] clothe : clothes) {
            hashMap.put(clothe[1], hashMap.getOrDefault(clothe[1], 0) + 1); //key가 있으면 원래 가지고 있는 value +1 key가 없으면 0 + 1 저장
        }

        Set keys = hashMap.keySet();

        for(Object key : keys){
            answer *= hashMap.get(key) + 1; // None 값도 추가하여 모든 경우의 수 계산
        }

        return answer - 1; // 아무것도 입지 않은 상태는 제외
    }

    @Test
    void 정답() {
        Assert.assertEquals(5, solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
        Assert.assertEquals(3, solution(new String[][]{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}));
    }
}
