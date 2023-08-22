package 그래프.P1854_K번째_최단경로_찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m, k;
    static List<List<Node>> graph;
    static PriorityQueue<Integer>[] distance;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P1854_K번째_최단경로_찾기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, cost));
        }

//        for (int i = 0; i <= n; i++) {
//            System.out.println(graph.get(i));
//        }

        dijkstra();

        for (int i = 1; i < distance.length; i++) {
            if (distance[i].size() == k) System.out.println(distance[i].peek() * -1);
            else System.out.println(-1);
        }
    }

    static public void dijkstra() {
        distance = new PriorityQueue[n + 1];

        for (int i = 0; i < distance.length; i++) {
            distance[i] = new PriorityQueue<>();
        }

        distance[1].add(0);

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < graph.get(node.index).size(); i++) {
                int nextIndex = graph.get(node.index).get(i).index;
                int cost = node.distance + graph.get(node.index).get(i).distance;

                if(distance[nextIndex].size() < k) {
                    distance[nextIndex].add(cost * -1);
                    queue.add(new Node(nextIndex, cost));
                } else if (distance[nextIndex].peek() * (-1) > cost) {
                    distance[nextIndex].poll();
                    distance[nextIndex].add(cost * -1);
                    queue.add(new Node(nextIndex, cost));
                }
            }
        }
    }
}

class Node {
    int index;
    int distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", distance=" + distance +
                '}';
    }
}