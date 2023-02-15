package 알고리즘_기초.P1713_후보_추천하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int T;
    static ArrayList<People> pictures;
    static boolean[] isIn;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/알고리즘_기초/P1713_후보_추천하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        pictures = new ArrayList<>();
        isIn = new boolean[101];

        for (int t = 0; t < T; t++) {
            int currentRecommend = Integer.parseInt(st.nextToken());

            if (isIn[currentRecommend] == false) { // 처음 등록이면
                if(pictures.size() >= N){ // 사이즈 초과이면 하나 삭제하고 등록
                    Collections.sort(pictures);

                    isIn[pictures.get(N-1).peopleNum] = false;
                    pictures.remove(N - 1);

                    pictures.add(new People(t,1,currentRecommend));
                    isIn[currentRecommend] = true;
                } else{ // 사이즈가 여유가 있다면 바로 등록
                    pictures.add(new People(t,1,currentRecommend));
                    isIn[currentRecommend] = true;
                }

            } else { // 이미 등록되어 있는 친구면 추천수 +1
                for (int i = 0; i < pictures.size(); i++) {
                    if (pictures.get(i).peopleNum == currentRecommend) {
                        pictures.get(i).setRecommendCnt(pictures.get(i).recommendCnt + 1);
                        break;
                    }
                }
            }
        }

        Collections.sort(pictures, new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return o1.peopleNum - o2.peopleNum;
            }
        });

        for (int i = 0; i < pictures.size(); i++) {
            System.out.print(pictures.get(i).peopleNum + " ");
        }

    }
}

class People implements Comparable<People> {
    int time;
    int recommendCnt;
    int peopleNum;

    public void setRecommendCnt(int recommendCnt) {
        this.recommendCnt = recommendCnt;
    }

    public People(int time, int recommendCnt, int peopleNum) {
        this.time = time;
        this.recommendCnt = recommendCnt;
        this.peopleNum = peopleNum;
    }

    @Override
    public int compareTo(People o) {
        int comp1 = o.recommendCnt - this.recommendCnt;
        if (comp1 == 0) { // 추천수가 같으면
            return o.time - time; // 시간의 내림차순
        } else { //추천수 내림차순
            return comp1;
        }
    }
}
