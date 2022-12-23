package Level_2.DFSBFS;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class 타겟_넘버 {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0);
    }

    int dfs(int[] numbers, int target, int depth) {
        int answer = 0;

        if (depth == numbers.length) {
            if (Arrays.stream(numbers).sum() == target) {
                return 1;
            } else {
                return 0;
            }
        } else {
            answer += dfs(numbers, target, depth + 1);
            numbers[depth] *= -1;
            answer += dfs(numbers, target, depth + 1);
            return answer;
        }
    }

    @Test
    public void 정답() {
        Assert.assertEquals(5, solution(new int[]{1, 1, 1, 1, 1}, 3));
        Assert.assertEquals(2, solution(new int[]{4, 1, 2, 1}, 4));
    }
}
