import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N =  Integer.parseInt(br.readLine());
		List<String> start = new LinkedList<>();
		String[] end = new String[N];

    // 터널 이전 차량 번호 저장
		for(int i=0; i<N; i++)
			start.add(br.readLine());

    // 터널 이후 차량 번호 저장
		for(int i=0; i<N; i++)
			end[i] = br.readLine();
    
		int count = 0; // 추월 차량 개수
		int before = 0; // 터널 이전 차량 배열 인덱스

    // 터널 이후 차량 배열 인덱스만큼 모든 차량 번호 확인
		for(int after=0; after<N; after++) {
      // 이전 차량과 번호가 다르면, 이전 차량 정보에서 삭제 및 카운팅 (항상 현재 before 인덱스 이후에 존재)
			if(!start.get(before).equals(end[after])) {
				start.remove(end[after]);
				count += 1;
			}
      // 같으면, 다음 이전 차량과 비교
			else {
				before += 1;
      }
		}
		
		System.out.println(count);
	}
}
