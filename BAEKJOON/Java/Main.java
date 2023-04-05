import java.util.*;

public class Main {
    static HashMap<Integer, Integer> size = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(solution(new int[] {93, 30, 55} , new int[] {1, 30, 5}));
        System.out.println(solution(new int[] {95, 90, 99, 99, 80, 99} , new int[] {1, 1, 1, 1, 1, 1}));
    }

    static public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        ArrayList<Integer> progressesList = new ArrayList<>();
        ArrayList<Integer> speedsList = new ArrayList<>();
        ArrayList<Integer> answerList = new ArrayList<>();

        for(int p : progresses) {
            progressesList.add(p);
        }

        for(int s : speeds) {
            speedsList.add(s);
        }

        int T = 10;

        while(T-- > 0) {
            int cnt = 0;
            ArrayList<Integer> removeIndex = new ArrayList<>();

            for(int i = 0 ; i < progressesList.size() ; i++) {
                progressesList.set(i,progressesList.get(i) + speedsList.get(i));
            }

            // System.out.println(progressesList);
            // System.out.println(cnt);

            if(progressesList.get(0) >= 100 ) {

                for(int i = 0 ; i < progressesList.size() ; i++) {
                    if(progressesList.get(i) >= 100 ){
                        cnt++;
                        removeIndex.add(i);
                    }
                }



                for(int i = 0 ; i < removeIndex.size() ; i++) {
                    progressesList.remove((int)removeIndex.get(i));
                    speedsList.remove((int)removeIndex.get(i));
                }

                answerList.add(cnt);
            }

            if(progressesList.isEmpty()) break;

        }



        return answer;
    }
}

class Node {
    int x;
    int y;

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
