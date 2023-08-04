import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ17615 {

	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		char[] balls=br.readLine().toCharArray();
		ArrayList<Integer> arr=new ArrayList<>();

		int cnt=1;
		char cur=balls[0];

    // ArrayList에 볼 뭉탱이 갯수를 저장
		for(int i=1;i<N;i++) {
			if(cur==balls[i]) {
				cnt++;
			}else {
				arr.add(cnt);
				cur=balls[i];
				cnt=1;
			}
		}
		arr.add(cnt);

    // 뭉탱이 크기가 1개 또는 2개면 0으로 출력하고 RETURN
		if(arr.size()==1||arr.size()==2) {
			System.out.println(0);
			return;
		}
		
		if(arr.size()%2==1) { // 뭉탱이 갯수가 홀수일 때
			// 1번 (ArrayList 인덱스 짝수번째 총합) - (맨앞 / 맨뒤중에 더 큰거) 
			int sum=0;
			for(int i=0;i<=arr.size()-1;i+=2) {
				sum+=arr.get(i);
			}
			sum=sum-Math.max(arr.get(0), arr.get(arr.size()-1));
			
			// 2번 (ArrayList 인덱스 홀수번째 총합)
			int sum2=0;
			for(int i=1;i<=arr.size()-1;i+=2) {
				sum2+=arr.get(i);
			}
      // 더 큰게 정답
			System.out.println(Math.min(sum, sum2));
			
		}else if(arr.size()%2==0) { // 뭉탱이 갯수가 짝수일 때
			// 1번 (ArrayList 인덱스 짝수번째 총합) - (맨 앞)
			int sum=0;
			for(int i=0;i<=arr.size()-1;i+=2) {
				sum+=arr.get(i);
			}
			sum=sum-arr.get(0);
			// 2번 (ArrayList 인덱스 홀수번째 총합) - (맨 뒤)
			int sum2=0;
			for(int i=1;i<=arr.size()-1;i+=2) {
				sum2+=arr.get(i);
			}
			sum2=sum2-arr.get(arr.size()-1);
      // 더 큰게 정답
			System.out.println(Math.min(sum, sum2));
		}
	}
}
