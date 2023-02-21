package 유니온파인드.P1976_여행_가자;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/유니온파인드/P1976_여행_가자/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int connected = Integer.parseInt(st.nextToken());

                if (connected == 1) {
                    union(Math.min(n, i), Math.max(n, i));
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        int start = find(Integer.parseInt(st.nextToken()));

        for (int i = 1; i < M; i++) {
            int now = Integer.parseInt(st.nextToken());

            // 맨 처음 출발 도시와 연결되어있지 않은 도시가 있으면
            // 여행 계획이 불가능한 것임.
            if (start != find(now)) {
                System.out.println("NO");
                return;
            }
        }


        System.out.println("YES");
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        parents[rootA] = rootB;
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }
}
