package 정렬.P1931_회의실_배정;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<ClassTime> classTimeArrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/정렬/P1931_회의실_배정/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            classTimeArrayList.add(new ClassTime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(classTimeArrayList);

        //System.out.println(classTimeArrayList);

        int start = 0;
        int end = 0;
        int result = 0;

        for (int i = 0; i < N; i++) {
            if(end <= classTimeArrayList.get(i).start){
                result++;
                start = classTimeArrayList.get(i).start;
                end = classTimeArrayList.get(i).end;
            }
        }

        System.out.println(result);
    }
}

class ClassTime implements Comparable<ClassTime> {
    int start;
    int end;

    public ClassTime(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(ClassTime o) {
        int cmp1 = this.end - o.end;

        if(cmp1 == 0){
            return this.start - o.start;
        }

        return cmp1;
    }

    @Override
    public String toString() {
        return "ClassTime{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
