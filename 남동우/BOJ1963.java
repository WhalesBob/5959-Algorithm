import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class BOJ1963 {
	static final int INFINITY = 999_999_999;
	static Set<Integer> primeNumberSet;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
    // 먼저 첫 줄에 test case 의 숫자 T를 입력받습니다.
		
		primeNumberSet = makePrimeNumber();
    // 본격적으로 문제 입력을 받기에 앞서, 1000 과 9999 사이의 소수를 모두 모아 전역 변수에 저장합니다.
    // 소수를 HashSet 에 저장하여, 소수 탐색 시 O(1) 만에 소수를 찾을 수 있게 해 줍니다.
		
		for(int test_case = 1; test_case <= t; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
      // 시작 비밀번호와, 목표 비밀번호를 입력받습니다.
			
			System.out.println(getConvertCount(start, end));
      // 몇 번 바꿀 수 있는지 정보를 구해 출력합니다.
		}
	}
	static Set<Integer> makePrimeNumber(){
		boolean[] visited = new boolean[10001];
		for(int i = 2; i < visited.length; i++) {
			visited[i] = true;
		}
		
		for(int i = 2; (i * i) <= 10000; i++) {
			if(visited[i]) {
				for(int j = i * i; j <= 10000; j += i) {
					visited[j] = false;
				}
			}
		}
    // 에라토스테네스의 체 기법을 활용합니다. 
    // 0 과 1을 제외한 나머지 visited 배열에 true 를 먼저 세팅해 줍니다.
    // 이후, 2부터 for 문을 시작해, 제곱이 10000 이 될때까지 에라토스테네스의 체 기법으로 
    // 소수의 제곱수부터 그 이후 소수의 배수의 visited 를 모두 false 로 돌려 줍니다.
    // https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4
    // 위의 링크에서 에라토스테네스의 체 기법을 확인할 수 있습니다.
		
		Set<Integer> primeSet = new HashSet<>();
		Stream.iterate(1000,  a -> a + 1).limit(9000).forEach(x -> {
			if(visited[x]) {
				primeSet.add(x);
			}
		});
    // Stream 을 하나 만들어, 1000 부터 9000번 안쪽으로 반복문을 돌며, visited 배열에서 해당 인덱스가 true 인 경우
    // primeSet 에 해당 숫자를 추가해 줍니다.
    
		return primeSet;
	}
	static int getConvertCount(int start, int end) {
    // 시작점에서 끝 점까지, BFS 순회하며 문제를 해결하는 method 입니다.
    
		Map<Integer, Integer> primeNumberMap = new HashMap<>();
		for(Integer n : primeNumberSet) {
			primeNumberMap.put(n, INFINITY);
		}
    // 9000개 중, 소수가 아닌 수도 존재할 것입니다. 그런것까지 다 넣기보다는, 단순히 primeNumberSet 에 있는
    // 숫자만 map 에 집어넣고, 초기값은 INFINITY 로 업데이트해 줍니다.
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		primeNumberMap.replace(start, 0);
    // BFS 를 위한 queue 를 만들어 주고, start 지점을 먼저 집어넣습니다. 
    // 그리고 start 지점은 0번만에 갈 수 있기 때문에, map 에도 업데이트해 줍니다.
    
		int count = 1; // 몇 번만에 갈 수 있는지 기록하는 count 변수를 초기화해 줍니다.
    
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
      // 해당 term 의 큐 사이즈만큼만 for 문을 돌아야 합니다. 가지고 있는 queue 의 size 만큼 
      // element 를 빼 주면서, 그 element 는 count 만에 갈 수 있다고 표기할 것입니다.
      
			for(int i = 0; i < queueSize; i++) {
				Integer element  = queue.remove();
				Set<Integer> nextPrimeNumber = getNextPrimeNumber(element);
        // element 를 빼 주고, 이 element 에서 한 숫자를 바꾸어 만들 수 있는 소수의 set을 만들어 줍니다.
        
				for(Integer next : nextPrimeNumber) {
					Integer value = primeNumberMap.get(next);
          // 위에서 만들어 준 다음 소수 요소들을 순회하며, 만약에 map 에 있는 기록보다 count 가 더 작다면,
          // 기존의 기록보다 더 빨리 바뀔 수 있다는 의미가 됩니다. 업데이트 해 줍니다.
					if(count < value) {
						primeNumberMap.replace(next, count);
						queue.add(next);
            // 만약에 map 에 있는 기록이 업데이트되었다면, BFS 를 위한 queue 에 해당 소수를 넣어 줍니다.
					}
				}
			}
			
			count++;
      // for 문을 한번 돌 때마다, count 를 하나씩 늘려 줍니다.
		}
		
		return primeNumberMap.get(end);
    // 맨 마지막에 업데이트된 end 가 몇번만에 최종적으로 바뀌었는지 return 합니다.
	}

	static Set<Integer> getNextPrimeNumber(int number){
    // 입력받은 number 를 가지고, 이 number 의 한 자리를 바꾸었을 때 
    // 나올 수 있는 소수의 set을 돌려주는 method 입니다.
   
		Set<Integer> set = new HashSet<>();
		char[] numberArray = Integer.toString(number).toCharArray();
		for(int i = 0; i < 4; i++) {
      // 어차피 4자리이므로, 숫자를 문자로 만든 뒤, char array 의 숫자를 하나씩 바꿔줄 수 있을 것입니다.
      
			char temp = numberArray[i];
      // 나중에 다시 원상복구시키기 위해, temp 에다가 현재 바꾸고자 하는 숫자를 입력해 줍니다.
      
			int convertNum = i == 0 ? 1 : 0; 
      // index 가 0일 때는 1부터 시작, 아닐 때는 0부터 시작해서 숫자를 바꿉니다.
      
			for(;convertNum < 10; convertNum++) {
				int convert = convertedNum(numberArray, convertNum, i);
        // i번째 자리에 convertNum 으로 숫자를 바꿔서 가지고 와 줍니다.
        
				if(primeNumberSet.contains(convert)) {
					set.add(convert);
				}
        // 소수 set에 해당 숫자가 포함되어 있으면, 해당 숫자를 저장합니다.
			}
			numberArray[i] = temp;
      // 0부터 9까지 모두 바꿔 주었으면, 다시 위의 for 문에 영향을 받지 않은 상태에서 다음 자릿수를 바꾸기 위해
      // numberArray[i] 를 원상복구 시켜 줍니다.
		}
		return set;
	}
	static int convertedNum(char[] numberArray,int changeNumber, int index) {
		numberArray[index] = (char)(changeNumber + '0');
    // numberArray 가 char 배열입니다. 그래서, 숫자를 char 형으로 바꿔 저장해 줍니다.
		StringBuilder builder = new StringBuilder();
		for(char c : numberArray) {
			builder.append(c);
    }
		return Integer.parseInt(builder.toString());
    // 바꾼 숫자를 StringBuilder 를 통해 모아 준 뒤, 다시 숫자로 되돌려 줍니다.
	}
}
