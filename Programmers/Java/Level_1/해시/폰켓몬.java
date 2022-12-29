package Level_1.해시;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 폰켓몬 {
    public int solution(int[] nums) {
        int canGetNumbers = nums.length / 2;

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        if(set.size() > canGetNumbers){
            return canGetNumbers;
        }
        else {
            return set.size();
        }
    }

    @Test
    void 정답() {
        Assert.assertEquals(2, solution(new int[]{3, 1, 2, 3}));
        Assert.assertEquals(3, solution(new int[]{3, 3, 3, 2, 2, 4}));
        Assert.assertEquals(2, solution(new int[]{3, 3, 3, 2, 2, 2}));
    }

}
