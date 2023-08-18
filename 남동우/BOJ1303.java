import java.io.*;
import java.util.*;

public class BOJ1303 {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0),
			Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
	static int B,W,count;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		
		char[][] matrix = makeMatrix(br, height);
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
        // DFS를 순회하며, 이미 체크된 병사는 O로 표기합니다.
        // 한번 DFS 를 순회하고 나면, 한번 뭉쳐 있었던 병사들은 O로 표기되어, 중복으로 더해지지는 않을 것입니다.
				if(matrix[y][x] != 'O') {
          // 미리 어느 팀인지 store 한 뒤, count 는 0으로 초기화합니다. 
					char store = matrix[y][x];
					count = 0;
          // dfs를 순회하면, 뭉쳐 있던 병사들이 모두 O 로 바뀔 것이고, 전역 변수인 count 가 얼마인지 업데이트 될 것입니다.
					dfs(matrix[y][x], matrix, x, y);

          // B라면, 전역 변수 B에 count 의 제곱이 추가될 것이고, W라면, W에 제곱이 추가될 것입니다.
					if(store == 'B') {
						B += Math.pow(count, 2);
					}
					if(store == 'W') {
						W += Math.pow(count, 2);
					}
				}
			}
		}
		System.out.println(W + " " + B);
	}
	static char[][] makeMatrix(BufferedReader br, int height) throws IOException{
		char[][] matrix = new char[height][];
		for(int i = 0; i < matrix.length; i++) {
			matrix[i] = br.readLine().trim().toCharArray();
		}
		return matrix;
	}
  // DFS를 순회하는 메소드입니다. 방문하자마자 count 를 하나 증가시켜 주고, 해당 (x,y) 자리의 표기는 O 가 될 것입니다.
  // 이후, matrix 범위 내에 있으며, 같은 팀일 때 DFS 를 순회할 것입니다.
	static void dfs(char team, char[][] matrix, int x, int y) {
		matrix[y][x] = 'O';
		count++;
		for(int i = 0; i < 4; i++) {
			int newX = x + direction.get(i).get(0);
			int newY = y + direction.get(i).get(1);
			if(canGo(newX, newY, matrix) && matrix[newY][newX] == team) {
				dfs(team, matrix, newX, newY);
			}
		}
	}
  // matrix 범위 내에 있는지 체크하는 메소드입니다.
	static boolean canGo(int x, int y, char[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}
