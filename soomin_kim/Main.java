import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		long[] trees = new long[N];
		long max = 0;
		answer = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]);
		}

		// 이분 탐색?
		binarySearch(trees, 0, max, M);
		System.out.println(answer);

	}

	private static void binarySearch(long[] trees, long start, long end, long h) {
			
		long mid = (start + end) / 2;
		
		if(start > end) {
			return;
		}
			
		if (getLen(mid, trees) >= h) {
			answer = Math.max(answer, mid);
			binarySearch(trees, mid + 1, end, h);
			
		}
		else
			binarySearch(trees, start, mid - 1, h);

	}

	private static long getLen(long mid, long[] trees) {

		long sum = 0;

		for (int i = 0; i < trees.length; i++) {
			long current = trees[i];
			if (current > mid)
				sum += current - mid;
		}

		return sum;
	}
}
