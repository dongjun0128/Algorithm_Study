package 그래프.P3830_교수님은_기다리지_않는다;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parents;
    static int[] weights;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P3830_교수님은_기다리지_않는다/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (true) {

            parents = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parents[i] = i;
            }

            weights = new int[N + 1];

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());

                char command = st.nextToken().charAt(0);

                if (command == '!') {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());

                    union(a, b, w);
                } else {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());

                    if (find(a) != find(b)) {
                        System.out.println("UNKNOWN");
                    } else {
                        System.out.println(weights[b] - weights[a]);
                    }
                }
            }


            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }
        }

    }

    public static void union(int a, int b, int w) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;

        parents[rootB] = rootA;
        weights[rootB] = w + (weights[a] - weights[b]);
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }

        int prv = find(parents[a]);
        weights[a] += weights[parents[a]];
        return parents[a] = prv;
    }
}