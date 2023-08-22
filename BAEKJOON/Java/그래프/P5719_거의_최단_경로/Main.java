package 그래프.P5719_거의_최단_경로;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int S, D;
    static List<List<Node>> graph;
    static int[] distance;
    static List<Integer>[] parents;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P5719_거의_최단_경로/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (N != 0 && M != 0) {

            st = new StringTokenizer(br.readLine());

            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>(N);

            for (int i = 0; i < N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.get(start).add(new Node(end, cost));
            }

//            for (int i = 0; i < N; i++) {
//                System.out.println(graph.get(i));
//            }

            check = new boolean[N][N];

            dijkstra();
            backTracking(D);
            dijkstra();

            if (distance[D] == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(distance[D]);
            }


            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }

    }

    static public void backTracking(int start) {
        if(start == S) return;

        for (int i = 0; i < parents[start].size(); i++) {
            int end = parents[start].get(i);

            if(check[end][start] == false) {
                check[end][start] = true;
                backTracking(end);
            }
        }

    }

    static public void dijkstra() {
        distance = new int[N];
        parents = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            parents[i] = new ArrayList<>();
        }

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[S] = 0;

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(S, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (distance[node.index] < node.cost) continue;

            for (int i = 0; i < graph.get(node.index).size(); i++) {
                int nextIndex = graph.get(node.index).get(i).index;
                int cost = graph.get(node.index).get(i).cost + node.cost;

                if (check[node.index][nextIndex] == false) {
                    if (distance[nextIndex] > cost) {
                        distance[nextIndex] = cost;
                        parents[nextIndex].clear();
                        parents[nextIndex].add(node.index);
                        queue.add(new Node(nextIndex, cost));
                    } else if (distance[nextIndex] == cost) {
                        parents[nextIndex].add(node.index);
                    }
                }
            }
        }
    }
}

class Node {
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", cost=" + cost +
                '}';
    }
}
