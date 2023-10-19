package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1802 {
	static char input[];
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			input = br.readLine().toCharArray();
			int size = input.length;
			flag = true;

			chkPaper(0, size - 1);

			if (flag) {
				sb.append("YES");
			} else {
				sb.append("NO");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void chkPaper(int start, int end) {
		if (start == end)
			return;

		int mid = (start + end) / 2;
		if (mid < 1 || mid >= input.length - 1) {
			return;
		}

		for (int i = start; i < mid; i++) {
			if (input[i] == input[end - i]) {
				flag = false;
				return;
			}
		}
		chkPaper(mid + 1, end);
		chkPaper(start, mid - 1);
	}

}
