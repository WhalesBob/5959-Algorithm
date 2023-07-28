import java.io.*;
import java.util.*;

public class BOJ18430 {
	static int maxStrength;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,1),
			Arrays.asList(-1,0), Arrays.asList(0,-1), Arrays.asList(1,0), Arrays.asList(0,1));
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		int[][] matrix = makeMatrix(br, height, width);
		maxStrength = 0;
		
		getMaxWeapon(matrix, new boolean[height][width], 0, 0, 0);
		System.out.println(maxStrength);
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix = new int[height][width];
		
		for(int y = 0; y < height; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	static void getMaxWeapon(int[][] matrix, boolean[][] visited, int y, int x, int currentStrength) {
		while(canGo(x,y,matrix)) {
			if(!visited[y][x]) {
				for(int i = 0; i < 4; i++) {
					int weaponSum = getPossibleWeapon(matrix, visited, i, y, x);
					if(weaponSum > 0) {
						getMaxWeapon(matrix, visited, y, x, currentStrength + weaponSum);
						getOffWeaponVisited(matrix,visited, i, y, x);
					}
				}
			}
			y = x + 1 < matrix[0].length ? y : y + 1;
			x = x + 1 < matrix[0].length ? x + 1 : 0;
		}
		
		if(maxStrength < currentStrength) {
			maxStrength = currentStrength;
		}
	}
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
	
	static int getPossibleWeapon(int[][] matrix, boolean[][] visited, int weaponIndex, int y, int x ) {
		List<List<Integer>> weaponPointList = new ArrayList<>();
		weaponPointList.add(Arrays.asList(x,y));
		weaponPointList.add(Arrays.asList(x + direction.get(weaponIndex).get(0), y + direction.get(weaponIndex).get(1)));
		weaponPointList.add(Arrays.asList(x + direction.get(weaponIndex+ 1).get(0), y + direction.get(weaponIndex + 1).get(1)));
		
		for(List<Integer> point : weaponPointList) {
			int pointX = point.get(0);
			int pointY = point.get(1);
			if(!canGo(pointX, pointY, matrix) || visited[pointY][pointX]) {
				return -1;
			}
		}
		
		int sum = 0;
		for(List<Integer> point : weaponPointList) {
			int pointX = point.get(0);
			int pointY = point.get(1);
			sum += matrix[pointY][pointX];
			visited[pointY][pointX] = true;
		}
		
		sum += matrix[y][x];
		return sum;
	}
	static void getOffWeaponVisited(int[][] matrix,boolean[][] visited, int weaponIndex, int y, int x) {
		List<List<Integer>> weaponPointList = new ArrayList<>();
		weaponPointList.add(Arrays.asList(x,y));
		weaponPointList.add(Arrays.asList(x + direction.get(weaponIndex).get(0), y + direction.get(weaponIndex).get(1)));
		weaponPointList.add(Arrays.asList(x + direction.get(weaponIndex+ 1).get(0), y + direction.get(weaponIndex + 1).get(1)));
		
		for(List<Integer> point : weaponPointList) {
			int pointX = point.get(0);
			int pointY = point.get(1);
			if(canGo(pointX, pointY, matrix)) {
				visited[pointY][pointX] = false;
			}
		}
	}
}
