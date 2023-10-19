import java.util.*;
import java.io.*;
public class BOJ_16401 {
	static long[] len;
	static long max = 0;
	static int m, n;
	public static void main(String[] args) throws IOException {
		
		
		//입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		len = new long[n];
		for(int i = 0; i<n; i++) {
			len[i] = Integer.parseInt(st.nextToken());
		}
		
		//이분탐색으로 최대 막대과자 찾기
		findMax(1, 1000000000);
		
		//출력
		System.out.println(max);
	}
	
	public static void findMax(long start, long end) {
		
		if(start > end) return; // 이분탐색 종료 조건
		
		long mid = (start + end) / 2;
		
		if(isPossible(mid)) { // 나눠줄 수 있음 그럼 좀더 큰 길이도 가능하지 않을까?
			findMax(mid+1, end);
			max = Math.max(max, mid);
		}
		else { //안돼!!
			findMax(start, mid-1);// 값 줄여서 다시 ㄱㄱ
		}
		
	}
	
	public static boolean isPossible(long snack) {
		
		int count = 0; // 몇명한테 나눠줄 수 있는지 count하는 변수
		for(int i = 0; i<n; i++) {
			count += len[i] / snack; // 몫이 있다면 몫만큼 나눠줄 수 있다.
			if(count >= m) // 시간초과방지를 위해서 가능한지 판단이되면 바로 true 출력
				return true;
		}
		return false;
	}
}
