package 그리디;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 모험가_길드 {
    public static int n;
    public static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        for(int i = 0 ; i < n; i++){
            arrayList.add(scan.nextInt());
        }
        Collections.sort(arrayList);

        int result = 0 ; // 총 그룹의 수
        int count = 0 ; // 현재 그룹에 포함된 모험가의 수

        for(int i = 0 ; i < n; i++){ // 공포도를 낮은 것 부터 하나씩 확인하며
            count += 1; // 현재 그룹에 해당 모험가를 포함시키기
            if(count >= arrayList.get(i)){ // 현재 그룹에 포함된 모험가의 수가 현재의 공포도 이상이라면, 그룹 결성
                result += 1; // 총 그룹의 수 증가시키기
                count = 0; // 현재 그룹에 포함된 모험가의 수 초기화
            }
        }

        System.out.println(result);
    }
}

/*
input
5
2 3 1 2 2
output
2
 */
