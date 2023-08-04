import java.io.*;
import java.util.*;

public class BOJ2075 {
	static ArrayList<Integer> list = new ArrayList<>();
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				list.add(Integer.parseInt(st.nextToken())); // ArrayList에 입력값 저장
			}
		}
		Collections.sort(list, Collections.reverseOrder()); // ArrayList 내림차순 정렬
		System.out.println(list.get(N-1)); // 인덱스는 0부터 시작이므로 N번째 값은 N-1
	}
}
