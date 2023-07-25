import java.io.*;
import java.util.*;

public class boj2302 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력
		
		// 좌석의 개수
		int n = Integer.parseInt(br.readLine());
		// 고정석의 개수
		int m = Integer.parseInt(br.readLine());
		
		// 고정석
		int[] vip = new int[m];
		for (int i=0; i<m; i++) {
			vip[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(vip);
		
		// 연속된 좌석의 개수에 따른 좌석 이동 가능한 경우의 수
		int[] dp = new int[41];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		for (int i=3; i<41; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		int answer = 1;
		
		// 고정석을 기준으로 좌석을 잘라서 이동 가능한 경우의 수를 구한 후 곱한다.
		int start = 1;
		for (int i:vip) {
			if (start != i) {
				answer *= dp[i-start];
			}
			start = i+1;
		}
		
		if (start < n+1) {
			answer *= dp[(n+1)-start];
		}
		
		// 정답 출력
		bw.write(answer + "\n");
		bw.flush();
		bw.close();
	}

}
