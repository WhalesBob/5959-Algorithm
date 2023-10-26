import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1535 {

    static int N;
    static int[] cost, joy, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        joy = new int[N + 1];
        dp = new int[101]; // 체력이 idx일 때 얻을 수 있는 최대 기쁨

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            joy[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=N;i++) { // i번째 사람과
            for(int j=1;j<=100;j++) { // 체력이 j일 때 악수
                if(j-cost[i]>0){
                    dp[j-cost[i]]=Math.max(dp[j-cost[i]],dp[j]+joy[i]);
                }
            }
        }

        int ans=0;
        for(int i=1;i<=100;i++){
            ans=Math.max(ans,dp[i]);
        }
        System.out.println(ans);
    }
}