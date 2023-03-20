package 삼성SW기출.P14891_톱니바퀴;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] gears;
    static int[] gear1, gear2, gear3, gear4;
    static int k;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P14891_톱니바퀴/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        gear1 = new int[8];
        gear2 = new int[8];
        gear3 = new int[8];
        gear4 = new int[8];
        gears = new int[5][8];

        String input = st.nextToken();
        for (int i = 0; i < 8; i++) {
            gear1[i] = input.charAt(i) - '0';
        }

        st = new StringTokenizer(br.readLine());
        input = st.nextToken();
        for (int i = 0; i < 8; i++) {
            gear2[i] = input.charAt(i) - '0';
        }

        st = new StringTokenizer(br.readLine());
        input = st.nextToken();
        for (int i = 0; i < 8; i++) {
            gear3[i] = input.charAt(i) - '0';
        }

        st = new StringTokenizer(br.readLine());
        input = st.nextToken();
        for (int i = 0; i < 8; i++) {
            gear4[i] = input.charAt(i) - '0';
        }

        gears[1] = gear1;
        gears[2] = gear2;
        gears[3] = gear3;
        gears[4] = gear4;

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        for (int a = 0; a < k; a++) {
            st = new StringTokenizer(br.readLine());
            int tempNum = Integer.parseInt(st.nextToken());
            int tempDirection = Integer.parseInt(st.nextToken());
            visited = new boolean[5];

            Queue<Gear> queue = new LinkedList<>();
            queue.add(new Gear(tempNum, tempDirection));

            while (!queue.isEmpty()) {
                Gear gear = queue.poll();
                int gearNum = gear.gearNum;
                int gearDirection = gear.gearDirection;
                visited[gearNum] = true;

                int leftGear = gearNum - 1;
                int rightGear = gearNum + 1;

                if (leftGear >= 1 && visited[leftGear] == false) {
                    // 맞닿은 극이 다른 경우 왼쪽 톱니바퀴는 반대방향으로 회전
                    if (gears[gearNum][6] != gears[leftGear][2]) {
                        queue.add(new Gear(leftGear, gearDirection * -1));
                    }
                }

                if (rightGear <= 4 && visited[rightGear] == false) {
                    // 맞닿은 극이 다른 경우 왼쪽 톱니바퀴는 반대방향으로 회전
                    if (gears[gearNum][2] != gears[rightGear][6]) {
                        queue.add(new Gear(rightGear, gearDirection * -1));
                    }
                }

                moveGear(gearNum,gearDirection);
            }
        }

        int result = 0;

        if(gears[1][0] == 1) result += 1;
        if(gears[2][0] == 1) result += 2;
        if(gears[3][0] == 1) result += 4;
        if(gears[4][0] == 1) result += 8;

        System.out.println(result);
    }

    static void moveGear(int gearNum, int gearDirection) {
        int[] temp = new int[8];
        int[] gear = gears[gearNum];

        if (gearDirection == 1) { // 시계방향
            for (int i = 1; i < 8; i++) {
                temp[i] = gear[i - 1];
            }
            temp[0] = gear[7];
            gears[gearNum] = temp;
        } else { // 반시계방향
            for (int i = 0; i < 7; i++) {
                temp[i] = gear[i + 1];
            }
            temp[7] = gear[0];
            gears[gearNum] = temp;
        }
    }
}

class Gear {
    int gearNum;
    int gearDirection;

    public Gear(int gearNum, int gearDirection) {
        this.gearNum = gearNum;
        this.gearDirection = gearDirection;
    }
}