package 삼성SW기출.P16235_나무_재테크;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A; // S2D2가 땅을 돌아다니면서 추가할 양분
    static int[][] map;
    static ArrayList<Tree> deadTrees;
    static PriorityQueue<Tree> trees;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P16235_나무_재테크/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], 5);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        trees = new PriorityQueue<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int old = Integer.parseInt(st.nextToken());

            trees.add(new Tree(x, y, old));
        }

        while (K-- > 0) {
            // 봄 : 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가. 하나의 칸에 여러 개의 나무가 있으면 아니가 어린 나무부터 양분을 먹는다. 만약 땅에 양분이 부족하면 즉시 사망
            // 여름 : 봄에 죽은 나무가 양분으로 변한다. (죽은 나무의 나이 / 2)
            // 가을 : 나무가 번식한다. 번식한느 나무의 나이는 5의 배수 (나무의 나이 % 5 == 0) 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
            // 겨울 S2D2가 땅을 돌아다니면서 땅에 양분을 추가
            spring();
            summer();
            falls();
            winter();
        }

        System.out.println(trees.size());

    }

    static void spring() {
        deadTrees = new ArrayList<>();
        PriorityQueue<Tree> nextTrees = new PriorityQueue<>();

        while (!trees.isEmpty()) {
            Tree tree = trees.poll();

            if (map[tree.x][tree.y] < tree.old) {
                deadTrees.add(tree);
            } else {
                map[tree.x][tree.y] -= tree.old;
                tree.old += 1;
                nextTrees.add(tree);
            }
        }

        trees = nextTrees;
    }

    static void summer() {
        for (Tree tree : deadTrees) {
            map[tree.x][tree.y] += tree.old / 2;
        }
    }

    static void falls() {
        PriorityQueue<Tree> nextTrees = new PriorityQueue<>();

        for (Tree tree : trees) {
            if (tree.old % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = tree.x + dx[i];
                    int ny = tree.y + dy[i];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        nextTrees.add(new Tree(nx, ny, 1));
                    }
                }
                nextTrees.add(tree);
            } else {
                nextTrees.add(tree);
            }
        }

        trees = nextTrees;
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree> {
        int x;
        int y;
        int old;

        public Tree(int x, int y, int old) {
            this.x = x;
            this.y = y;
            this.old = old;
        }

        @Override
        public int compareTo(Tree o) {
            return this.old - o.old;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "x=" + x +
                    ", y=" + y +
                    ", old=" + old +
                    '}';
        }
    }
}
