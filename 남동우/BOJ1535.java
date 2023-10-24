import java.io.*;
import java.util.StringTokenizer;

public class BOJ1535 {
	static int max = 0;
	static int[] loseHpArray;
	static int[] joyArray;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int personCount = Integer.parseInt(br.readLine());
		loseHpArray = makeArray(br, personCount);
		joyArray = makeArray(br, personCount);
    // 입력으로 사람의 수, 잃는 체력 배열, 얻는 기쁨 배열을 불러옵니다.
		
		dfs(100, 0, 0);
    // dfs 를 순회하며, 얻을 수 있는 최대 기쁨을 업데이트합니다.
    // N의 최대값은 20이고, 2^20 이 100만 정도의 숫자이기 때문에, 완전탐색을 해도 좋다는 판단이 들었습니다.
		System.out.println(max);
    // 얻을 수 있는 최대 기쁨을 출력합니다.
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
  
  // 완전탐색을 진행하며, 얻을 수 있는 최대 기쁨을 가져오기 위한 dfs 입니다. 
	static void dfs(int hp, int joy, int index) {
    // 보고 있는 index 가 사람 숫자 인덱스의 끝에 도착했으면, max 값을 업데이트하고 return 합니다.
		if(index == joyArray.length) {
			if(joy > max) {
				max = joy;
			}
			
			return;
		}

    // hp 가 현재 보고 있는 인덱스의 잃는 체력보다 클 때, 해당 사람에게 인사하고 dfs를 순회합니다.
    // hp == loseHpArray[index] 일 때에는, 세준이가 죽기 때문에, 같을 때는 포함하지 않습니다.
		if(hp > loseHpArray[index]) {
			dfs(hp - loseHpArray[index], joy + joyArray[index], index + 1);
		}

    // 해당 index 의 사람에게 인사하지 않고, dfs를 순회합니다.
		dfs(hp, joy, index + 1);
	}
}
