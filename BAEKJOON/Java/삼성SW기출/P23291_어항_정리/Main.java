package 삼성SW기출.P23291_어항_정리;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P23291_어항_정리/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        // 어항은 정뮥면체 모양이고, 한 변의 길이는 모두 1이다.
        // 상어가 가지고 있는 어항은 N개이고, 가장 처음에 어항은 일렬로 바다 위에 놓여져 있다.
        // 어항에는 물고기가 한 마리 이상 들어있다.
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 어항 정리하는 과정
        // 1. 물고기의 수가 가장 적은 어항에 물고리를 한 마리 넣는다.
        // 만약, 그러한 어항이 여러개라면 물고기의 수가 최소인 어항 모두에 한 마리씩 넣는다.
        addFish();

        // 2. 어항을 쌓는다.
        // 먼저, 가장 왼쪽에 있는 어항을 그 어항의 오른쪽에 있는 어항 위에 올려놓는다.
        // 이제 2개 이상 쌓여있는 어항을 모두 공중 부양 시킨 다음, 전체를 시계방향으로 90도 회전시킨다.
        // 이후 공중 부양시킨 어항을 바닥에 있는 어항의 위에 올려놓는다.
        // 바닥의 가장 왼쪽에 있는 어항 위에 공중 부양시킨 어항 중 가장 왼쪽에 있는 어항이 있어야 한다.
        // 이 작업은 공중 부양시킨 어항 중 가장 오른쪽에 있는 어항의 아래에 바닥에 있는 어항이 있을때까지 반복한다.

        // 공중부양 작업이 모두 끝나면, 어항에 있는 물고기의 수를 조절한다.
        // 모든 인접한 두 어항에 대해서 물고기 수의 차이를 구한다.
        // 이 차이를 5로 나눈 몫을 d라고 하자.
        // d가 0보다 크면, 두 어항 중 물고기의 수가 많은 곳에 있는 물고기 d 마리를 적은 곳에 있는 곳으로 보낸다.
        // 이 과정은 모든 인접한 칸에 대해서 동시에 발생한다.

        // 이제 다시 어항을 바닥에 일렬로 놓아야 한다.
        // 가장 왼쪽에 있는 어항부터, 그리고 아래에 있는 어항 부터 가장 위에 있는 어항까지 순서대로 바닥에 놓아야 한다.

        // 다시 공중 부양 작업을 해야 한다.
        // 이번에는 가운데를 중심으로 왼쪽 N/2개를 공중부양 시켜 전체를 시계 방향으로 180도 회전시킨 다음, 오른쪽 N/2개의 위에 놓는다.
        // 이 작업은 두 번 반복해야 한다.
        // 두 번 반복하면 바닥에 있는 어항의 수는 N/4가 된다.

        // 다시 한 물고기 조절 작업을 수행하고, 바닥에 일렬로 놓는 작업을 수행한다.
    }

    static public void upArr() {
        int[][] tempArr = new int[N][N];

        for(int i = 0 ; i < N ; i++) {
            tempArr[N - 1][i] = arr[i];
        }


    }

    // 1. 물고기의 수가 가장 적은 어항에 물고리를 한 마리 넣는다.
    // 만약, 그러한 어항이 여러개라면 물고기의 수가 최소인 어항 모두에 한 마리씩 넣는다.
    static public void addFish() {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            min = Math.min(arr[i], min);
        }

        for (int i = 0; i < N; i++) {
            if(arr[i] == min) {
                arr[i]++;
            }
        }
    }

}
