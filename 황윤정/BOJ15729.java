import java.io.*;
import java.util.*;

public class BOJ15729 {
	static int N, result; // N자리수, 눌러야하는 버튼의 최솟값
	static int[] answer; // 쪽지에 적혀있는 상태
	static int[] arr; // 불을 켜고 끌 배열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		answer = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		
		arr = new int[N];
		for(int i=0; i<N; i++) {
			if(arr[i] != answer[i]) { // 현재 상태가 쪽지와 다르다면
				for(int j=i; j<i+3 && j<N; j++) {
					// 현재 자리부터 오른쪽 두 개까지 상태 바꾸기 && 배열인덱스 체크
					if(arr[j] == 0) {
						arr[j] = 1; // 0->1
					}
					else {
						arr[j] = 0; // 1->0
					}
				}
				result++; // 버튼 누른 수 증가
			}
		}
		System.out.println(result);
	}
}
