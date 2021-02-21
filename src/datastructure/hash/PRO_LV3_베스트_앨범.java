package datastructure.hash;

import java.util.*;

public class PRO_LV3_베스트_앨범 {

    public class Genre implements Comparable<Genre> {
        String genre;

        int play;

        public Genre(String genre, int play) {
            this.genre = genre;
            this.play = play;
        }

        @Override
        public int compareTo(Genre g) {
            return g.play - this.play;
        }
    }

    public class Pair implements Comparable<Pair> {
        int play, idx;

        public Pair(int play, int idx) {
            this.play = play;
            this.idx = idx;
        }

        @Override
        public int compareTo(Pair p) {
            return p.play - this.play;
        }
    }


    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, PriorityQueue<Pair>> order = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            if (map.containsKey(genres[i])) {
                map.put(genres[i], map.get(genres[i]) + plays[i]);
                order.get(genres[i]).add(new Pair(plays[i], i));
            } else {
                map.put(genres[i], plays[i]);
                order.put(genres[i], new PriorityQueue<Pair>());
                order.get(genres[i]).add(new Pair(plays[i], i));
            }
        }

        List<Genre> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(new Genre(entry.getKey(), entry.getValue()));
        }

        Collections.sort(list);

        List<Integer> result = new ArrayList<>();

        for (Genre genre : list) {
            PriorityQueue<Pair> pq = order.get(genre);
            int cnt = 0;
            while (!pq.isEmpty()) {
                if (cnt == 2) break;
                result.add(pq.poll().idx);
                cnt += 1;
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}
