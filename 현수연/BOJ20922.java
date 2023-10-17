import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20922 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수열 길이
		int K = Integer.parseInt(st.nextToken()); // 겹쳐도되는 최대 개수
		int arr[] = new int[N]; // 수열 배열
		int num[] = new int[100001]; // 해당 숫자가 얼마나 있는지 체크용 배열
		int start=0, cnt=0, max=0;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			// 수열 입력
			arr[i] = Integer.parseInt(st.nextToken());
			// 입력받은 수열이 겹쳐도 되는 수를 넘지 않았으면 수열 길이 카운팅
			if(++num[arr[i]]<=K)
				cnt++;
			// 만일 겹쳐도 되는 수를 넘었을 경우
			else {
				// 수열의 첫 수부터 현재 탐색하고 있는 수열의 수까지 탐색
				for(int j=start;j<i;j++) {
					// 체크용 배열과 수열 길이를 빼준 후
					num[arr[j]]--;
					cnt--;
					// 겹쳐도 되는 개수까지 내려왔을 때
					if(num[arr[i]]<=K) {
						// 수열 첫 수 갱신 및 break;
						start=j+1;
						cnt++; // if문을 거치지 못했기 때문에 cnt++ 한 번 더 해주기,,,!
						break;
					}
				}
			}
			max=max>cnt?max:cnt; // 경우에 따라 변한 cnt값을 max값과 비교하여 max값 갱신
		}
		System.out.println(max);
	}
}
