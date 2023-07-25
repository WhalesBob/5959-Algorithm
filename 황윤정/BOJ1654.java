import java.io.*;
import java.util.*;

public class BOJ1654 {
	static int K, N;
	static int[] arr;
	static long result = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int max = Integer.MIN_VALUE;
		arr = new int[K];
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		binarySearch(max);
		bw.write(result+"\n");
		bw.flush();
		bw.close();
	}
	
	static void binarySearch(int max) {
		long low = 1;
		long high = max;
		long mid;
		
		while(low <= high) {
			int cnt = 0;
			mid = (low + high) / 2;
			for(int i=0; i<K; i++) {
				cnt += arr[i] / mid; // 랜선을 mid만큼 자르고 개수 세기
			}
			// 랜선 개수가 N보다 작다면 자르는 길이를 줄인다
			if(cnt < N) {
				high = mid-1;
			}
			// 랜선 개수가 N보다 크거나 같다면 길이를 늘린다
			else {
				low = mid+1;
				result = Math.max(mid, result); // 최대 랜선 길이 갱신
			}
		}
	}
}
