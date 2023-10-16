import java.io.*;
import java.util.*;

public class BOJ2805 {
	static int N, M, result = Integer.MAX_VALUE; // 나무 개수, 모아야하는 나무 양, 자르는 높이
	static long amount = Integer.MAX_VALUE; // 나무 자르고 모은 양
	static int[] arr; // 나무 입력값
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		getTree();
		if(amount == Integer.MAX_VALUE) { // 나무를 모으지 못한 경우
			System.out.println(0);
		}
		else { // 나무를 모은 경우
			System.out.println(result);
		}
	}

	// 이분탐색을 이용하여 가능한 가장 큰 나무 높이 구하기
	static void getTree() {
		int low = 1; // 자르는 최저 높이
		int high = arr[N-1]; // 자르는 최고 높이
		int mid = 0; // 자르는 나무 높이

		while(low <= high) {
			mid = (int)((low+(long)high)/2);

			long sum = 0; // 나무 자른 양
			for(int i=0; i<N; i++) {
				int tmp = arr[i] - mid; // 나무 자르기
				if(tmp > 0) {
					sum += tmp;
				}
			}
			if(sum >= M) { // 자른 나무가 M미터 이상이고
				if(sum < amount) { // 이전에 모은 양보다 작다면
					result = mid; // 갱신
					amount = sum;
				}
				low = mid+1; // 나무를 더 적게 모으기
			}
			else {
				high = mid-1; // 나무를 더 많이 모으기
			}
		}
	}
}