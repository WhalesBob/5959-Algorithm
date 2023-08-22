import java.io.*;
import java.util.StringTokenizer;

public class BOJ1890 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] matrix = makeMatrix(br, n); // 입력 2차원 배열을 받아 줍니다.
		long[][] count = new long[matrix.length][matrix[0].length]; // DP 를 이용할 것입니다. 먼저, memoization 을 해 줄 2차원 배열을 만듭니다.
		count[0][0] = 1; // 시작점을 갈 수 있는 경우의 수를 1로 초기화 해 줍니다.
    // 여기에서, "갈 수 있는 경우의 수가 2^64 - 1 보다 작거나 같다" 라고 하였으므로, long 2차원 배열을 이용합니다. 
		System.out.println(getCount(matrix, count)); // 결과값을 출력합니다. 
	}
	static long getCount(int[][] matrix, long[][] count) {
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
        // 2차원 배열을 2중 for 문을 돌며 순회합니다. 
        // matrix[y][x] 가 자주 쓰일 것 같아, value 로 만들어 주고, 값이 0 이라면 다음 값을 순회합니다.
				int value = matrix[y][x];
				if(value == 0) {
					continue;
				}

        // value 가 0이 아니라면, 해당 자리에서 value 를 더한 만큼 오른쪽과 아래로 이동할 수 있습니다.
        // 오른쪽 과 아래 위치가 이차원 배열에서 벗어나는지 아닌지 파악하고, 이차원 배열에서 벗어나지 않는다면
        // count 에 기록해 줍니다.
				if(canGo(x + value, y, matrix)) {
					count[y][x + value] += count[y][x];
				}
				if(canGo(x, y + value, matrix)) {
					count[y + value][x] += count[y][x];
				}
			}
		}
		// 최종적으로 오른쪽 아래의 기록 값을 출력합니다.
		return count[matrix.length - 1][matrix[0].length - 1];
	}
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
	static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
		int[][] matrix = new int[size][size];
		for(int y = 0; y < size; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < size; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
 	}
}
