package 삼성SW기출.P20056_마법사_상어와_파이어볼;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static ArrayList<Fireball> fireballs = new ArrayList<>();
    static Queue<Fireball>[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P20056_마법사_상어와_파이어볼/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            fireballs.add(new Fireball(x, y, m, direction, speed));
        }

        map = new Queue[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            move();
            secondMotion();
        }

        int result = 0;

        for (int i = 0; i < fireballs.size(); i++) {
            result += fireballs.get(i).m;
        }

        System.out.println(result);
    }

    static void move() {

        for (int i = 0; i < fireballs.size(); i++) {
            int direction = fireballs.get(i).direction;
            int speed = fireballs.get(i).speed;

            for (int j = 0; j < speed; j++) {
                fireballs.get(i).x += dx[direction];
                if (fireballs.get(i).x < 0) {
                    fireballs.get(i).x += N;
                } else if (fireballs.get(i).x >= N) {
                    fireballs.get(i).x -= N;
                }

                fireballs.get(i).y += dy[direction];
                if (fireballs.get(i).y < 0) {
                    fireballs.get(i).y += N;
                } else if (fireballs.get(i).y >= N) {
                    fireballs.get(i).y -= N;
                }
            }

            map[fireballs.get(i).x][fireballs.get(i).y].add(fireballs.get(i));
        }
    }

    static void secondMotion() {
        ArrayList<Fireball> tempFireballs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() == 1) {
                    tempFireballs.add(map[i][j].poll());
                } else if (map[i][j].size() >= 2) {
                    ArrayList<Fireball> duplicatedFireballs = new ArrayList<>();

                    while (!map[i][j].isEmpty()) {
                        duplicatedFireballs.add(map[i][j].poll());
                    }

                    tempFireballs = divide(tempFireballs, duplicatedFireballs);
                }
            }
        }

        fireballs = tempFireballs;
    }

    static ArrayList<Fireball> divide(ArrayList<Fireball> tempFireballs, ArrayList<Fireball> duplicatedFireballs) {

        int sumM = 0;
        int sumSpeed = 0;
        int flag = 0;
        int direction = duplicatedFireballs.get(0).direction % 2;

        for (int i = 0; i < duplicatedFireballs.size(); i++) {
            sumM += duplicatedFireballs.get(i).m;
            sumSpeed += duplicatedFireballs.get(i).speed;

            if (direction != duplicatedFireballs.get(i).direction % 2) flag = 1;
        }

        if (sumM / 5 == 0) return tempFireballs;

        int x = duplicatedFireballs.get(0).x;
        int y = duplicatedFireballs.get(0).y;

        if (flag == 0) {
            tempFireballs.add(new Fireball(x, y, sumM / 5, 0, sumSpeed / duplicatedFireballs.size()));
            tempFireballs.add(new Fireball(x, y, sumM / 5, 2, sumSpeed / duplicatedFireballs.size()));
            tempFireballs.add(new Fireball(x, y, sumM / 5, 4, sumSpeed / duplicatedFireballs.size()));
            tempFireballs.add(new Fireball(x, y, sumM / 5, 6, sumSpeed / duplicatedFireballs.size()));
        } else {
            tempFireballs.add(new Fireball(x, y, sumM / 5, 1, sumSpeed / duplicatedFireballs.size()));
            tempFireballs.add(new Fireball(x, y, sumM / 5, 3, sumSpeed / duplicatedFireballs.size()));
            tempFireballs.add(new Fireball(x, y, sumM / 5, 5, sumSpeed / duplicatedFireballs.size()));
            tempFireballs.add(new Fireball(x, y, sumM / 5, 7, sumSpeed / duplicatedFireballs.size()));
        }

        return tempFireballs;
    }
}

class Fireball {
    int x;
    int y;
    int m;
    int direction;
    int speed;

    public Fireball(int x, int y, int m, int direction, int speed) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.direction = direction;
        this.speed = speed;
    }
}
