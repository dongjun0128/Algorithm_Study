package Level_3.단어변환;

public class Main {

    static int answer;
    static boolean[] visited;

    public static int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        visited = new boolean[words.length];

        for (int i = 0; i < words.length; i++) {
            if(check(begin, words[i]) == 1) {
                dfs(words[i], target, words, 0, i);
            }
        }


        return answer;
    }

    public static void dfs(String begin, String target, String[] words, int cnt, int index) {
        // 1. 체크 인
        visited[index] = true;
        cnt++;

        // 2. 목적지인가?
        if(begin.equals(target)) {
            answer = Math.min(answer,cnt);
        } else {
            // 3. 연결된 곳 순회
            for (int i = 0; i < words.length; i++) {
                // 4. 갈 수 있는가?
                if(check(begin, words[i]) == 1 && visited[i] == false) {
                    // 5, 간다.
                    dfs(words[i], target, words, cnt, i);
                }
            }
        }

        // 6. 체크 아웃
        cnt--;
        visited[index] = false;
    }

    public static int check(String str1, String str2) {
        int cnt = 0;

        for(int i = 0 ; i< str1.length() ; i++) {
            if(str1.charAt(i) != str2.charAt(i)) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));

    }
}
