package 최장_증가_부분_수열.P12015_가장_긴_증가하는_수열2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/최장_증가_부분_수열/P12015_가장_긴_증가하는_수열2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> LIS = new ArrayList<>();

        LIS.add(arr[0]);
        int lengthOfLIS = 1;

        // LIS 알고리즘 (NlogN)
        for (int i = 1; i < N; i++) {
            if (LIS.get(LIS.size() - 1) < arr[i]) {
                lengthOfLIS++;
                LIS.add(arr[i]);
            }

            else {
                int start = 0;
                int end = LIS.size();

                while(start < end){
                    int mid = (start + end) / 2;

                    if(LIS.get(mid) < arr[i]) {
                        start = mid + 1;
                    } else {
                        end = mid;
                    }
                }

                LIS.set(start, arr[i]);
            }

        }

        System.out.println(lengthOfLIS);
    }
}
