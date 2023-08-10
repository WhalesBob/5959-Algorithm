import java.io.*;
import java.util.StringTokenizer;

public class BOJ1080 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] A = makeMatrix(br, n, m);
		int[][] B = makeMatrix(br, n, m);
		// 2차원 배열 2개를 만들어 줍니다.
    
		int maxY = n - 3 + 1;
		int maxX = m - 3 + 1;
		int count = 0;
    // 3 by 3 으로 진행되어야 하기 때문에, 탐색할 범위를 지정하기 위한 maxY, maxX 를 정해 줍니다.
		
		for(int y = 0; y < maxY; y++) {
			for(int x = 0; x< maxX; x++) {
				if(A[y][x] != B[y][x]) {
          // 만약 맨 위 왼쪽 부분의 요소들이 다르다면, 바꿔 주어야 합니다. 바꾸어 줍니다.
          // 이후 바꾼 카운트를 입력해 줍니다.
					changeMatrix(A, x, y);
					count++;
				}
			}
		}
		
		System.out.println(compareTwoMatrix(A, B) ? count : -1);
    // 모든 요소를 고려해 바꾸었다면, 두 개의 2차원 배열을 비교해 줍니다. 
    // 모두 같다면 count 를 출력하며, 하나의 요소라도 다르다면 -1 을 출력해 줍니다.
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			char[] charArray = br.readLine().trim().toCharArray();
			
			for(int x = 0; x < width; x++) {
				matrix[y][x] = charArray[x] - '0';
			}
		}
		return matrix;
	}
	static void changeMatrix(int[][] matrix, int startX, int startY) {
		int endY = Math.min(startY + 3, matrix.length);
		int endX = Math.min(startX + 3, matrix[0].length);
    // endY, endX 를 설정해 줍니다. startX, startX + 3 과 이차원 배열의 길이를 비교해서 더 짧은 것으로 맞추어 줍니다.
		
		for(int y = startY; y < endY; y++) {
			for(int x = startX; x < endX; x++) {
				matrix[y][x] = (matrix[y][x] + 1) % 2;
        // 0 이면 1, 1 이면 0으로 바꿔 주는 코드입니다. 그냥 무조건 + 1 이후 2로 나머지 연산해 주면, 바뀝니다.
			}
		}
	}
  // 두개의 2차원 배열을 비교해 주는 함수입니다.
	static boolean compareTwoMatrix(int[][] matrixA, int[][] matrixB) {
		for(int y = 0; y < matrixA.length; y++) {
			for(int x = 0; x < matrixA[0].length; x++) {
				if(matrixA[y][x] != matrixB[y][x]) {
					return false;
				}
			}
		}
		return true;
	}
}
