package 인덱스_트리.P2094_강수량;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int Y, X;
    static List<Raining> rainingList;
    static int S;
    static int[] tree;
    static HashMap<Integer, Raining> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/인덱스_트리/P2094_강수량/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        rainingList = new ArrayList<>(n);
        int index = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int year = Integer.parseInt(st.nextToken());
            int rain = Integer.parseInt(st.nextToken());

            rainingList.add(new Raining(year, rain, index++));
            hashMap.put(year, rainingList.get(i));
        }

        init();

        //System.out.println(Arrays.toString(tree));

        m = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            Y = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            Raining rainingY = hashMap.get(Y);
            Raining rainingX = hashMap.get(X);

            boolean check1;
            boolean check2;
            boolean check3;

            if (rainingY != null && rainingX != null) {
                if (X - Y == rainingX.index - rainingY.index) {
                    check1 = true;
                } else {
                    check1 = false;
                }

                if (rainingX.rain <= rainingY.rain) {
                    check2 = true;
                } else {
                    check2 = false;
                }

                int maxRain = query(rainingY.index + 2, rainingX.index);

                if (maxRain < rainingX.rain) {
                    check3 = true;
                } else {
                    check3 = false;
                }

                if (check1 && check2 && check3) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }

            } else {
                if (rainingX == null && rainingY == null) {
                    System.out.println("maybe");
                } else if (rainingX == null) {

                }
            }
        }

    }

    public static int query(int left, int right) {
        left = left + S - 1;
        right = right + S - 1;

        int max = Integer.MIN_VALUE;

        while (left <= right) {
            if (left % 2 == 1) {
                max = Math.max(max, tree[left]);
                left++;
            }

            if (right % 2 == 0) {
                max = Math.max(max, tree[right]);
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return max;
    }

    public static void init() {
        S = 1;

        while (S < n) {
            S *= 2;
        }

        tree = new int[S * 2];

        for (int i = 0; i < n; i++) {
            tree[i + S] = rainingList.get(i).rain;
        }

        for (int i = S - 1; i > 0 ; i--) {
            tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
        }
    }
}

class Raining {
    int year;
    int rain;
    int index;

    public Raining(int year, int rain, int index) {
        this.year = year;
        this.rain = rain;
        this.index = index;
    }
}
