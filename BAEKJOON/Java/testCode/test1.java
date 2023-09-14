package testCode;

import java.util.*;

public class test1 {

    static boolean[][][] map = new boolean[11][11][4];
    static int X = 5;
    static int Y = 5;
    static int[] dx = {-1 , 0 , 1, 0};
    static int[] dy = {0 , 1 , 0 , -1};
    static HashSet<String> set = new HashSet<>();

    public static int solution(String dirs) {
        int answer = 0;

        for(int i = 0 ; i < dirs.length() ; i++) {
            int index = 0;

            if(dirs.charAt(i) == 'U') {
                index = 0;
            } else if(dirs.charAt(i) == 'R') {
                index = 1;
            } else if(dirs.charAt(i) == 'D') {
                index = 2;
            } else if(dirs.charAt(i) == 'L') {
                index = 3;
            }

            int nx = X + dx[index];
            int ny = Y + dy[index];

            if(nx < 0 || nx > 10 || ny < 0 || ny > 10) continue;

            if(map[nx][ny][index] == false) {
                map[nx][ny][index] = true;
                map[nx][ny][(index + 2) % 4] = true;
                map[X][Y][index] = true;
                map[X][Y][(index + 2) % 4] = true;

                answer++;
            }

            X = nx;
            Y = ny;

            HashMap<String, String> map = new HashMap<>();

            map.put("asd", "asd");
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("UDU"));
    }
}