import java.util.Scanner;

public class BOJ1654 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int K = sc.nextInt();
		int N = sc.nextInt();

		int[] length = new int[K];
		long max = Integer.MIN_VALUE;

		for (int i = 0; i < K; i++) {
			length[i] = sc.nextInt();
			max = Math.max(max, length[i]);
		}

		max += 1;
		long min = 0;


		while (min < max) {

			long mid = (min + max) / 2;

			long cnt = 0;

			for (int i = 0; i < K; i++) {
				cnt += length[i] / mid; // 몇 개의 랜선으로 잘리는 지?
			}

			if (cnt < N) { // 랜선 갯수가 더 적게 나옴 -> 길이를 더 짧게 해야 됨
				max = mid;
			} else { // 랜선 갯수가 같거나 더 많게 나옴
				min = mid + 1;
			}
		}

		System.out.println(min - 1);
	}

}