package 삼성SW기출.P16234_인구_이동;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] A;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P16234_인구_이동/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        while(true) {
            ArrayList<ArrayList<Country>> union = new ArrayList<>();
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == false) {
                        ArrayList<Country> temp = new ArrayList<>();
                        temp.add(new Country(i, j));
                        visited[i][j] = true;
                        Queue<Country> queue = new LinkedList<>();
                        queue.add(new Country(i, j));

                        while (!queue.isEmpty()) {
                            Country country = queue.poll();

                            int x = country.x;
                            int y = country.y;

                            for (int k = 0; k < 4; k++) {
                                int nx = x + dx[k];
                                int ny = y + dy[k];

                                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                                    if (check(x, y, nx, ny) && visited[nx][ny] == false) {
                                        queue.add(new Country(nx, ny));
                                        visited[nx][ny] = true;
                                        temp.add(new Country(nx, ny));
                                    }
                                }
                            }
                        }

                        union.add(temp);
                    }
                }
            }

            int flag = 0;

            for (int i = 0; i < union.size(); i++) {
                if (union.get(i).size() > 1) {
                    int sum = 0;
                    flag = 1;

                    for (int j = 0; j < union.get(i).size(); j++) {
                        sum += A[union.get(i).get(j).x][union.get(i).get(j).y];
                    }

                    int people = sum / union.get(i).size();

                    for (int j = 0; j < union.get(i).size(); j++) {
                        A[union.get(i).get(j).x][union.get(i).get(j).y] = people;
                    }
                }
            }

            if(flag == 0) break;
            else result++;
        }

        System.out.println(result);

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(A[i]));
//        }
    }

    static boolean check(int x, int y, int nx, int ny) {
        int now = A[x][y];
        int next = A[nx][ny];

        if (Math.abs(now - next) >= L && Math.abs(now - next) <= R) {
            return true;
        }

        return false;
    }
}

class Country {
    int x;
    int y;

    public Country(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Country{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
