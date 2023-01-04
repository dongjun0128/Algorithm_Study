package 구현;

import java.util.Scanner;

public class 상하좌우 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        scan.nextLine(); //버퍼 지우기
        String[] plans = scan.nextLine().split(" ");
        int x = 1, y = 1;

        // L, R, U, D에 따른 이동 방향
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        char[] moveTypes = {'L', 'R', 'U', 'D'};

        // 이동 계획을 하나씩 확인
        for (int i = 0; i < plans.length; i++) {
            char plan = plans[i].charAt(0);
            // 이동 후 좌표 구하기
            int nx = -1, ny = -1;
            for (int j = 0; j < 4; j++) {
                if (plan == moveTypes[j]) {
                    nx = x + dx[j];
                    ny = y + dy[j];
                }
            }

            if (nx < 1 || ny < 1 || nx > n || ny > n) continue;
            // 이동 수행
            x = nx;
            y = ny;
        }

        System.out.println(x + " " + y);
    }
}

/*
input
5
R R R U D D
output
3 4
 */
