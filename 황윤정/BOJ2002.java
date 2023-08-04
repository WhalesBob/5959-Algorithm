import java.io.*;
import java.util.*;

public class BOJ2002 {
	static int N, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> inOrder = new HashMap<>();
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			inOrder.put(br.readLine(),i); // 터널에 들어간 차, 들어온 순서 저장
		}
		int[] outOrder = new int[N];
		for(int i=0; i<N; i++) {
			String car = br.readLine();
			outOrder[i] = inOrder.get(car); // i는 나온 순서
		}
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(outOrder[i] > outOrder[j]) { // 늦게 나온 차들보다 들어온 순서가 늦다면
					result++; // 추월
					break;
				}
			}
		}
		System.out.println(result);
	}
}