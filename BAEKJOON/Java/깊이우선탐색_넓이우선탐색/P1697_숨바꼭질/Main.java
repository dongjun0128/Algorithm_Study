package 깊이우선탐색_넓이우선탐색.P1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/깊이우선탐색_넓이우선탐색/P1697_숨바꼭질/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new int[100001];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(N);
        visited[N] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == K) {
                System.out.println(visited[now] - 1);
                break;
            }

            if (now * 2 <= 100000 && visited[now * 2] == 0) {
                visited[now * 2] = visited[now] + 1;
                queue.add(now * 2);
            }

            if (now + 1 <= 100000 && visited[now + 1] == 0) {
                visited[now + 1] = visited[now] + 1;
                queue.add(now + 1);
            }

            if (now - 1 >= 0 && visited[now - 1] == 0) {
                visited[now - 1] = visited[now] + 1;
                queue.add(now - 1);
            }

        }


    }
}
