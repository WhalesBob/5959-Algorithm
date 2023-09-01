import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
    if(n == 1){ // n이 1일 때는, 1 이하인 소수가 존재하지 않으므로, 0을 출력하고 프로그램을 끝냅니다.
			System.out.println(0);
			return;
		}
		
		List<Integer> primeList = getPrimeNumber(n);
    // 해당 숫자 이하의 범위에서 소수 리스트를 미리 구해 줍니다. 
		
		int count = 0; // 소수의 연속합이 가능한 count 를 0으로 초기화 해 줍니다.
		if(primeList.get(primeList.size() - 1) == n) {
			count++; // 단 하나의 "소수의 연속합" 도, 연속합으로 쳐 줍니다. 그래서, 소수 리스트의 맨 마지막이 n 이라면 count 를 하나 늘려 줍니다.
		}
		
		int foundCountValue = 1; // foundCountValue 는, 몇 개 더해서 답을 도출할 것인지에 대해 찾는 것입니다. 일단 1로 초기화 해 줍니다.
		int maxCount = getMaxAddCount(primeList, n); // 2부터 그 위로 쭉 더했을 때, 몇 개를 더해야 n 이상의 수를 뽑는지 구합니다.
    // 2부터 임의의 소수 k까지 더했을 때 n을 넘었다면, 그 위의 갯수를 더해 n 이 나오게 하는 것은 불가능합니다. 그래서, 2부터 maxCount 개 까지의 범위만 고려해 
    // 이진탐색을 수행할 것입니다.
		
		int right = primeList.size() - 1; // right 값을 primeList.size() - 1 로 초기화합니다. 이진탐색을 위한 것입니다.
		boolean found = false;
		while(foundCountValue <= maxCount) {
			int left = 0; // left 값을 언제나 0으로 초기화 해 줍니다.
			if(!found) right = primeList.size() - 1; // 아래에서, boolean found 가 true 로 업데이트 되었다면, 다음 foundCountValue 값을 볼 때는 이전 mid 위의 값을 볼 필요가 없습니다.
      // 그래서 found 가 false 일 때만, 다시 primeList.size() - 1 로 업데이트 해 줄 것입니다.
			foundCountValue++; 
      // 이전 foundCountValue 의 숫자 갯수만큼 더해서 n 이 나오는 케이스가 존재하든, 존재하지 않든, 하나 늘려서 그 다음 케이스를 보는 작업이 필요합니다. 
      // 그래서 바깥의 foundCountValue 를 항상 하나씩 늘려 줍니다. 
			while(left <= right) { // 이진 탐색을 수행하는 영역입니다.
				found = false; // 먼저 found 를 false 로 만들어 주고, mid 값을 구합니다.
				int mid = (left + right) / 2;
				int result = isAvailable(primeList, mid, foundCountValue, n);
				if(result == 0) { // result 가 0 이 나왔다는 것은, (mid - foundCountValue) 부터 mid 까지의 인덱스를 가지고 소수의 합을 구했을 때, n 이 도출되었다는 것입니다.
					count++;  // count를 하나 늘려 주고, right 를 mid -1 로 만들어 줍니다. 그리고 그 다음 foundCountValue 를 찾으러 갑니다.
					right = mid - 1;
					found = true;
					break;
				}else if(result == 1) { // (mid-foundCountValue) ~ mid 까지 모두 더했을 때 n 보다 그 합이 더 크다면, right 를 mid - 1 로 바꿔 줍니다.
					right = mid - 1;
				}else {
					left = mid + 1; // 위의 합보다 n 이 크다면, 더 큰 값에서 비교해야 합니다. left 를 mid + 1 로 바꿔 줍니다.
				}
			}
		}
		System.out.println(count); // 위에서 도출한 count 를 출력합니다.
	}
  // 이진탐색 조건을 도출하는 메소드입니다. 주로 boolean method 를 사용하지만, 지금은 (크다/작다/같다) 를 표시하기 위해, int method 로 만들어 줍니다.
	static int isAvailable(List<Integer> list, int lastIndex, int elementCount, int targetValue) { 
		int sum = 0;
		int count = 0;
		if(lastIndex > list.size() -1) {
			lastIndex = list.size() - 1;
		}
		for(int i = lastIndex; i > lastIndex - elementCount && i >= 0; i--) {
			sum += list.get(i);
			count++;
		}

    // sum 이 targetValue 와 같으며, 더해진 숫자가 elementCount 와 같을 때, 정말 elementCount 개  만큼 더했을때 targetValue 와 같은 합이 나왔다는 것입니다. 0을 리턴합니다.
		if(sum == targetValue && count == elementCount) {
			return 0;
		}
    // 크거나 작을때, 각각 1이나 2를 리턴합니다.
		return sum > targetValue ? 1 : 2;
	}

  // 에라토스테네스의 체 방법을 이용한 소수 구하는 메소드입니다. 인터넷에 치면 맨 위에 나오는 방식으로 구현했습니다.
	static List<Integer> getPrimeNumber(int n){
		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false; isPrime[1] = false;
		
		for(int i = 2; i * i <= n; i++) {
			for(int j = i * 2; j <= n; j += i) {
				isPrime[j] = false;
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i <= n; i++) {
			if(isPrime[i]) {
				list.add(i);
			}
		}
		return list;
	}
  // 위에서, 가장 많이 더해 주는 카운트를 만드는 method 입니다. 이 이상의 갯수로 소수를 더해 줄 필요가 없다는 의미이기도 합니다.
	static int getMaxAddCount(List<Integer> list, int targetValue) {
		int sum = 0;
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i);
			if(sum > targetValue) {
				return i;
			}
		}
		return list.size() -1;
	}
} 
