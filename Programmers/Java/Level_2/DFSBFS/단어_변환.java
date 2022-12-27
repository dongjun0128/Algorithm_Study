package Level_2.DFSBFS;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class 단어_변환 {
    boolean[] visited;
    int answer = 0;

    public int solution(String begin, String target, String[] words) {
        answer = 999999;
        visited = new boolean[words.length];

        dfs(begin, target, words, 0);
        return answer == 999999 ? 0 : answer; // 변환 가능한 경우가 없으면 0, 있으면 그 횟수를 return
    }

    public void dfs(String begin, String target, String[] words, int cnt) {
        if (begin.equals(target)) {
            answer = Math.min(cnt, answer); // 변환 가능한 횟수중 최솟값만 answer에 저장
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (visited[i] == false && countDuplicateWords(begin, words[i]) == begin.length() - 1) { // 바꾸지 않은 단어 중 한 개의 알파벳만 바꾸는 경우
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }
    }


    public int countDuplicateWords(String standard, String str) {
        int cnt = 0;

        for (int i = 0; i < standard.length(); i++) {
            if (standard.charAt(i) == str.charAt(i)) cnt++;
        }

        return cnt;
    }

    @Test
    void 정답1() {
        Assert.assertEquals(4, solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        Assert.assertEquals(0, solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }
}
