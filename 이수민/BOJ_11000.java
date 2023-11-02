import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture implements Comparable<Lecture>{
    int s;
    int t;


    public Lecture(int s, int t) {
        super();
        this.s = s;
        this.t = t;
    }


    @Override
    public int compareTo(Lecture o) {
        // 시작 시간 기준 정렬
        if (this.s == o.s)
            return this.t - o.t;
        else
            return this.s - o.s;
    }


}

public class BOJ_11000 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());

        // 강의 리스트
        ArrayList<Lecture> lectures = new ArrayList<>();
        // 강의실 (현재 강의실의 수업이 끝나는 시간 저장)
        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            lectures.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lectures);

        for(Lecture lecture : lectures) {
            // 강의실의 수업 종료 시간보다 현재 강의의 시작 시간이 더 이르면, 이미 있는 강의실 중 수업을 할 수 있는 강의실이 없다는 의미이기 때문에 새로운 강의실이 필요하다.

            if (!rooms.isEmpty() && rooms.peek() <= lecture.s){
                // 현재 강의실에서 수업할 수 있는 경우 - 강의실 업데이트를 위해 현재 강의실 정보 삭제
                rooms.poll();
            }

            // 강의실 정보 추가
            rooms.add(lecture.t);
        }

        System.out.println(rooms.size());

    }
}
