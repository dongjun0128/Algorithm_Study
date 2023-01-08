package 구현;

import java.util.Scanner;

public class 왕실의_나이트 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 현재 나이트의 위치 입력받기
        String inputData = sc.nextLine();
        int row = inputData.charAt(1) - '0';
        int column = inputData.charAt(0) - 'a' + 1;

        // 나이트가 이동할 수 있는 8가지 방향 정의
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

        // 8가지 방향에 대하여 각 위치로 이동이 가능한지 확인
        int result = 0;
        for (int i = 0; i < 8; i++) {
            // 이동하고자 하는 위치 확인
            int nextRow = row + dx[i];
            int nextColumn = column + dy[i];
            // 해당 위치로 이동이 가능하다면 카운트 증가
            if (nextRow >= 1 && nextRow <= 8 && nextColumn >= 1 && nextColumn <= 8) {
                result += 1;
            }
        }

        System.out.println(result);
    }

    /*
    static int[][] graph;
    static int cnt=0;

    public static void main(String[] args) {
        graph = new int[8][8];
        Scanner scan = new Scanner(System.in);
        int x=0;
        int y=0;

        String input = scan.next();

        for(char d : input.toCharArray()){
            if(Character.isDigit(d)){
                x = (int)d - (int)'1';
            }
            else{
                y = (int)d - (int)'a';
            }
        }
        move(x,y);

        System.out.println(cnt);
    }

    static void move(int x, int y){
        int []dx = {2,2,-2,-2,1,1,-1,-1};
        int []dy = {1,-1,1,-1,2,-2,2,-1};

        for(int i = 0 ; i<8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx <0 || nx >8 || ny < 0 || ny >8){
                continue;
            }

            cnt++;
        }
    }
     */
}

/*
input
c2
output
6

input
a1
output
2
 */