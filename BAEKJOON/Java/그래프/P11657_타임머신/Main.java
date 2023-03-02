package 그래프.P11657_타임머신;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Node> graph;
    static final int INF = Integer.MAX_VALUE;

    public static boolean BellmanFord(int start){
        long[] dist = new long[N + 1];
        Arrays.fill(dist,INF);
        dist[start] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Node node = graph.get(j);

                if(dist[node.start] != INF && dist[node.end] > dist[node.start] + node.cost){
                    dist[node.end] = dist[node.start] + node.cost;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            Node node = graph.get(i);

            if(dist[node.start] != INF && dist[node.end] > dist[node.start] + node.cost){

                System.out.println("-1");
                return false;
            }
        }

        for (int i = 2; i < dist.length; i++) {
            if (dist[i] == INF)
                System.out.println(-1);
            else
                System.out.println(dist[i]);
        }

        return true;

    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P11657_타임머신/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int m = 0; m < M; m++) {
            int a, b, c;

            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.add(new Node(a,b,c));
        }

        BellmanFord(1);
    }
}

class Node {
    int start;
    int end;
    int cost;

    public Node(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}