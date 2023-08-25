import java.util.*;

public class BOJ1342 {
    static Set<Long> haveSet;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = sc.next().toCharArray(); // 문자열을 입력받습니다.
        int[] convert = new int[input.length]; // 문자열 중복을 비교하기보다는, 숫자 중복을 비교하는게 여차하면 
      // 더 좋습니다. 숫자로 잔머리 썼구나~ 하고 생각해 주길 바랍니다 ^^
        Map<Character, Integer> map = new HashMap<>();
      // 해시맵으로, 문자와 숫자를 매핑합니다. 
        int value = 1;
        haveSet = new HashSet<>(); // 나중에, 행운의 문자열을 숫자화(String -> Long) 해서 저장할 set 입니다.

        for(int i = 0; i < input.length; i++){
            if(!map.containsKey(input[i])){
                map.put(input[i], value++);
            }

            convert[i] = map.get(input[i]);
        }
      // 들어오는 순서대로, 숫자로 바꿔 주기 위함입니다. baab 순으로 들어왔으면 b가 1, a 는 2에 매핑되어
      // baab -> 2112 순으로 숫자 배열로 받아 줍니다. 

      // 문자열 최대 길이는 10입니다. 그런 의미에서, value 가 11 이 되었다는 것은 
      // 문자열 10개가 다 다르다는 의미입니다. 그냥 10! 을 출력할 수 있게 해 줍니다. 
      // 문자열 종류가 9개면, 그냥 permutation 돌아 줄 것입니다.

        if(value == 11){
            System.out.println(3_628_800);
            return;
        }

      // 순열을 만들어, "행운의 문자열" 경우의 수를 구해 줍니다.
        permutation(convert, 0, convert.length, convert.length);
      // haveSet 에는 해당 입력으로 나올 수 있는 행운의 문자열인 경우가 있습니다. 그 size 를 가져와 출력합니다.
        System.out.println(haveSet.size());
    }

  // 순열을 만드는 코드입니다. 순열이 모두 만들어졌을 때, 행운의 문자열이 맞으면
  // 단순히 문자열로 저장하는 것이 아니라, 숫자 배열을 long 으로 변환해
  // 해당 long 값을 저장합니다. 이렇게 하면, 숫자를 저장하는 것이라 메모리 초과도 예방할 수 있습니다.
    static void permutation(int[] arr, int depth, int n, int r){
        if(depth == r){
            if(isLuckString(arr)){
                long value = convertToLong(arr);
                haveSet.add(value);
            }

            return;
        }

        for(int i = depth; i < n; i++){
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }
  // arr에 보이는 숫자 배열을 단순히 모두 이어 붙여 long 형 숫자로 만들어 줍니다. 
  // String 을 계속 생성하면, 메모리 초과가 일어납니다. 그래서, 숫자 연산을 통해서 
  // int[] 배열을 long 으로 변환해 줍니다.
    static long convertToLong(int[] arr){
        long sum = 0;

        for(int i = arr.length - 1; i >= 0; i--){
            sum += (arr[i] * Math.pow(10, arr.length - i));
        }
        return sum;
    }
  // 단순히, "행운의 문자열" 의 조건을 만족하는지 확인하는 것입니다.
    static boolean isLuckString(int[] arr){
        for(int i = 1; i < arr.length - 1; i++){
            if(arr[i-1] == arr[i] || arr[i] == arr[i+1]){
                return false;
            }
        }
        return true;
    }
    static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
