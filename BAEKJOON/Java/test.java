// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

public class test {
    static List<Integer> cardList;
    static boolean[] visited;
    static Set<Integer> set;

    public static int solution(int[] card, int n) {
        // 여기에 코드를 작성해주세요.
        int answer = 0;
        set = new HashSet<>();

        Arrays.sort(card);

        cardList = new ArrayList<>();
        for (int i = 0; i < card.length; i++) {
            cardList.add(card[i]);
        }

        visited = new boolean[card.length];

        for (int i = 0; i < card.length; i++) {
            dfs(i, new ArrayList<>(), 1);
        }

        List<Integer> listlist = new ArrayList<>();

        for(int num : set) {
            listlist.add(num);
        }

        Collections.sort(listlist);

        for (int i = 0; i < listlist.size(); i++) {
            if(listlist.get(i) == n) {
                return i + 1;
            }
        }


        return -1;
    }

    public static void dfs(int index, List<Integer> combi, int depth) {
        // 1. 체크인
        combi.add(cardList.get(index));
        visited[index] = true;

        // 2. 목적지인가?
        if(depth == 4) {
            //System.out.println(combi);
            String str = "";
            for(int num : combi) {
                str += num;
            }
            set.add(Integer.parseInt(str));
        } else {
            // 3. 연결된 곳 순회
            for(int i = 0 ; i < cardList.size() ; i++) {
                // 4. 갈 수 있는가?
                if(visited[i] == false) {
                    // 5. 간다.
                    dfs(i,combi, depth + 1);
                }
            }
        }

        // 6. 체크아웃
        combi.remove(depth - 1);
        visited[index] = false;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        int card1[] = {1, 2, 1, 3};
        int n1 = 1312;
        int ret1 = solution(card1, n1);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        int card2[] = {1, 1, 1, 2};
        int n2 = 1122;
        int ret2 = solution(card2, n2);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }
}