package 알고리즘_기초.P1062_가르침;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static boolean[] visited = new boolean[26];
    static String[] words;
    static int selectedCount;
    static int max;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/알고리즘_기초/P1062_가르침/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;

        words = new String[N];
        selectedCount = 5;
        max = 0;

        for (int i = 0; i < N; i++) {
            String input = new StringTokenizer(br.readLine()).nextToken().replaceAll("[antic]","");

            words[i] = input;
        }

        if(K < 5) {
            System.out.println(0);
            return;
        } else if (K == 5) {
            System.out.println(countReadable());
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        for (int i = 0; i < 26; i++) {
            if(visited[i] == false){
                dfs(i);
            }
        }

        System.out.println(max);
    }

    public static void dfs(int index){
        // 1. 체크인
        visited[index] = true;
        selectedCount++;

        // 2. 목적지인가?
        if(selectedCount == K) {
            max = Math.max(countReadable(), max);
        }else{
            // 3. 갈 수 있는곳 순회
            for (int i = index + 1; i < 26; i++) {
                // 4. 갈 수 있는가?
                if(visited[i] == false){
                    // 5. 간다
                    dfs(i);
                }
            }
        }


        // 6. 체크아웃
        visited[index] = false;
        selectedCount--;
    }

    public static int countReadable(){
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            boolean isReadable = true;

            String word = words[i];

            for (int j = 0; j < word.length(); j++) {
                if( visited[word.charAt(j) - 'a'] == false ){
                    isReadable = false;
                    break;
                }
            }

            if(isReadable == true){
                cnt++;
            }

        }

        return cnt;
    }
}
