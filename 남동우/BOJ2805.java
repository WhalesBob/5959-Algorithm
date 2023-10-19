import java.io.*;
import java.util.StringTokenizer;

public class BOJ2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] array = makeArray(br, n);
		System.out.println(getMaximumHeight(array, m)); // 입력을 받아오고, 이분탐색을 통해 설정할 수 있는 가장 높은
    // 목표 height 를 출력합니다.
	}
	static int getMaximumHeight(int[] array, int needLength) {
		int left = 0, right = getMax(array); // left, right 를 설정합니다. right 는 array 길이 중 가장 큰 값을 가져옵니다.
		int answer = 0;

    // 아래는 이분 탐색을 수행하는 코드입니다.
		while(left <= right) {
			int mid = (left + right) / 2;
			if(isAvailable(array, mid, needLength)) { 
				answer = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
    // 이분 탐색을 통해 가지고 오는 답을 출력합니다.
		return answer;
	}

  // 이분 탐색에서의 조건문 메소드입니다.
	static boolean isAvailable(int[] array, int targetHeight, int need) {
		long sum = 0; // 합산이 integer 범위를 넘을 수 있어 long 으로 설정합니다.
		for(int i = 0; i < array.length; i++) {
			if(array[i] > targetHeight) {
				sum += (array[i] - targetHeight);
			}
		}
    // 문제 조건에 따라 sum 을 다 더한 후, need 와 비교해 true/false 를 반환합니다.
		
		return sum >= need;
	}
	static int getMax(int[] array) {
		int max = -1;
		for(int i = 0; i < array.length; i++) {
			if(max < array[i]) {
				max = array[i];
			}
		}
		return max;
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		return array;
	}
}
