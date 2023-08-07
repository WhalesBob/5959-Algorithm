import java.io.*;
import java.util.StringTokenizer;

public class BOJ1011 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			System.out.println(solution(start, end));
		}
	}
	static int solution(int start, int end) {
		int value = Math.abs(end - start);
		int n = (int)Math.ceil(Math.sqrt(value));
		return (value > n * (n-1)) ? 2*n-1 : 2*n-2;
	}
}
