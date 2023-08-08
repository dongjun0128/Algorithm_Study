package 슬라이딩_윈도우.P15961_회전_초밥;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int d;
    static int k;
    static int c;
    static int[] sushiTable;
    static HashMap<Integer, Integer> sushi = new HashMap<>(); // 초밥 종류
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/슬라이딩_윈도우/P15961_회전_초밥/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushiTable = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            sushiTable[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;

        while (end < k) {
            sushi.put(sushiTable[end], sushi.getOrDefault(sushiTable[end], 0) + 1);
            end++;
        }

        sushi.put(c, sushi.getOrDefault(c, 0) + 1);
        answer = sushi.size();
        if (sushi.get(c) == 1) {
            sushi.remove(c);
        } else {
            sushi.put(c, sushi.getOrDefault(c, 0) - 1);
        }

        while (start < N) {
            sushi.put(sushiTable[end], sushi.getOrDefault(sushiTable[end], 0) + 1);
            end = (end + 1) % N;

            if (sushi.get(sushiTable[start]) == 1) {
                sushi.remove(sushiTable[start]);
            } else {
                sushi.put(sushiTable[start], sushi.getOrDefault(sushiTable[start], 0) - 1);
            }

            sushi.put(c, sushi.getOrDefault(c, 0) + 1);
            answer = Math.max(answer, sushi.size());
            start = start + 1;

            if (sushi.get(c) == 1) {
                sushi.remove(c);
            } else {
                sushi.put(c, sushi.getOrDefault(c, 0) - 1);
            }

        }

        System.out.println(answer);
    }
}
