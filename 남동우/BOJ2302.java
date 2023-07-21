import java.io.*;

public class Main {
	static int[] pivonacciArray;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int vipCase = Integer.parseInt(br.readLine());
		int[] vipChair = makeVipCase(br, vipCase);
		initPivonacci();
		
		System.out.println(getCaseAnswer(vipChair,n));
	}
	static void initPivonacci() {
		pivonacciArray = new int[41];
		pivonacciArray[0] = 1;
		pivonacciArray[1] = 1;
		for(int i = 2; i <= 40; i++) {
			pivonacciArray[i] = pivonacciArray[i-1] + pivonacciArray[i-2];
		}
	}

	static int[] makeVipCase(BufferedReader br,int n) throws IOException {
		int[] array = new int[n];
		for(int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		return array;
	}
	static int getCaseAnswer(int[] vipChair, int n) {
		int answer = 1;
		
		for(int i = 0; i <= vipChair.length; i++) {
			int start = i > 0 ? vipChair[i-1] + 1 : 1;
			int end = i < vipChair.length ? vipChair[i] - 1 : n;
			
			answer *= pivonacciArray[1 + end - start];
		}
		return answer;
	}
}
