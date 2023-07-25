import java.io.*;
import java.util.*;

public class BOJ2784 {
	static boolean isChecked;
	static List<Integer[]> indexMatrix;
	static Set<char[][]> mapList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> inputList = makeInputList(br);
		mapList = new HashSet<>();
		inputList.sort(Comparator.naturalOrder());
		
		dfs(inputList, new boolean[6], 0, 3);
		if(mapList.size() == 0) {
			System.out.println(0);
			return;
		}
		
		char[][] finalMap = null;
		for(char[][] map : mapList) {
			if(finalMap == null) {
				finalMap = map;
			}else {
				finalMap = compareTwoMap(finalMap, map) ? finalMap : map;
			}
		}
		
		printWordMap(finalMap);
	}
	static boolean compareTwoMap(char[][] A, char[][] B) {
		String buildA = makeString(A);
		String buildB = makeString(B);
		return buildA.compareTo(buildB) < 0;
	}
	static String makeString(char[][] map) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++) {
				builder.append(map[i][j]);
			}
		}
		return builder.toString();
	}
	static List<String> makeInputList(BufferedReader br) throws IOException{
		List<String> list = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			list.add(br.readLine().trim());
		}
		
		return list;
	}
	
	static void dfs(List<String> inputList, boolean[] visited, int currentIndex, int count) {
		if(count == 0) {
			isChecked = checkAndPrintIfPositive(inputList, visited);
			return;
		}
		
		for(int i = currentIndex; i < 6; i++) {
			visited[i] = true;
			dfs(inputList, visited, i + 1, count - 1);
			visited[i] = false;
		}
	}
	static boolean checkAndPrintIfPositive(List<String> inputList, boolean[] visited) {
		List<String> falseList = new ArrayList<>();
		List<Integer> indexList = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			if(visited[i]) {
				indexList.add(i);
			}else {
				falseList.add(inputList.get(i));	
			}
		}
		
		indexMatrix = new ArrayList<>();
		setIndexMatrixByPermutation(indexList.toArray(new Integer[0]), 0, 3, 3);
		for(int i = 0; i < indexMatrix.size(); i++) {
			Integer[] indexArray = indexMatrix.get(i);
			char[][] map = new char[3][3];
			setWordMap(map, inputList, indexArray);

			List<String> verticalWord = makeVerticalWord(map, indexArray);
			
			for(String falseWord : falseList) {
				if(verticalWord.contains(falseWord)) {
					verticalWord.remove(falseWord);
				}else {
					break;
				}
			}
			
			if(verticalWord.size() == 0 && !haveThisMap(map)) {
				mapList.add(map);
				return true;
			}
		}
		return false;
	}
	static boolean haveThisMap(char[][] map) {
		for(char[][] haveMap : mapList) {
			if(isSame(haveMap, map)) {
				return true;
			}
		}
		return false;
	}
	static boolean isSame(char[][] A, char[][] B) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(A[i][j] != B[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	static void setIndexMatrixByPermutation(Integer[] arr, int depth, int n, int r){
		if(depth == r) {
			indexMatrix.add(Arrays.stream(arr).toArray(Integer[]::new));
			return;
		}
		
		for(int i = depth; i < n ; i++) {
			swap(arr, depth, i);
			setIndexMatrixByPermutation(arr, depth + 1, n, r);
			swap(arr, depth, i);
		}
	}
	static void swap(Integer[] arr, int depth, int i) {
		int temp = arr[depth];
		arr[depth] = arr[i];
		arr[i] = temp;
	}
	static List<String> makeVerticalWord(char[][] map, Integer[] indexArray){
		List<String> verticalList = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		for(int x = 0; x < 3; x++) {
			builder.delete(0, builder.length());
			for(int y = 0; y < 3; y++) {
				builder.append(map[y][x]);
			}
			verticalList.add(builder.toString());
		}
		return verticalList;
	}
	static void printWordMap(char[][] map) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	static void setWordMap(char[][] map, List<String> wordList, Integer[] indexArray) {
		for(int i = 0; i < 3; i++) {
			char[] wordCharArray = wordList.get(indexArray[i]).toCharArray();
			for(int j = 0; j < 3; j++) {
				map[i][j] = wordCharArray[j];
			}
		}
	}
}
