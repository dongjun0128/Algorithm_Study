package 그래프.P1717_집합의_표현;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P1717_집합의_표현/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) { // a와 b 유니온
                union(a,b);
            } else { // a와 b가 같은 집합인지?
                int rootA = find(a);
                int rootB = find(b);

                if(rootA == rootB) System.out.println("YES");
                else System.out.println("NO");
            }
        }

    }

    static void union(int a, int b) {
        int minValue = Math.min(a, b);
        int maxValue = Math.max(a, b);

        int rootMin = find(minValue);
        int rootMax = find(maxValue);

        parents[rootMax] = rootMin;
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }
}
