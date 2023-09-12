import java.io.*;
import java.util.*;

public class BOJ16401 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] array = makeArray(br, n); // 입력을 받아 줍니다. 이때, array 를 정렬해서 가져옵니다.
		
		System.out.println(getMaxLength(array, m)); // 이분탐색을 활용하여 정답을 도출합니다.
	}
	static int getMaxLength(int[] array, int cousinCount) {
		int left = 0, right = array[array.length - 1]; // left, right 를 초기화합니다.
		int answer = 0; // 어떤 정답도 도출되지 않았을 경우, 0이 리턴될 것입니다. answer 를 먼저 0으로 초기화합니다.
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(isAvailable(array, mid, cousinCount)) { // 해당 길이만큼의 과자를 모두에게 줄 수 있으면,
				answer = mid; // answer 에 먼저 mid 를 저장해 줍니다.
				left = mid + 1; // left 는 mid + 1로 만들어 줍니다. 
        // 만약 mid + 1 ~ right 사이에서 조카들에게 같은 길이의 과자를 줄 수 없게 된다면, 마지막으로 저장된
        // answer 가 리턴될 것입니다.
			}else {
        // 해당 길이만큼의 과자를 모두에게 줄 수 없으면, right 를 mid - 1 로 만든 뒤 다시 탐색합니다.
				right = mid - 1;
			}
		}
		
		return answer; // 도출된 answer 를 출력합니다.
	}
	static boolean isAvailable(int[] snacks, int length, int cousinCount) {
		int count = 0; // 과자를 length 개 만큼 준 갯수를 counting 할 것입니다.
		int snackIndex = snacks.length - 1; // 첫 과자는, 가장 긴 snack 을 볼 것입니다.
		int[] copy = snacks.clone(); // 이후의 탐색에 영향을 주지 않도록, 배열을 clone 해서 가져옵니다.
		
		while(count < cousinCount && snackIndex >= 0) { // count 가 이미 조카 수보다 크거나 같으면, 탈출합니다. 
      // snackIndex 가 0 보다 작다면, 이것은 불가능한 케이스이기 때문에 탈출합니다.
			if(copy[snackIndex] >= length) { // 보고 있는 과자 길이가 정해진 과자 길이보다 크다면, count 를 1 늘려 주고, 
        // 해당 과자의 길이에서 length 만큼 줄여 줍니다. 
				count++;
				copy[snackIndex] -= length;
			}else { // 보고 있는 과자 길이가 정해진 과자 길이보다 짧다면, 그것보다 더 짧은 과자를 봅니다.
				snackIndex--;
			}
		}
		
		return cousinCount <= count; 
    // 조카에게 줘야 하는 과자 갯수보다 실제 줄 수 있는 과자가 많거나 같다면 true, 아니면 false가 return 됩니다.
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
    Arrays.sort(array);
		return array;
	}
}
