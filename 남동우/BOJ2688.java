import java.io.*;
import java.util.Arrays;

public class BOJ2688 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			bw.write(getResult(input) + "\n");
		}
		bw.flush();
	}
  // 정답을 도출하는 메소드입니다.
	static long getResult(int n) {
    // integer 배열로 하면, 오버플로우가 날 것입니다. long 배열로 초기화 해줍니다.
		long[][] dp = new long[n][10];
		if(n == 1) {
			return 10;
		}
  // 2차원 dp 배열을 초기화해 주고, n 이 1일 때 10을 그냥 돌려 줍니다.

		// 첫번째 행을 1로 채웁니다.
		Arrays.fill(dp[0], 1);

    // 0 은 이전 행에서 0~9 열까지의 합산입니다. 
		// 1 은 이전 행에서 1~9 열까지의 합산입니다.
		// 2 는 이전 행에서 1~9 열까지의 합산입니다.
		for(int y = 1; y < n; y++) {
			for(int x = 0; x < 10; x++) {
				for(int k = x; k < 10; k++) {
					dp[y][x] += dp[y-1][k];
				}
			}
		}
    // 마지막 행의 요소를 합산해서 돌려 줍니다.
		return Arrays.stream(dp[n-1]).sum();
	}
}
