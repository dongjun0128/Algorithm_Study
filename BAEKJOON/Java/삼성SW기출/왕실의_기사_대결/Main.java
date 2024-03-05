package 삼성SW기출.왕실의_기사_대결;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Robot {
        int id;
        int x;
        int y;
        int h;
        int w;
        int k;
        int cntK;
        boolean alive;

        public Robot(int id, int x, int y, int h, int w, int k) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
            this.k = k;
            cntK = 0;
            alive = true;
        }

        public void inMap(boolean imMove) {
            int startX = this.x;
            int startY = this.y;
            int endX = x + h - 1;
            int endY = y + w - 1;

            for (int i = startX; i <= endX; i++) {
                for (int j = startY; j <= endY; j++) {
                    robotMap[i][j] = id;

                    if (imMove == false && map[i][j] == 1) {
                        cntK++;
                    }
                }
            }
        }

        public void outMap() {
            int startX = this.x;
            int startY = this.y;
            int endX = x + h - 1;
            int endY = y + w - 1;

            for (int i = startX; i <= endX; i++) {
                for (int j = startY; j <= endY; j++) {
                    robotMap[i][j] = 0;
                }
            }
        }
    }

    static int L,N, Q;

    static int[][] robotMap;
    static int[][] map; // 0 빈칸 1 함정 2 벽

    static ArrayList<Robot> robots;
    static boolean[] visited;
    static int moveRobotId;
    static ArrayList<Integer> removeRobots;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/왕실의_기사_대결/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[L][L];
        robotMap = new int[L][L];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < L; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    robotMap[i][j] = -1;
                }
            }
        }

        robots = new ArrayList<>();
        robots.add(new Robot(0, -1, -1, -1, -1, -1)); // 더미 로봇
        robots.get(0).alive = false;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            robots.add(new Robot(i, x, y, h, w, k));

            robots.get(i).inMap(true);
        }

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());

            int id = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            moveRobotId = id;

            if (robots.get(id).alive == true) {
                if (canMove(robots.get(id), d) == true) {
                    visited = new boolean[N + 1];
                    removeRobots = new ArrayList<>();
                    remove(robots.get(id), d);
                    create();
                }
            }

            for (Robot robot : robots) {
                if (robot.alive == true) {
                    if (robot.cntK >= robot.k) {
                        robot.alive = false;
                        robot.outMap();
                    }
                }
            }
        }

        int answer = 0;

        for (Robot robot : robots) {
            if (robot.alive == true) {
                answer += robot.cntK;
            }
        }

        System.out.println(answer);

    }

    static void create() {
        for (int id : removeRobots) {
            if (id == moveRobotId) {
                robots.get(id).inMap(true);
            } else {
                robots.get(id).inMap(false);
            }
        }
    }

    static void remove(Robot robot, int d) {
        if (d == 0) { // 위
            int nx = robot.x + dx[d];

            for (int i = robot.y; i <= robot.y + robot.w - 1; i++) {

                if (robotMap[nx][i] != 0 && visited[robotMap[nx][i]] == false) {
                    visited[robotMap[nx][i]] = true;

                    int robotId = robotMap[nx][i];
                    remove(robots.get(robotId), d);
                }
            }
        } else if (d == 1) { // 오른쪽
            int ny = robot.y + robot.w - 1 + dy[d];

            for (int i = robot.x; i <= robot.x + robot.h - 1  ; i++) {
                if (robotMap[i][ny] != 0 && visited[robotMap[i][ny]] == false) {
                    visited[robotMap[i][ny]] = true;

                    int robotId = robotMap[i][ny];
                    remove(robots.get(robotId), d);
                }
            }

        } else if (d == 2) { // 아래
            int nx = robot.x + robot.h - 1 + dx[d];

            for (int i = robot.y; i <= robot.y + robot.w - 1; i++) {

                if (robotMap[nx][i] != 0 && visited[robotMap[nx][i]] == false) {
                    visited[robotMap[nx][i]] = true;

                    int robotId = robotMap[nx][i];
                    remove(robots.get(robotId), d);
                }
            }

        } else if (d == 3) { // 왼쪽
            int ny = robot.y + dy[d];

            for (int i = robot.x; i <= robot.x + robot.h - 1  ; i++) {
                if (robotMap[i][ny] != 0 && visited[robotMap[i][ny]] == false) {
                    visited[robotMap[i][ny]] = true;

                    int robotId = robotMap[i][ny];
                    remove(robots.get(robotId), d);
                }
            }
        }

        robot.outMap();
        robot.x = robot.x + dx[d];
        robot.y = robot.y + dy[d];
        removeRobots.add(robot.id);

    }

    static boolean canMove(Robot robot, int d) {
        if (d == 0) { // 위
            int nx = robot.x + dx[d];

            if (nx < 0 || nx >= L) { // 수정해보기
                return false;
            }

            for (int i = robot.y; i <= robot.y + robot.w - 1; i++) {

                if(map[nx][i] == 2) {
                    return false;
                }

                if (robotMap[nx][i] != 0) {
                    int robotId = robotMap[nx][i];

                    if (canMove(robots.get(robotId), d) == false) {
                        return false;
                    }
                }
            }
        } else if (d == 1) { // 오른쪽
            int ny = robot.y + robot.w - 1 + dy[d];

            if(ny < 0 || ny >= L) return false;

            for (int i = robot.x; i <= robot.x + robot.h - 1  ; i++) {

                if(map[i][ny] == 2) {
                    return false;
                }

                if (robotMap[i][ny] != 0) {
                    int robotId = robotMap[i][ny];

                    if (canMove(robots.get(robotId), d) == false) {
                        return false;
                    }
                }
            }

        } else if (d == 2) { // 아래
            int nx = robot.x + robot.h - 1 + dx[d];

            if (nx < 0 || nx >= L) { // 수정해보기
                return false;
            }

            for (int i = robot.y; i <= robot.y + robot.w - 1; i++) {

                if(map[nx][i] == 2) {
                    return false;
                }

                if (robotMap[nx][i] != 0) {
                    int robotId = robotMap[nx][i];

                    if (canMove(robots.get(robotId), d) == false) {
                        return false;
                    }
                }
            }

        } else if (d == 3) { // 왼쪽
            int ny = robot.y + dy[d];

            if(ny < 0 || ny >= L) return false;

            for (int i = robot.x; i <= robot.x + robot.h - 1  ; i++) {

                if(map[i][ny] == 2) {
                    return false;
                }

                if (robotMap[i][ny] != 0) {
                    int robotId = robotMap[i][ny];

                    if (canMove(robots.get(robotId), d) == false) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
