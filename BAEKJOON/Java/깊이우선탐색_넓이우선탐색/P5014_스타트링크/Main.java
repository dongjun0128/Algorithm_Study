package 깊이우선탐색_넓이우선탐색.P5014_스타트링크;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int F, S, G, U, D;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/깊이우선탐색_넓이우선탐색/스타트링크/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        visited = new int[F + 1];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(S);
        visited[S] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == G) {
                System.out.println(visited[now] - 1);
            }

            if (now + U <= F && visited[now + U] == 0) {
                visited[now + U] = visited[now] + 1;
                queue.add(now + U);
            }

            if (now - D > 0 && visited[now - D] == 0) {
                visited[now - D] = visited[now] + 1;
                queue.add(now - D);
            }

        }


        if(visited[G] == 0)
            System.out.println("use the stairs");
    }

}
