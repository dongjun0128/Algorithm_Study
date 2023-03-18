package 삼성SW기출.P14889_스타트와_링크;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] table;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> teamList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P14889_스타트와_링크/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int result = Integer.MAX_VALUE;
        N = Integer.parseInt(st.nextToken());

        table = new int[N][N];
        visited = new boolean[N];
        teamList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            dfs(i, new ArrayList<>());
        }

        for (int i = 0; i < teamList.size(); i++) {
            ArrayList<Integer> tempATeam = teamList.get(i);
            ArrayList<Integer> tempBTeam = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if (!tempATeam.contains(j)) tempBTeam.add(j);
            }

            int aTeam = calculateTeam(tempATeam);
            int bTeam = calculateTeam(tempBTeam);

            result = Math.min(result, Math.abs(aTeam - bTeam));

        }

        System.out.println(result);

    }

    static void dfs(int index, ArrayList<Integer> team) {
        // 1. 체크인
        visited[index] = true;
        team.add(index);

        // 2. 목적지인가?
        if (team.size() == N / 2) {
            ArrayList<Integer> temp = new ArrayList<>();

            for (int i = 0; i < N / 2; i++) {
                temp.add(team.get(i));
            }
            teamList.add(temp);
        } else {
            // 3. 연결된 곳 순회
            for (int i = index; i < N; i++) {
                // 4. 갈 수 있는가?
                if (visited[i] == false) {
                    // 5. 간다
                    dfs(i, team);
                }
            }
        }

        // 6. 체크 아웃
        visited[index] = false;
        team.remove(team.size() - 1);
    }

    static int calculateTeam(ArrayList<Integer> team) {
        int sum = 0;

        for (int i = 0; i < team.size(); i++) {
            for (int j = 0; j < team.size(); j++) {
                int a = team.get(i);
                int b = team.get(j);

                sum += table[a][b];
            }
        }

        return sum;
    }
}
