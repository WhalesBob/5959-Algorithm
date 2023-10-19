import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_19638 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        // 거인을 키 순서대로 정렬
        PriorityQueue<Integer> heights = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i=0; i<n; i++)
            heights.offer(Integer.parseInt(br.readLine()));

        int used = 0; // 뿅망치를 사용한 횟수

        while(used < t){
            int tallest = heights.peek();

            // 제일 큰 거인이 센티보다 작은 경우 - 모든 거인이 센티보다 작음
            // 가장 큰 거인이 1이라면, 센티가 더 커질 수 있는 경우가 없음
            if (tallest < h || tallest == 1) break;

            // 제일 큰 거인이 센티보다 크거나 같으면, 거인의 키를 반으로 나누어 큐에 다시 넣는다.
            heights.offer(heights.poll()/2);
            ++used; // 뿅망치 사용횟수 증가

        }

        System.out.println(h > heights.peek() ? "YES\n"+used : "NO\n"+heights.poll());

    }
}
