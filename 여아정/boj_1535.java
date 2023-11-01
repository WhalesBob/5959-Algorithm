package dodo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1535 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		int[][] input = new int[N + 1][2];
		int[] dp = new int[101];

		for (int j = 0; j < 2; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 100; j >= input[i][0]; j--) {
				//기존값과 현재 넣고자 하는 값의 행복값의 합의 대소를 비교하여 값을 최대로 갱신한다.
				dp[j] = Math.max(dp[j], dp[j - input[i][0]] + input[i][1]);
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < 100; i++) {
			if (dp[i] > max)
				max = dp[i];
		}

		System.out.println(max);
	}

}
