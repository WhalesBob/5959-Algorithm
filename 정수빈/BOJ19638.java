import java.util.*;
import java.io.*;

public class BOJ19638 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 인구 수
        long H = Integer.parseInt(st.nextToken()); // 센티 키
        int T = Integer.parseInt(st.nextToken()); // 망치 횟수 제한

        Queue<Integer> q = new PriorityQueue<>((o1,o2)->o2-o1);

        for (int i = 0; i < N; i++)
            q.add(Integer.parseInt(br.readLine()));

        // 가장 키가 큰 거인 가운데 하나를 때린다
        // 키가 1/2배 된다

        int count = 0;
        int result = 1;
        while(!q.isEmpty()) {
            int n = q.poll();
            result = n;

            if (n < H || T == count || n==1) break;

            int newn = n / 2;

            q.add(newn);
            count += 1;
        }

        if (result < H) {
            System.out.println("YES");
            System.out.println(count);
        } else {
            System.out.println("NO");
            System.out.println(result);
        }
    }
}
