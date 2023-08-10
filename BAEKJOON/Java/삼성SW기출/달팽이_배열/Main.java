package 삼성SW기출.달팽이_배열;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N;
    static boolean[] visited;
    static ArrayList<Integer> list;
    static List<List<Integer>> cntList;

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);

        //N = scanner.nextInt();

        //outInArr();
        //inOutArr();

        list = new ArrayList<>();
        cntList = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            list.add(i);
        }

        visited = new boolean[list.size()];

        for (int i = 0; i < list.size(); i++) {
            combination(new ArrayList<>(), i, 1);
        }

        System.out.println(cntList.size());
    }

    static void permutation(ArrayList<Integer> permu, int index, int depth) {
        // 1. 체크 인
        permu.add(list.get(index));
        visited[index] = true;

        // 2. 목적지인가?
        if(depth == 3){
            System.out.println(permu);
            cntList.add(permu);
        } else {
            // 3. 연결된 곳 순회
            for (int i = 0; i < list.size(); i++) {
                if(visited[i] == false)
                    permutation(permu,i,depth + 1);
            }
        }

        // 6. 체크 아웃
        permu.remove(depth - 1);
        visited[index] = false;
    }

    static void combination(ArrayList<Integer> combi, int index, int depth) {
        // 1. 체크 인
        combi.add(list.get(index));
        visited[index] = true;

        // 2. 목적지인가?
        if (depth == 4) {
            System.out.println(combi);
            cntList.add(combi);
        } else {
            // 3. 연결된 곳 순회
            for (int i = index; i < list.size(); i++) {
                if (visited[i] == false) {// 4. 갈 수 있는가?
                    // 5. 간다.
                    combination(combi, i, depth + 1);
                }
            }
        }


        // 6. 체크 아웃
        visited[index] = false;
        combi.remove(depth - 1);
    }

    static void outInArr() {
        int[][] map = new int[N][N];

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int num = 1;
        int x = 0;
        int y = -1;
        int direction = 0;
        int height = N - 1;
        int width = N;

        while (true) {
            if (num > N * N) break;

            if (direction % 2 == 0) {
                int cnt = 0;

                while (cnt++ < width) {
                    x = x + dx[direction];
                    y = y + dy[direction];

                    map[x][y] = num;
                    num++;

                }

                width--;
                direction = (direction + 1) % 4;

            } else {
                int cnt = 0;

                while (cnt++ < height) {
                    x = x + dx[direction];
                    y = y + dy[direction];

                    map[x][y] = num;
                    num++;

                }

                height--;
                direction = (direction + 1) % 4;
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static void inOutArr() {
        int[][] map = new int[N][N];

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        int num = 1;
        int x = N / 2;
        int y = N / 2;
        int width = 1;
        int height = 1;
        int direction = 0;

        map[x][y] = num++;

        loop1:
        while (true) {
            if (num > N * N) break;

            if (direction % 2 == 0) {
                int cnt = 0;

                while (cnt++ < width) {
                    x = x + dx[direction];
                    y = y + dy[direction];

                    if (x < 0 || x >= N || y < 0 || x >= N) {
                        break loop1;
                    }

                    map[x][y] = num++;
                }

                direction = (direction + 1) % 4;
                width++;
            } else {
                int cnt = 0;

                while (cnt++ < height) {
                    x = x + dx[direction];
                    y = y + dy[direction];

                    if (x < 0 || x >= N || y < 0 || x >= N) {
                        break loop1;
                    }

                    map[x][y] = num++;
                }

                direction = (direction + 1) % 4;
                height++;
            }


        }

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }

    }
}
