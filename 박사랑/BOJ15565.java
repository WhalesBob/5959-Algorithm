import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15565 {

    static int N, K, ryan = 0, ans = Integer.MAX_VALUE;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int doll = Integer.parseInt(st.nextToken());

            if (doll == 1) { // 라이언 인형
                q.offer(doll);
                ryan++;
                if (ryan == K) { // K개가 됐을 때
                    ans=Math.min(ans,q.size());
                } else if(ryan>K){ // K+1개가 됐을 때
                    q.poll();
                    while(true){ // 다음 라이언을 만날 때까지 큐에서 빼준다
                        int peek=q.peek();
                        if(peek==1){
                            break;
                        }else{
                            q.poll();
                        }
                    }
                    ans=Math.min(ans,q.size());
                }

            } else { // 어피치 인형
                if(ryan>=1) {
                    q.offer(doll);
                }
            }
        }
        ans= ans==Integer.MAX_VALUE? -1:ans;
        System.out.println(ans);
    }
}