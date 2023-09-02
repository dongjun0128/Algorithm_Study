package Level_3.베스트앨범;

import java.util.*;

public class Main {
    public static int[] solution(String[] genres, int[] plays) {
        // 1. 속한 노래가 많이 재생된 장르 먼저 수록
        // 2. 장르 내에서 많이 재생된 노래를 먼저 수록
        // 3. 같으면 고유 번호가 낮은 노래를 먼저 수록

        HashMap<String, Integer> cntMap = new HashMap<>();
        HashMap<String, PriorityQueue<Music>> musicMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            cntMap.put(genres[i], cntMap.getOrDefault(genres[i], 0) + plays[i]);
            if (musicMap.get(genres[i]) == null) {
                musicMap.put(genres[i], new PriorityQueue<>());
                musicMap.get(genres[i]).add(new Music(plays[i], i));
            } else {
                musicMap.get(genres[i]).add(new Music(plays[i], i));
            }
        }

        List<Genre> genreList = new ArrayList<>();
        for (String str : cntMap.keySet()) {
            genreList.add(new Genre(str, cntMap.get(str)));
        }

        Collections.sort(genreList);

        System.out.println(genreList);
        List<Integer> answerList = new ArrayList<>();

        for (Genre genre : genreList) {
            if (musicMap.get(genre.genre).size() >= 2) {
                for (int i = 0; i < 2; i++) {
                    answerList.add(musicMap.get(genre.genre).poll().index);
                }
            } else {
                answerList.add(musicMap.get(genre.genre).poll().index);
            }
        }

        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    static class Genre implements Comparable<Genre> {
        String genre;
        int cnt;

        public Genre(String genre, int cnt) {
            this.genre = genre;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Genre o) {
            return o.cnt - this.cnt;
        }

        @Override
        public String toString() {
            return "Genre{" +
                    "genre='" + genre + '\'' +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    static class Music implements Comparable<Music> {
        int play;
        int index;

        public Music(int play, int index) {
            this.play = play;
            this.index = index;
        }

        @Override
        public int compareTo(Music o) {
            int cmp1 = o.play - this.play;

            if (cmp1 == 0) {
                int cmp2 = this.index - o.index;
                return cmp2;
            } else {
                return cmp1;
            }
        }

        @Override
        public String toString() {
            return "Music{" +
                    "play=" + play +
                    ", index=" + index +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}));
    }
}
