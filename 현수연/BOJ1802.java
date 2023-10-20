import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1802 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 테스트 케이스
		for(int test_case=0;test_case<T;test_case++) {
			char in[] = br.readLine().toCharArray(); // OUT IN 입력
			int mid = in.length/2; // 정중앙 index 값
			boolean isPossible= true; // 종이 접기로 가능한지 여부 값
			// 마지막 접기 부분까지 반복
			while(mid>0) {
				// 중앙 부분을 기준으로 양옆이 다른 방향으로 접혔는지 검토
				for(int i=1;i<=mid;i++) {
					// 만일 같은 방향으로 접혔을 경우 종이접기 불가능 및 break
					if(in[mid-i]==in[mid+i]) {
						isPossible=false;
						break;
					}
				}
				// 검토를 마친 후 한 번 접은 다음의 종이접기가 가능한지 검토를 위해 mid값 갱신
				mid/=2;
				// 이미 종이접기가 불가능할 경우 break
				if(!isPossible)
					break;
			}
			// 최종으로 가능여부 출력
			if(isPossible)
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		System.out.println(sb);
	}
}
