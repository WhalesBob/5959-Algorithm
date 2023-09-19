import java.io.*;
import java.util.*;

public class BOJ2512 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] array = makeArray(br, n); // 배열을 입력받습니다.
		
		int totalBudget = Integer.parseInt(br.readLine()); // 가용 가능한 예산 총액을 입력받습니다.
		System.out.println(getMaxOneBudget(array, totalBudget)); // 한 부서당 사용할 수 있는 최대 예산을 출력합니다.
	}
	static int getMaxOneBudget(int[] array, int totalBudget) {
		int left = 1, right = Arrays.stream(array).max().getAsInt(); // left 는 1, right 는 배열 중 가장 큰 값을 할당합니다.
		int answer = left; // answer 를 left 로 초기화합니다.
		
		while(left <= right) { // 이분탐색을 수행합니다.
			int mid = (left + right) / 2;
			if(isAvailable(array, mid, totalBudget)) {
				answer = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
		
		return answer;
	}
	static boolean isAvailable(int[] array, int budget, int totalBudget) {
		int sum = 0; // 해당 최대 예산을 설정했을 때 전체적으로 드는 예산이 얼마가 되는지 확인하기 위해, sum 을 0으로 초기화합니다.
		for(int i = 0; i < array.length; i++) {
			sum += (array[i] >= budget) ? budget : array[i]; // array[i] 가 budget 보다 크면 budget 을 더하고, 아니면 array[i] 를 더합니다.
		}
		
		return sum <= totalBudget; // sum 이 totalBudget 보다 큰지 확인합니다.
	}
	
	static int[] makeArray(BufferedReader br, int size) throws IOException {
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
}
