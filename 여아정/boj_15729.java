package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15729 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] button = new int[N];
		int[] result = new int[N];
		int ans = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			button[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			if (result[i] != button[i]) {// 초기값과 출력하고자 하는 값이 다를경우
				for (int j = i; j < i + 3 && j < N; j++) {// 현재~현재+2만큼 값 변경
					result[j] = result[j] > 0 ? 0 : 1;
				}
				ans++;
			}
		}
		System.out.println(ans);
	}

}
