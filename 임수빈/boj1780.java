import java.io.*;
import java.util.*;

public class boj1780 {

	static int[][] paper;
	static int[] answer = new int[3];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		paper = new int[n][n];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j=0; j<n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cut(0, 0, n);
		for (int a: answer) {
			System.out.println(a);
		}
		
	}
	
	static boolean isSame(int x, int y, int size, int num) {		
		for (int i=x; i<x+size; i++) {
			for (int j=y; j<y+size; j++) {
				if (paper[i][j] != num) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static void cut(int x, int y, int size) {
		int num = paper[x][y];
		
		// 종이가 모두 같은 수로 되어 있는 경우
		if (isSame(x, y, size, num)) {
			// 종이 개수 추가
			answer[num+1]++;
			return;
		}
		
		// 종이가 모두 같은 수로 되어 있지 않은 경우
		size /= 3;
		cut(x, y, size);
		cut(x, y+size, size);
		cut(x, y+2*size, size);
		cut(x+size, y, size);
		cut(x+size, y+size, size);
		cut(x+size, y+2*size, size);
		cut(x+2*size, y, size);
		cut(x+2*size, y+size, size);
		cut(x+2*size, y+2*size, size);
	}
}
