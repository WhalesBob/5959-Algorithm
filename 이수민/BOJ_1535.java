package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1535 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		// 소요하는 체력이 100이 되면 죽는다.
		int[][] dp = new int[N+1][100];
		int[][] p = new int[N+1][2];
		
		for(int i=0; i<2; i++) { // [0]: 잃는 체력 / [1]: 얻는 기쁨
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				p[j][i] = Integer.parseInt(st.nextToken());
			}	
		}

		// 사람들을 순서대로 파악하면서, 현재 체력 소모에서 가장 큰 기쁨을 얻을 수 있는 값을 누적한다.
		for(int i=1; i<=N; i++) {
			for(int j=1; j<100; j++) {
				if (p[i][0] > j) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], p[i][1] + dp[i-1][j-p[i][0]]);
				}
			}
		}

		System.out.println(dp[N][99]);
		
	}
}
