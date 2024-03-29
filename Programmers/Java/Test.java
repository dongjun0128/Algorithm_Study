import java.util.*;


public class Test {
    static boolean[] visited;
    public static void main(String[] args) {
        //"2022.02.28"   ["A 23"]   ["2020.01.28 A"]   [1]
        ////"2020.01.02", ["A", 1], ["2020.01.02 A"], []
        //System.out.println(solution("2020.01.02",new String [] {"A 1"} , new String[] {"2020.01.02 A"}));

        int[] numbers = {6, 10, 2};
        visited = new boolean[numbers.length];

        for(int i = 0 ; i< numbers.length ; i++) {
            dfs("", 1, i, numbers);
        }
    }

    static public void dfs(String str, int depth, int index, int[] numbers) {
        // 1. 체크 인
        str += Integer.toString(numbers[index]);
        visited[index] = true;

        // 2. 목적지인가?
        if(depth == numbers.length) {
            System.out.println(str);
        } else {
            // 3. 연결된 곳 순회
            for(int i = 0 ; i < numbers.length ; i++) {
                // 4. 갈 수 있는가?
                if(visited[i] == false) {
                    // 5. 간다
                    dfs(str,depth + 1 , i , numbers);
                }
            }
        }

        // 6. 체크아웃
        str = str.substring(0,str.length() - Integer.toString(numbers[index]).length());
        visited[index] = false;
    }

    static public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        StringTokenizer st = new StringTokenizer(today,".");
        ArrayList<Integer> answerList = new ArrayList<>();

        HashMap<String,Integer> term = new HashMap<>();

        int thisYear = Integer.parseInt(st.nextToken());
        int thisMonth = Integer.parseInt(st.nextToken());
        int thisDay = Integer.parseInt(st.nextToken());

        // System.out.println(year);
        // System.out.println(month);
        // System.out.println(day);

        for(String t : terms) {
            StringTokenizer st2 = new StringTokenizer(t);
            term.put(st2.nextToken(),Integer.parseInt(st2.nextToken()));
        }

        int index = 0;

        for(String p : privacies) {
            index++;
            st = new StringTokenizer(p);

            StringTokenizer st2 = new StringTokenizer(st.nextToken(),".");

            int year = Integer.parseInt(st2.nextToken());
            int month = Integer.parseInt(st2.nextToken());
            int day = Integer.parseInt(st2.nextToken());


            int expiration = term.get(st.nextToken());

            month += expiration;
            day -= 1;

            if(day <= 0) {
                month -= 1;
                day += 28;
            }

            if(month <= 0) {
                year -= 1;
                month += 12;
            } else {

                year += month / 12 ;
                month = month % 12;

                if(month <= 0) {
                    year -= 1;
                    month += 12;
                }

            }

            if(thisYear < year) {
                continue;
            } else if(thisYear == year) {
                if(thisMonth < month) {
                    continue;
                } else if(thisMonth == month) {
                    if(thisDay < day) {
                        continue;
                    }
                }
            }
                answerList.add(index);



            System.out.println(year);
            System.out.println(month);
            System.out.println(day);
            System.out.println(expiration);



        }


        System.out.println(answerList);

        return answer;
    }
}
