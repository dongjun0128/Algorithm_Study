import java.util.*;

public class Main {
    static HashMap<Integer, Integer> size = new HashMap<>();

    public static void main(String[] args) {

        HashMap<String,Integer> map = new HashMap<>();

        map.put("aaa",1);
        System.out.println(map);
        map.remove("aaa");
        System.out.println(map);
        map.put("aaa",2);
        System.out.println(map);

        int[] arr = {1, 2, 3, 4};
        test(arr);
        System.out.println("arr = " + Arrays.toString(arr));

//        int[] arr = {1, 3, 5, 7, 8};
//        int sumArr = 8;
//
//        for (int i = 0; i < arr.length; i++) {
//            ArrayList<Integer> list = new ArrayList<>();
//            boolean[] visited = new boolean[arr.length];
//
//            dfs(visited, arr[i], i, sumArr, arr, list, 0);
//        }

        //System.out.println(solution(new int[] {93, 30, 55} , new int[] {1, 30, 5}));
        //System.out.println(solution(new int[] {95, 90, 99, 99, 80, 99} , new int[] {1, 1, 1, 1, 1, 1}));
    }

    static void test(int[] arr) {
        arr[0] = 123;
    }

    static void dfs(boolean[] visited, int sum, int index, int sumArr, int[] arr, ArrayList<Integer> list, int depth) {
        // 1. 체크인
        visited[index] = true;
        list.add(index);

        // 2. 목적지인가?
        if (sum >= sumArr) {
            if (sum == sumArr) {
                System.out.println(list);
            }
        } else {
            // 3. 연결된 곳 순회
            for (int i = index; i < arr.length; i++) {
                if (visited[i] == false) {// 4. 갈 수 있는가?
                    // 5. 간다.
                    dfs(visited, sum + arr[i], i, sumArr, arr, list, depth + 1);
                }
            }
        }

        // 6. 체크아웃
        visited[index] = false;
        list.remove(depth);
    }

    static public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        ArrayList<Integer> progressesList = new ArrayList<>();
        ArrayList<Integer> speedsList = new ArrayList<>();
        ArrayList<Integer> answerList = new ArrayList<>();

        for (int p : progresses) {
            progressesList.add(p);
        }

        for (int s : speeds) {
            speedsList.add(s);
        }

        int T = 10;

        while (T-- > 0) {
            int cnt = 0;
            ArrayList<Integer> removeIndex = new ArrayList<>();

            for (int i = 0; i < progressesList.size(); i++) {
                progressesList.set(i, progressesList.get(i) + speedsList.get(i));
            }

            // System.out.println(progressesList);
            // System.out.println(cnt);

            if (progressesList.get(0) >= 100) {

                for (int i = 0; i < progressesList.size(); i++) {
                    if (progressesList.get(i) >= 100) {
                        cnt++;
                        removeIndex.add(i);
                    }
                }


                for (int i = 0; i < removeIndex.size(); i++) {
                    progressesList.remove((int) removeIndex.get(i));
                    speedsList.remove((int) removeIndex.get(i));
                }

                answerList.add(cnt);
            }

            if (progressesList.isEmpty()) break;

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
