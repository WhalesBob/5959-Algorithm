import java.io.*;
import java.util.*;

public class boj19638 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

        	// 내림차순 정렬
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
            
        });
        
        for (int i=0; i<n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int height;
        int cnt = 0; // 마법의 뿅망치를 사용한 횟수
        while (cnt < t) {
            // 가장 키가 큰 거인 뽑기
        	height = pq.peek();
            
        	// 자신보다 키가 작으면 종료
            if (height < h) {
                break;
            }
            
            // 더이상 키를 줄일 수 없으면 종료
            if (height == 1) {
                break;
            }
            
            // 뿅망치 때리기
            pq.add(pq.poll() / 2);
            cnt++;
        }
        
        height = pq.poll();
        if (height < h) {
            sb.append("YES\n").append(cnt);
        } else {
            sb.append("NO\n").append(height);
        }

        System.out.println(sb);
        
    }

}