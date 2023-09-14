package 깊이우선탐색_넓이우선탐색.P2606_바이러스;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/깊이우선탐색_넓이우선탐색/P2606_바이러스/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        parents = new int[N + 1];

        for (int i = 0; i <= N ; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        //System.out.println(Arrays.toString(parents));
        int answer = 0;

        for (int i = 2; i <= N; i++) {
            if(find(i) == find(1)) answer++;
        }

        System.out.println(answer);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        parents[rootB] = rootA;
    }

    public static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
