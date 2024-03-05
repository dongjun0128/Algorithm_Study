package 삼성SW기출.싸움땅.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N,M,K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static ArrayList<Player> players;
    static PriorityQueue<Integer>[][] gunMap;
    static int[][] playerMap;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/싸움땅/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        // 게임은 N*N 크기의 격자에서 진행되며, 각각의 격자에는 무기들이 있을 수 있습니다.
        // 초기에는 무기들이 없는 빈 격자에 플레이어들이 위치하며 각 플레이어는 초기 능력치를 가집니다.

        gunMap = new PriorityQueue[N][N];
        players = new ArrayList<>();
        playerMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gunMap[i][j] = new PriorityQueue<>();
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int gun = Integer.parseInt(st.nextToken());

                if(gun != 0)
                    gunMap[i][j].add(gun);
            }
        }

        players.add(new Player(-1, -1, -1, -1, 0));

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            players.add(new Player(x, y, d, s, i));
            playerMap[x][y] = i;
        }

        // 첫 번째 플레이어부터 순차적으로 본인이 향하고 있는 방향대로 한 칸만큼 이동합니다.
        // 만약 해당 방향으로 나갈 때 격자를 벗어나는 경우에는 정반대 방향으로 방향을 바꾸어서 1만큼 이동합니다.

        // 만약 이동한 방향에 플레이어가 없다면 해당 칸에 총이 있는지 확인합니다.
        // 총이 있는 경우, 해당 플레이어는 총을 획득
        // 플레이어가 이미 총을 가지고 있는 경우에는 놓여있는 총들과 플레이어가 가지고 있는 총 가운데 더 쎈 총을 획득 , 나머지는 격자에 둡니다.

        // 만약 이동한 방향에 플레이어가 있는 경우에는 두 플레이어가 싸우게 됩니다.
        // 해당 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합을 비교하여 더 큰 플레이어가 이깁니다.
        // 만일 이 수치가 같은 경우에는 플레이어의 초기 능력치가 높은 플레이어가 승리하게 됩니다.
        // 이긴 플레이어는 각 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합의 차이 만큼 포인트로 획득
        // 진 플레이어는 본인이 가지고 있는 총을 해당 격자에 내려놓고, 해당 플레이어가 원래 가지고 있던 방향대로 한 칸 이동합니다.
        // 만약 이동하려는 칸에 다른 플레이어가 있거나, 격자 범위 밖인 경우에는 오른쪽으로 90도씩 회전하여 빈칸이 보이는 순간 이동
        // 만약 해당 칸에 총이 있다면 해당 플레이어는 가장 공격력이 높은 총을 획득하고 나머니 총들은 해당 격자에 내려 놓습니다.

        // 이긴 플레이어는 승리한 칸에 떨어져 있는 총들과 원래 들고 있었던 총 중 가장 공격력이 높은 총을 획득하고 격내에 내려 놓습니다.

        while (K-- > 0) {
            // 첫 번째 플레이어부터 순차적으로 본인이 향하고 있는 방향대로 한 칸만큼 이동합니다.
            // 만약 해당 방향으로 나갈 때 격자를 벗어나는 경우에는 정반대 방향으로 방향을 바꾸어서 1만큼 이동합니다.

            for (int i = 1; i <= M; i++) {
                Player player = players.get(i);

                playerMap[player.x][player.y] = 0;

                int nx = player.x + dx[player.d];
                int ny = player.y + dy[player.d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    player.d = (player.d + 2) % 4;

                    nx = player.x + dx[player.d];
                    ny = player.y + dy[player.d];
                }

                // 만약 이동한 방향에 플레이어가 없다면 해당 칸에 총이 있는지 확인합니다.
                // 총이 있는 경우, 해당 플레이어는 총을 획득
                // 플레이어가 이미 총을 가지고 있는 경우에는 놓여있는 총들과 플레이어가 가지고 있는 총 가운데 더 쎈 총을 획득 , 나머지는 격자에 둡니다.
                if (playerMap[nx][ny] == 0) {
                    if (!gunMap[nx][ny].isEmpty()) {
                        int gun = gunMap[nx][ny].poll();

                        if(gun > 0) {
                            if (player.hasGun == false) {
                                player.hasGun = true;
                                player.gun = gun;
                            } else {
                                if (player.gun > gun) { // 플레이어의 총이 더 쎄면 다시 내려놓는다.
                                    gunMap[nx][ny].add(gun);
                                } else { // 바닥의 총이 더 쎄면 내 총을 내려놓고 줍는다.
                                    if(player.gun > 0) {
                                        gunMap[nx][ny].add(player.gun);
                                        player.gun = gun;
                                    }
                                }
                            }
                        }
                    }

                    player.x = nx;
                    player.y = ny;
                    playerMap[player.x][player.y] = player.id;
                } else {
                    player.x = nx;
                    player.y = ny;
                    int otherPlayerId = playerMap[player.x][player.y];
                    Player otherPlayer = players.get(otherPlayerId);

                    int playerPower = player.s + player.gun;
                    int otherPlayerPower = otherPlayer.s + otherPlayer.gun;

                    Player winner;
                    Player loser;

                    if (playerPower == otherPlayerPower) {
                        if (player.s > otherPlayer.s) {
                            winner = player;
                            loser = otherPlayer;
                        } else {
                            winner = otherPlayer;
                            loser = player;
                        }
                    } else if (playerPower > otherPlayerPower) {
                        winner = player;
                        loser = otherPlayer;
                    } else {
                        winner = otherPlayer;
                        loser = player;
                    }

                    winner.point = winner.point + Math.abs(playerPower - otherPlayerPower);
                    loserStep(loser);
                    winnerStep(winner);

                    // 만약 이동한 방향에 플레이어가 있는 경우에는 두 플레이어가 싸우게 됩니다.
                    // 해당 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합을 비교하여 더 큰 플레이어가 이깁니다.
                    // 만일 이 수치가 같은 경우에는 플레이어의 초기 능력치가 높은 플레이어가 승리하게 됩니다.
                    // 이긴 플레이어는 각 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합의 차이 만큼 포인트로 획득
                    // 진 플레이어는 본인이 가지고 있는 총을 해당 격자에 내려놓고, 해당 플레이어가 원래 가지고 있던 방향대로 한 칸 이동합니다.
                    // 만약 이동하려는 칸에 다른 플레이어가 있거나, 격자 범위 밖인 경우에는 오른쪽으로 90도씩 회전하여 빈칸이 보이는 순간 이동
                    // 만약 해당 칸에 총이 있다면 해당 플레이어는 가장 공격력이 높은 총을 획득하고 나머니 총들은 해당 격자에 내려 놓습니다.

                    // 이긴 플레이어는 승리한 칸에 떨어져 있는 총들과 원래 들고 있었던 총 중 가장 공격력이 높은 총을 획득하고 격내에 내려 놓습니다.


                }


                // playerMap 갱신!!
                // player 의 x, y 갱신!!
            }

        }

        for (int i = 1; i <= M; i++) {
            System.out.print(players.get(i).point + " ");
        }

    }

    // 진 플레이어는 본인이 가지고 있는 총을 해당 격자에 내려놓고, 해당 플레이어가 원래 가지고 있던 방향대로 한 칸 이동합니다.
    // 만약 이동하려는 칸에 다른 플레이어가 있거나, 격자 범위 밖인 경우에는 오른쪽으로 90도씩 회전하여 빈칸이 보이는 순간 이동
    // 만약 해당 칸에 총이 있다면 해당 플레이어는 가장 공격력이 높은 총을 획득하고 나머니 총들은 해당 격자에 내려 놓습니다.
    static void loserStep(Player player) {
        if (player.hasGun == true) {
            player.hasGun = false;
            gunMap[player.x][player.y].add(player.gun);
            player.gun = -1;
        }

        playerMap[player.x][player.y] = 0;

        while (true) {
            int nx = player.x + dx[player.d];
            int ny = player.y + dy[player.d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || playerMap[nx][ny] != 0) {
                player.d = (player.d + 1) % 4;
            } else {
                player.x = nx;
                player.y = ny;
                break;
            }
        }

        playerMap[player.x][player.y] = player.id;

        if (!gunMap[player.x][player.y].isEmpty()) {
            int gun = gunMap[player.x][player.y].poll();

            if(gun > 0) {
                player.hasGun = true;
                player.gun = gun;
            }
        }
    }

    // 이긴 플레이어는 승리한 칸에 떨어져 있는 총들과 원래 들고 있었던 총 중 가장 공격력이 높은 총을 획득하고 격내에 내려 놓습니다.
    static void winnerStep(Player player) {
        playerMap[player.x][player.y] = player.id;

        if (!gunMap[player.x][player.y].isEmpty()) {
            int gun = gunMap[player.x][player.y].poll();

            if(gun > 0) {
                if (player.hasGun == false) {
                    player.hasGun = true;
                    player.gun = gun;
                } else {
                    if (player.gun > gun) { // 플레이어의 총이 더 쎄면 다시 내려놓는다.
                        gunMap[player.x][player.y].add(gun);
                    } else { // 바닥의 총이 더 쎄면 내 총을 내려놓고 줍는다.
                        if(player.gun > 0) {
                            gunMap[player.x][player.y].add(player.gun);
                            player.gun = gun;
                        }
                    }
                }
            }
        }

    }

    static class Player {
        int x;
        int y;
        int d;
        int s;
        int id;
        int point;
        int gun;
        boolean hasGun;

        public Player(int x, int y, int d, int s, int id) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.id = id;
            this.point = 0;
            this.gun = -1;
            hasGun = false;
        }
    }

}