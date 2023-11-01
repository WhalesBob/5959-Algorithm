import java.io.*;

public class BOJ1802 {
	static int T;
	static char[] input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			input = br.readLine().toCharArray();
			boolean result = doFold(0, input.length-1); // 동호의 규칙대로 접을 수 있는가
			if(result) {
				sb.append("YES\n");
			}
			else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	// 중심에서 대칭되는 좌,우가 다른지 확인(다른 방향으로 접어야 종이가 포개짐) 
	static boolean doFold(int start, int end) {
		if(start == end) { // 남은 길이가 1
			return true;
		}
		int mid = (start+end) / 2;
		for(int i=start; i<mid; i++) {
			if(input[i] == input[end-i]) {
				return false;
			}
		}
		return doFold(start, mid-1) && doFold(mid+1, end);
	}
}
