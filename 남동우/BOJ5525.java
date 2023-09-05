import java.io.*;

public class BOJ5525 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int size = Integer.parseInt(br.readLine());
		char[] input = br.readLine().trim().toCharArray();
		
		System.out.println(countAnswer(input, n));
	}
	static int countAnswer(char[] input, int n) {
		int count = 0; // 몇 개 있는지 카운트하기 위한 변수 count 를 초기화합니다.
		
		int[] memo = new int[input.length]; // 메모를 통해, O(N) 만에 몇 개 있는지 파악하기 위해, int[] 배열 memo 를 초기화합니다.
		
		for(int i = 0; i < input.length; i++) {
			if(input[i] == 'I') {
        // input[i] 가 'I' 인 것을 기준으로 잡습니다. 'O' 일 때는 넘어갑니다.
				if(i >= 2 && input[i-1] == 'O' && input[i-2] == 'I') { // 바로 앞이 O, 그 앞이 I 일때는, 그 전의 I 였던 memo 에서 +1 합니다.
					memo[i] = memo[i-2] + 1; 
				}else { // 위의 조건이 만족하지 않을 때는, 다시 0으로 초기화합니다.
					memo[i] = 0;
				}
			}
		}
		// memo[i] 가 n 이상인 것만 카운트합니다. 그렇게 해야, 'IOIOI' 와 같은 케이스가 memo[i] >= n 에서 i 를 끝으로 하는 곳에 존재하는 것입니다.
		for(int i = 0; i < memo.length; i++) {
			if(memo[i] >= n) {
				count++;
			}
		}
		return count;
	}
}
