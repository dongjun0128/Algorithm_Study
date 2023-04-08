package 삼성SW기출.꼬리잡기놀이;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Ball {
        int x;
        int y;
        int direction;

        public Ball(int x, int y) {
            this.x = x;
            this.y = y;
            this.direction = 0;
        }
    }

    static class Node {
        int x;
        int y;
        int role;

        public Node(int x, int y, int role) {
            this.x = x;
            this.y = y;
            this.role = role;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", role=" + role +
                    '}';
        }
    }

    static int N, M, K;
    static int[][] map;
    static ArrayList<ArrayList<Node>> teams;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static Ball ball;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/꼬리잡기놀이/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        teams = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    ArrayList<Node> team = makeTeam(i, j);
                    teams.add(team);
                    //System.out.println(team);
                }
            }
        }

        ball = new Ball(0, 0);

        while (K-- > 0) {
            // 1. 머리 사람을 따라서 한 칸 이동
            for (ArrayList<Node> team : teams) {
                move(team);
            }

            // 2. 공 던지기
            throwBall();
            moveBall();

        }

        System.out.println(result);
    }

    static void moveBall() {
        if (ball.direction == 0) {
            if (ball.x == N - 1) {
                ball.direction = (ball.direction + 1) % 4;
            } else {
                ball.x++;
            }
        } else if (ball.direction == 1) {
            if (ball.y == N - 1) {
                ball.direction = (ball.direction + 1) % 4;
            } else {
                ball.y++;
            }
        } else if (ball.direction == 2) {
            if (ball.x == 0) {
                ball.direction = (ball.direction + 1) % 4;
            } else {
                ball.x--;
            }
        } else if (ball.direction == 3) {
            if (ball.y == 0) {
                ball.direction = (ball.direction + 1) % 4;
            } else {
                ball.y--;
            }
        }
    }

    static void throwBall() {
        int x = ball.x;
        int y = ball.y;

        while (true) {
            if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 3) {
                int index = findOrder(x, y);
                result += (index * index);
                break;
            }

            x += dx[ball.direction];
            y += dy[ball.direction];

            if (x < 0 || x >= N || y < 0 || y >= N) break;
        }
    }

    static ArrayList<Node> reverseOrder(ArrayList<Node> team) {
        ArrayList<Node> newTeam = new ArrayList<>();

        Node firstToLast = new Node(team.get(0).x, team.get(0).y, 3);
        Node lastToFirst = new Node(team.get(team.size() - 1).x, team.get(team.size() - 1).y, 1);

        newTeam.add(lastToFirst);
        for (int i = team.size() - 2; i >= 1; i--) {
            newTeam.add(team.get(i));
        }
        newTeam.add(firstToLast);

        team = newTeam;

        map[team.get(0).x][team.get(0).y] = team.get(0).role;
        map[team.get(team.size() - 1).x][team.get(team.size() - 1).y] = team.get(team.size() - 1).role;

        return team;
    }

    static int findOrder(int x, int y) {
        for (int j = 0; j < teams.size(); j++) {
            ArrayList<Node> team = teams.get(j);
            for (int i = 0; i < team.size(); i++) {
                if (team.get(i).x == x && team.get(i).y == y) {
                    ArrayList<Node> newTeam = reverseOrder(team);
                    teams.set(j, newTeam);
                    return i + 1;
                }
            }
        }

        return -1;
    }

    static void move(ArrayList<Node> team) {
        Node head = team.get(0);

        int postX = 0;
        int postY = 0;

        for (int i = 0; i < 4; i++) {
            int nx = head.x + dx[i];
            int ny = head.y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (map[nx][ny] == 4) {
                    postX = head.x;
                    postY = head.y;

                    head.x = nx;
                    head.y = ny;

                    map[head.x][head.y] = 1;

                    for (int j = 1; j < team.size(); j++) {
                        Node back = team.get(j);

                        int tempX = back.x;
                        int tempY = back.y;


                        back.x = postX;
                        back.y = postY;

                        map[back.x][back.y] = back.role;
                        postX = tempX;
                        postY = tempY;

                        if (j == team.size() - 1) { // 맨 끝이라면 4로 만들어주기
                            map[postX][postY] = 4;
                        }
                    }

                    break;
                } else if (map[nx][ny] == 3) {
                    postX = head.x;
                    postY = head.y;

                    head.x = nx;
                    head.y = ny;

                    map[head.x][head.y] = 1;

                    for (int j = 1; j < team.size(); j++) {
                        Node back = team.get(j);

                        int tempX = back.x;
                        int tempY = back.y;


                        back.x = postX;
                        back.y = postY;

                        map[back.x][back.y] = back.role;
                        postX = tempX;
                        postY = tempY;
                    }

                    break;
                }
            }
        }
    }

    static ArrayList<Node> makeTeam(int x, int y) {
        ArrayList<Node> team = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(new Node(x, y, 1));
        team.add(new Node(x, y, 1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == false) {
                    if (map[nx][ny] == 2) {
                        queue.add(new Node(nx, ny, 2));
                        team.add(new Node(nx, ny, 2));
                        visited[nx][ny] = true;
                        break;
                    } else if (map[nx][ny] == 3 && team.size() >= 2) {
                        team.add(new Node(nx, ny, 3));
                        visited[nx][ny] = true;
                        break;
                    }
                }
            }
        }


        return team;
    }
}
