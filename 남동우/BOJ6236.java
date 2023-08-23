import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int sum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		sum = 0;
    // n과 m 을 입력받고, sum 을 0으로 초기화 해 줍니다. 
    // sum 은 이후 돈을 입력받을 때 돈들의 합을 저장할 것입니다.
		
		int[] array = makeArray(br, n); // 돈들을 입력받아 1차원 배열에 저장합니다.
		int left = 1, right = sum, answer = left; // 이진 탐색을 위해, left/right/answer 를 저장합니다.
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(isAvailable(array, mid, m)) { 
        // 최소값을 저장하기 위해, mid 만큼 돈을 뽑았을 때 m 번보다 더 작거나 같게 출금했다면
        // answer 에 mid 를 저장해 놓고 right 값을 mid 보다 더 줄여 이진 탐색을 더 시도합니다.
				answer = mid;
				right = mid - 1;
			}else {
        // mid 만큼 돈을 뽑았을 때, m 번보다 더 많이 출금했다면, 돈을 덜 출금한 것입니다. left 를 mid + 1 으로 바꿉니다.
				left = mid + 1;
			}
		}
    // 저장한 정답을 출력합니다.
		System.out.println(answer);
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(br.readLine());
			sum += array[i];
		}
		return array;
	}
  // 이진 탐색을 결정짓는 메소드입니다.
	static boolean isAvailable(int[] array, int takeMoney, int m) {
		int leftMoney = takeMoney;
		int count = 1;
		for(int i = 0; i < array.length; i++) {
			if(array[i] > takeMoney) { // 한 번이라도, 새로 출금하는 금액이 돈의 액수보다 작으면, 무조건 돈을 더 뽑아야 하는 것입니다. 
				return false;
			} 
      // 만약, 이번 회차에 사용해야 할 돈보다 남은 돈이 많거나 같다면, 굳이 더 출금하지 않고 남은 돈을 사용해 줍니다.
			if(array[i] <= leftMoney) {
				leftMoney -= array[i];
			}else { // 이번 회차에 사용해야 할 돈보다 남은 돈이 적다면, 새로 돈을 뽑아 주고, 돈을 뽑은 횟수를 증가시키며
        // 다시 leftMoney 에서 돈을 사용해 줍니다.
				leftMoney = takeMoney;
				count++;
				leftMoney -= array[i];
			}
		}
		// 돈을 뽑은 횟수가, m 번보다 작거나 같은지 확인합니다.
    // 출금 횟수가 m 보다 작다면, 무조건 출금하는 돈을 줄여야 합니다.
    // 만약, 출금 횟수가 m 과 같다고 해도, 위에서 answer 에 저장해 주고, 한번 더 줄여가며 count 가 m 이 되는 더 최소의 경우가 있는지
    // 파악하기 위함입니다.
		return count <= m;
	}
}
