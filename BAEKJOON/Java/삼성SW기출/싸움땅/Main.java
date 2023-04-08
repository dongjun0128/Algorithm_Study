package 삼성SW기출.싸움땅;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Gun implements Comparable<Gun> {
        int power;

        public Gun(int power) {
            this.power = power;
        }

        @Override
        public String toString() {
            return "Gun{" +
                    "power=" + power +
                    '}';
        }

        @Override
        public int compareTo(Gun o) { // 총의 공격력 만큼 내림차순
            return o.power - this.power;
        }
    }

    static class Player {
        int x;
        int y;
        int d;
        int s;
        int id;
        Gun gun;
        int sumS;
        int point;

        public Player(int x, int y, int d, int s, int id) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.id = id;
        }

        public void refresh() {
            if (gun != null)
                sumS = s + gun.power;
            else
                sumS = s;
        }
    }

    static int N, M, K;
    static PriorityQueue<Gun>[][] gunMap;
    static int[][] playerMap;
    static ArrayList<Player> players;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/싸움땅/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        gunMap = new PriorityQueue[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int power = Integer.parseInt(st.nextToken());

                gunMap[i][j] = new PriorityQueue<>();

                if (power != 0)
                    gunMap[i][j].add(new Gun(power));
            }
        }

        players = new ArrayList<>();
        players.add(new Player(-1, -1, -1, -1, -1)); // 0번 채우기 위한 더미
        playerMap = new int[N][N];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            players.add(new Player(x - 1, y - 1, d, s, i));
            playerMap[x - 1][y - 1] = i;
        }

        while (K-- > 0) {
            // 1. 첫 번째 플레이어 부터 향하는 방향으로 움직이기, 격자를 벗어난다면 정 반대방향
            for (int i = 1; i < players.size(); i++) {
                Player player = players.get(i);

                int nx = player.x + dx[player.d];
                int ny = player.y + dy[player.d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) { //격자를 벗어나면
                    player.d = (player.d + 2) % 4; //정 반대방향
                    nx = player.x + dx[player.d];
                    ny = player.y + dy[player.d];
                }

                // 플레이어 이동
                playerMap[player.x][player.y] = 0;

                player.x = nx;
                player.y = ny;

                if (playerMap[player.x][player.y] == 0) { //이동할 방향에 플레이어가 없다면
                    playerMap[player.x][player.y] = player.id;

                    if (!gunMap[player.x][player.y].isEmpty()) { // 총이 있으면 획득
                        if (player.gun == null) { // 플레이어의 총이 없으면 획득
                            Gun gun = gunMap[player.x][player.y].poll();

                            player.gun = gun;
                            player.refresh();
                        } else { // 있다면 더 쎈 총 갖고 나머지는 내려놓는다.
                            Gun gun = gunMap[player.x][player.y].poll();

                            if (player.gun.power < gun.power) { // 바닥에 있는 총이 더 쎄면 내꺼 내려놓고 총 줍기
                                Gun tempGun = new Gun(player.gun.power);
                                gunMap[player.x][player.y].add(tempGun);
                                player.gun = null;
                                player.gun = gun;

                            } else { // 내가 갖고있는 총이 더 쎄면 다시 내려놓기
                                gunMap[player.x][player.y].add(gun);
                            }
                        }
                    }

                } else { // 이동 한 곳에 플레이어가 있어서 싸워야한다.
                    int otherPlayerId = playerMap[player.x][player.y];
                    Player otherPlayer = players.get(otherPlayerId);

                    // 싸움
                    player.refresh();
                    otherPlayer.refresh();

                    if (player.sumS > otherPlayer.sumS) {
                        player.point += (player.sumS - otherPlayer.sumS);
                        loserPlayer(otherPlayer);
                        winnerPlayer(player);
                    } else if (player.sumS < otherPlayer.sumS) {
                        otherPlayer.point += (otherPlayer.sumS - player.sumS);
                        loserPlayer(player);
                        winnerPlayer(otherPlayer);
                    } else {
                        if(player.s > otherPlayer.s) {
                            player.point += (player.sumS - otherPlayer.sumS);
                            loserPlayer(otherPlayer);
                            winnerPlayer(player);
                        } else {
                            otherPlayer.point += (otherPlayer.sumS - player.sumS);
                            loserPlayer(player);
                            winnerPlayer(otherPlayer);
                        }
                    }
                }
            }
        }

        for (int i = 1; i < players.size(); i++) {
            System.out.print(players.get(i).point);
            System.out.print(" ");
        }

    }

    static void loserPlayer(Player loserPlayer) {
        if (loserPlayer.gun != null) { // 총이 있다면
            // 본인의 총 내려놓기
            Gun loserGun = new Gun(loserPlayer.gun.power);
            gunMap[loserPlayer.x][loserPlayer.y].add(loserGun);
            loserPlayer.gun = null;
        }

        while (true) {
            // 원래 가려던 방향으로 이동
            int nx = loserPlayer.x + dx[loserPlayer.d];
            int ny = loserPlayer.y + dy[loserPlayer.d];

            // 격자를 벗어나거나 다른 플레이어가 있는 경우 오른쪽 90도 회전
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || playerMap[nx][ny] != 0) {
                loserPlayer.d = (loserPlayer.d + 1) % 4;
                continue;
            }


            playerMap[loserPlayer.x][loserPlayer.y] = 0;
            loserPlayer.x = nx;
            loserPlayer.y = ny;
            playerMap[loserPlayer.x][loserPlayer.y] = loserPlayer.id;
            break;
        }

        if (!gunMap[loserPlayer.x][loserPlayer.y].isEmpty()) {
            Gun gun = gunMap[loserPlayer.x][loserPlayer.y].poll();

            loserPlayer.gun = gun;
        }
    }

    static void winnerPlayer(Player winnerPlayer) {
        playerMap[winnerPlayer.x][winnerPlayer.y] = winnerPlayer.id;

        if (!gunMap[winnerPlayer.x][winnerPlayer.y].isEmpty()) { // 총이 있으면 획득
            if (winnerPlayer.gun == null) { // 플레이어의 총이 없으면 획득
                Gun gun = gunMap[winnerPlayer.x][winnerPlayer.y].poll();

                winnerPlayer.gun = gun;
                winnerPlayer.refresh();
            } else { // 있다면 더 쎈 총 갖고 나머지는 내려놓는다.
                Gun gun = gunMap[winnerPlayer.x][winnerPlayer.y].poll();

                if (winnerPlayer.gun.power < gun.power) { // 바닥에 있는 총이 더 쎄면 내꺼 내려놓고 총 줍기
                    Gun tempGun = new Gun(winnerPlayer.gun.power);
                    gunMap[winnerPlayer.x][winnerPlayer.y].add(tempGun);
                    winnerPlayer.gun = null;
                    winnerPlayer.gun = gun;

                } else { // 내가 갖고있는 총이 더 쎄면 다시 내려놓기
                    gunMap[winnerPlayer.x][winnerPlayer.y].add(gun);
                }
            }
        }
    }


}
