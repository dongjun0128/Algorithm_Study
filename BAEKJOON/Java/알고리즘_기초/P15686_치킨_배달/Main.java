package 알고리즘_기초.P15686_치킨_배달;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<ChickenHouse> chickenHouseList = new ArrayList<>();
    static ArrayList<House> houseList = new ArrayList<>();
    static boolean[] visited;
    static int minChickenDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/알고리즘_기초/P15686_치킨_배달/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chickenHouseList.add(new ChickenHouse(i, j));
                } else if (map[i][j] == 1) {
                    houseList.add(new House(i, j));
                }
            }
        }

        visited = new boolean[chickenHouseList.size()];

        for (int i = 0; i < chickenHouseList.size(); i++) {
            dfs(new ArrayList<ChickenHouse>(), i, M);
        }

        System.out.println(minChickenDistance);

    }

    static void dfs(ArrayList<ChickenHouse> survivalChickenHouseList, int chickenHouseListIdx, int survivalChickenHouse) {
        // 1. 체크인
        visited[chickenHouseListIdx] = true;
        survivalChickenHouseList.add(chickenHouseList.get(chickenHouseListIdx));

        // 2. 목적지인가?
        if (survivalChickenHouse == survivalChickenHouseList.size()) {
            //System.out.println(survivalChickenHouseList);
            calculateChickenDistance(houseList, survivalChickenHouseList);
            resetChickenDistance();
        } else{
            // 3. 연결된 곳 순회
            for (int i = chickenHouseListIdx + 1; i < chickenHouseList.size(); i++) {
                // 4. 갈 수 있는가?
                if (visited[i] == false) {
                    // 5.간다
                    dfs(survivalChickenHouseList, i, survivalChickenHouse);
                }
            }
        }

        // 6. 체크아웃
        visited[chickenHouseListIdx] = false;
        for (int i = 0; i < survivalChickenHouseList.size(); i++) {
            int r = chickenHouseList.get(chickenHouseListIdx).r;
            int c = chickenHouseList.get(chickenHouseListIdx).c;

            if(survivalChickenHouseList.get(i).r == r && survivalChickenHouseList.get(i).c == c){
                survivalChickenHouseList.remove(i);
                break;
            }
        }
    }

    static void resetChickenDistance(){
        for (int i = 0; i < houseList.size(); i++) {
            houseList.get(i).reset();
        }
    }

    static void calculateChickenDistance(ArrayList<House> houseList, ArrayList<ChickenHouse> survivalChickenHouseList) {
        for (int i = 0; i < houseList.size(); i++) {

            House house = houseList.get(i);

            int houseR = house.r;
            int houseC = house.c;

            for (int j = 0; j < survivalChickenHouseList.size(); j++) {

                ChickenHouse chickenHouse = survivalChickenHouseList.get(j);

                int chickenR = chickenHouse.r;
                int chickenC = chickenHouse.c;

                house.setChickenDistance(calculateDistance(houseR, chickenR, houseC, chickenC));
            }
        }

        int chickenDistance = 0;

        for (int i = 0; i < houseList.size(); i++) {
            chickenDistance += houseList.get(i).chickenDistance;
        }

        minChickenDistance = Math.min(chickenDistance, minChickenDistance);
    }

    static int calculateDistance(int houseR, int chickenR, int houseC, int chickenC) {
        return Math.abs(houseR - chickenR) + Math.abs(houseC - chickenC);
    }
}

class ChickenHouse {
    int r;
    int c;
    int chickenDistance;

    public ChickenHouse(int r, int c) {
        this.r = r;
        this.c = c;
        this.chickenDistance = 0;
    }

    @Override
    public String toString() {
        return "ChickenHouse{" +
                "r=" + r +
                ", c=" + c +
                ", chickenDistance=" + chickenDistance +
                '}';
    }
}

class House {
    int r;
    int c;
    int chickenDistance;

    public House(int r, int c) {
        this.r = r;
        this.c = c;
        this.chickenDistance = Integer.MAX_VALUE;
    }

    public void reset(){
        this.chickenDistance = Integer.MAX_VALUE;
    }

    public void setChickenDistance(int chickenDistance) {
        this.chickenDistance = Math.min(chickenDistance, this.chickenDistance);
    }

    @Override
    public String toString() {
        return "House{" +
                "r=" + r +
                ", c=" + c +
                ", chickenDistance=" + chickenDistance +
                '}';
    }
}