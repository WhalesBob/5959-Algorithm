import java.util.Scanner;

public class BOJ1699 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(solution(n));
	}
	static int solution(int n) {
		int[] array = new int[n + 1];
		array[1] = 1; // DP 초기 세팅입니다. 
    // 어차피 1이상의 숫자 n이 주어지고 1은 1의 제곱으로만 만들 수 있기 때문에 1로 세팅합니다.
		
		for(int i = 2; i <= n; i++) { // 2부터 n까지 순차적으로 채워 줍니다.
			int min = i; // 최소값을 그냥 i 로 잡아 줍니다. (1^2) * i 를 넘는 값을 가질 수 없습니다.
			for(int j = 1; j <= Math.sqrt(i); j++) { // i 의 제곱까지 고려해서 제곱수 합의 갯수를 for 문을 순회하며 경우의 수를 봅니다.
				int value = array[i - j * j] + 1; // array[i - j * j] 의 케이스를 모두 보면서 최소값을 찾습니다.
				if(value < min) {
					min = value; // 최소값을 업데이트해 줍니다.
				}
			}
			array[i] = min; // 찾은 제곱수 합의 최소갯수를 입력해 줍니다.
		}
		return array[n]; // 최종적으로 찾은 합의 최소 갯수를 리턴합니다.
	}
}
