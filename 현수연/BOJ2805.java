import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 나무 수와 필요한 나무 길이 입력
		int N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());
		
		// 나무 입력
		st = new StringTokenizer(br.readLine());
		long tree[] = new long[N];
		long maxH = 0; // 나무들 중 제일 긴 값 저장
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			maxH = maxH > tree[i] ? maxH : tree[i];
		}
		
		// 이분탐색
		
		// start부터 end까지가 mid가 될 수 있는 범위
		long start = 0, end = maxH;
		long mid = -1;

		// start가 end보다 커질 경우 탈출
		while (start <= end) {
			long sum = 0;
			mid = (end + start) / 2;
			// 모든 나무에 대해서 자른 후의 sum 값을 구한다
			for (int i = 0; i < N; i++) {
				sum += (tree[i] <= mid ? 0 : tree[i] - mid);
				// 만일 원하는 조건을 이미 충족할 경우, start 갱신 후 for문 break
				if (sum >= M) {
					start = mid + 1;
					break;
				}
			}
			// 모든 나무를 잘랐음에도 조건을 충족하지 못할 경우 end값만 갱신
			if (sum < M) {
				end = mid - 1;
			}
		}
		// 최종적으로 나온 end값이 가장 높게 잘랐을 경우이기 때문에 end 출력
		System.out.println(end);
	}
}