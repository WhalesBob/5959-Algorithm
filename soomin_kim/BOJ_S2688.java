package study_0831;

import java.util.*;
import java.io.*;

public class BOJ_S2688 {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringBuilder sb = new StringBuilder();
		 int t = Integer.parseInt(br.readLine());
		
		 long[][] dp = new long[75][75];
		
		 // 조합을 dp로 만들기
		 for(int i = 0; i<dp.length; i++) {
			 dp[i][0] = 1;
			 dp[i][i] = 1;
			 for(int j = 1; j<i; j++) {
				 // nCr = n-1Cr-1 + n-1Cr
				 dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
			 }
			 System.out.println();
		 }
		
		 //testcase
		 for(int testCase = 1; testCase <= t; testCase++) {
			 int n = Integer.parseInt(br.readLine());
			
			 //중복조합
			 sb.append(dp[10+n-1][n]).append("\n");
		 }
		 System.out.println(sb);
	 }
}
