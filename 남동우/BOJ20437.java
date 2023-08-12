import java.io.*;
import java.util.*;

public class BOJ20437 {
    static int min;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            char[] charArray = br.readLine().trim().toCharArray();
            int k = Integer.parseInt(br.readLine());
            Map<Character, Node> charIndexMap = makeCharMap(charArray, k);
          // 각 문자의 리스트가 담긴 맵을 만들어, 활용해 줍니다. 
          // 문자 숫자가 k개 미만인 문자들은, map 에서 제외해 필요없는 다른 연산을 하지 않도록 만들었습니다.

          // 문자 숫자가 모두 k개 미만이라면, map 에 섞이지 않습니다. 그럴 때는, -1 을 출력해 주고 다음 부분으로 넘어갑니다.
            if(charIndexMap.isEmpty()){
                System.out.println(-1);
                continue;
            }

          // 어차피 조건에서의 최소 길이도, 앞 뒤가 해당 문자로 이루어진 것이 최소 길이입니다. 다를 것이 없기 때문에, 한방에 최대 최소 문자열 길이를 찾아 줍니다.
            getMinimumLength(charIndexMap, k);
          // 구한 최대 최소를 출력합니다.
            System.out.printf("%d %d\n", min, max);
        }
    }
    static void getMinimumLength(Map<Character, Node> map, int k){
        for(char c : map.keySet()){
          // map 안에 들어 있는 문자를 기준으로 확인합니다.
            List<Integer> list = map.get(c).indexList;
            for(int i = 0; i < list.size() - k + 1; i++){
                int length = list.get(i + k - 1)- list.get(i) + 1; // 길이는 k번째 뒤에 있는 인덱스 - 현재 인덱스 + 1 입니다/
                if(min > length){
                    min = length;
                }
                if(max < length){
                    max = length;
                }
              // 최대 최소를 업데이트해 줍니다.
            }
        }
    }

    static Map<Character,Node> makeCharMap(char[] charArray, int k){
        Map<Character, Node> map = new HashMap<>();

      // 맵에다, 문자를 key로 하는 데이터들을 넣어 줍니다.
        for(int i = 0; i < charArray.length; i++){
            char c = charArray[i];
            if(map.containsKey(c)){
                map.get(c).add(i);
              // Node 를 가져와서, 해당 Node 에 i번째 index 를 add 해 주면 자동으로 데이터를 갱신할 수 있게 해 두었습니다.
            }else{
                map.put(c, new Node(c, i));
              // Node 생성자에, 단순히 문자와 해당 문자의 인덱스만 넣어도 관련 데이터들을 생성할 수 있게 해 줍니다.
            }
        }
        Set<Character> deleteSet = new HashSet<>();
        for(Character c : map.keySet()){
            if(map.get(c).count < k){
                deleteSet.add(c);
            }
        }
      // ConcurrentModificationException 을 피하기 위해서, 일단 제외할 문자들을 set 에 넣어 관리해 줍니다.

        for(Character c : deleteSet){
            map.remove(c);
        }
        return map;
      // 이후, deleteSet 에 있는 key 문자들을 모두 map 에서 제외해 주고 반환합니다.
    }

  // 여러 개의 데이터를 한방에 묶어 관리하기로 합니다. 
  // 어차피 이제 Arrays.asList() 로 간결하게 사용하는 것은,,, 무서워서 안할래요 ㅎㅎㅎㅎ 메모리 터질까봐 ㅠ
    static class Node{
        char data;
        int count;
        List<Integer> indexList;

        public Node(char data, int index) {
            this.data = data;
            this.count = 1;
            this.indexList = new ArrayList<>();
            this.indexList.add(index);
        }
      // Node 에 data, index 만 넣어줘도, 필요한 것들을 생성해서 저장하게끔 합니다.

        public void add(int index){
            this.count++;
            this.indexList.add(index);
        }
      // 새로운 인덱스를 해당 Node 에 추가하면, count 를 하나 늘리고, indexList에 추가할 수 있게 합니다.
    }
}
