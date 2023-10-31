import java.io.*;
import java.util.*;

public class BOJ2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Trie trie = new Trie(); // 트라이(Trie) 자료구조를 만들어 줍니다. 자료구조 내용은 아래에서 설명합니다.
        int max = -1; // 첫 케이스에서 겹치는 접두사가 하나도 없더라도, before/after 에서 업데이트할 수 있도록 max 값을 -1 로 초기화합니다.

        List<String> inputList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            inputList.add(br.readLine().trim());
        }
        // 입력을 받아 줍니다.
      
        trie.insert(inputList.get(0)); // 가장 첫번째 문자열을 트라이 자료구조에 넣어 줍니다.
        String before = "";
        String after = "";
      // 전/후 (S/T) 를 받아 저장할 before,after 를 초기화합니다.

        for(int i = 1; i < inputList.size(); i++){ // 1번째 인덱스부터 순회합니다. 앞에서 0번째 인덱스 문자열을 trie 에 넣었기 때문입니다.
            String input = inputList.get(i); // i번째 인덱스 문자열을 받아옵니다.
            int result = trie.insert(input); 
          // trie 자료구조에 문자열을 집어넣고, 기존에 trie 자료구조 안에 있는 문자열 케이스와 몇번째 접두사까지 맞는지
          // 그 정보를 들고 옵니다.
          
            if(max < result){ 
              // 기존의 max 값보다 input 으로 넣은 공통접두사 길이가 더 길다면
              // max, after 를 현재 값들로 업데이트합니다
              // before 는 trie 에서 해당 input 과 접두사가 겹친 문자열 중 가장 먼저 들어간 문자열을 받습니다. 
              // 해당 로직은, 아래에서 설명합니다.
                max = result; 
                after = input;
                before = trie.getBeforeWord(max, after);
            }else if(max == result){
              // 기존 max 값과 같다면, input 을 기준으로 접두사가 겹친 문자열을 일단 들고옵니다.
                String compare = trie.getBeforeWord(result, input);
              // 들고 온 문자열의 입력순서가, 현재 before 에 등록되어 있는 문자열의 입력순서보다 빠르다면, before, after 를 업데이트합니다.
                if(inputList.indexOf(compare) < inputList.indexOf(before)){
                    before = compare;
                    after = input;
                }
            }
        }

      // 정답을 출력합니다.
        System.out.printf("%s\n%s\n", before, after);

    }
  // Trie 안에 들어갈 Node 를 선언합니다. 
  // n-th tree 를 만들어 줄 것인데, 문자 하나하나를 기준으로 할 것이기 때문에 Map 자료구조를 사용합니다
  // 이때, 입력 순서를 기억해야 하기 때문에, LinkedHashMap 을 사용해 가져와 줍니다.
    static class Node{
        LinkedHashMap<Character, Node> childNode = new LinkedHashMap<>();
    }

  // Trie 자료구조를 정의한 클래스입니다.
    static class Trie{
      // 맨 위에 rootNode 를 만들어, 시작점을 저장해 줍니다.
      // 이전 문자열을 만들어 가져오기 위해, StringBuilder 를 하나 미리 만들어 줍니다.
        Node rootNode = new Node();
        StringBuilder builder = new StringBuilder();

      // 문자열을 trie 자료구조에 입력해 저장하고, 입력 문자열에서 몇 번째 인덱스까지 겹쳤는지 정보를 반환해 주는 메소드입니다.
        int insert(String str){

          // trie 의 root node 부터 탐색을 시작합니다.
            Node node = this.rootNode;
          // 접두사가 겹친 count 를 세기 위해 초기화합니다.
            int count = 0;

          // 입력값을 순회합니다.
            for(int i = 0; i < str.length(); i++){
              // 현재 i 가 입력값의 길이보다 작으며, node 아래의 LinkedHashMap 에 지금 보고자 하는 문자가 포함되어 있다면
              // trie 안에 들어 있는 임의의 문자열과 접두사가 겹치는 것입니다. 
              // count 를 하나 늘려 주고, node 를 해당 노드로 바꿔 줍니다.
                while(i < str.length() && node.childNode.containsKey(str.charAt(i))){
                    count++;
                    node = node.childNode.get(str.charAt(i++));
                }

              // 위의 while 문을 탈출했다면, i 가 str.length() 까지 돌았거나 더 이상 매칭되는 접두사 문자가
              // 없다는 의미입니다. 이때, i 의 값을 한번 체크해 주고, i 가 입력 문자열 끝까지 돌았다면
              // for 문을 탈출해 줍니다.
                if(i >= str.length()){
                    break;
                }
              // i 가 끝까지 돌지 않았다면, 나머지 문자들을 trie 에 입력해 줍니다. 
              // 새로운 node 들을 계속 이어 주며, trie 에 입력해 줍니다.
                node.childNode.put(str.charAt(i), new Node());
                node = node.childNode.get(str.charAt(i));

            }
          // 해당 문자열의 끝을 알리는 문자를 trie 에 넣어 줍니다. 
          // 이후, 접두사가 어디까지 겹쳤는지 숫자를 리턴합니다.
            node.childNode.put('\0', new Node());
            return count;
        }

      // after와 접두사가 가장 많이 겹쳤던 들고 오는 메소드입니다. 
      // n번째까지는 after 를 기준으로 node 를 탐색하며, n 번째 뒤에는 입력 순서가 가장 빠른
      // 문자열을 탐색하여 들고옵니다.
        String getBeforeWord(int n, String after){
          // Trie 안에 있는 StringBuilder 의 문자열을 모두 비워 줍니다.
          // 이후, trie 의 시작 노드부터 탐색을 시작합니다.
            builder.delete(0, builder.length());
            Node node = this.rootNode;

          // 겹쳤던 숫자만큼 문자열을 after와 함께 탐색합니다.
          // n번째 index까지만 겹쳤기 때문에, 해당 부분까지만 after 의 문자열과 함께 탐색하고
          // 그 이후에는 입력 순으로 가장 빠른 문자열을 찾을 것입니다.
            for(int i = 0; i < n; i++){
                node = node.childNode.get(after.charAt(i));
                builder.append(after.charAt(i));
            }

          // 위의 for 문을 순회했다면, 입력 순으로 가장 빠른 문자열을 찾습니다.
          // for 문을 순회할 때, node 를 아래로 탐색해 가며 for 문을 돌아 줍니다.
            for(Node child=node; child.childNode.size() != 0; child = child.childNode.get(findFirst(child.childNode))){
              // LinkedHashMap 에서 가장 먼저 들어온 값을 찾아 firstKey 로 받아옵니다.
              Character firstKey = findFirst(child.childNode);
              // firstKey 값이 표시한 입력 마지막 값이라면, for 문을 탈출합니다.
                if(firstKey == '\0'){
                    break;
                }
              // firstKey 값을 StringBuilder 에 합쳐 줍니다.
                builder.append(firstKey);
            }
              // 합쳐 준 StringBuilder 를 String 으로 바꿔 출력합니다.
            return builder.toString();
        }

      // LinkedHashMap 의 입력 첫번째 문자열을 가지고 오는 메소드입니다.
        static Character findFirst(LinkedHashMap<Character, Node> map){
            return map.keySet().stream().findFirst().orElse(null);
        }
    }
}
