import java.io.*;
import java.util.*;

public class BOJ1922 {
    static int[] root;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

      // union-find 알고리즘 적용을 위해, 각 node 별 root를 표기하기 위해 root 배열을 만들어 줍니다.
      // 이후, root 를 자기 자신으로 만들어 줍니다.
        root = new int[n+1];
        for(int i = 1; i < root.length; i++){
            root[i] = i;
        }

      // 연결 시 비용을 오름차순으로 정렬하기 위해, PriorityQueue 를 사용해 줍니다. 
      // Node Class 를 아래에다 따로 만들어 주어, 어떻게 정렬하는지 그 기준을 만들어 줍니다.
        PriorityQueue<Node> queue = new PriorityQueue<>();

      // 입력을 받아, Node 객체를 PriorityQueue 에 넣어 줍니다.
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            queue.add(new Node(start, end, value));
        }

      // 최소 비용을 다 더해 주기 위해, sum  을 0 으로 초기화해 줍니다.
        int sum = 0;
      // PriorityQueue 가 비지 않았고, 모두 같은 root를 이미 가지고 있지 않는 경우 while 문을 계속 순회합니다.
        while(!queue.isEmpty() && !isAllSameRoot()) {
            Node node = queue.remove();
          // Queue 안에서 연결 비용이 가장 작은 선을 가지고 옵니다.
          // 이후, start 지점과 end 지점 노드의 root가 같지 않다면, 선을 연결해 주며 
          // 두 점의 root 를 union 을 통해 하나로 만들어 줍니다.

            if(find(node.start) != find(node.end)){
                sum += node.value;
                union(node.start, node.end);
            }
        }
      // 답을 출력합니다.
        System.out.println(sum);
    }
  // 이미 모두 같은 root라면, 더 이상 선을 연결할 필요가 없습니다. root 들을 모두 검사해 줍니다.
    static boolean isAllSameRoot(){
        for(int i = 2; i < root.length; i++){
            if(root[i] != root[i-1]){
                return false;
            }
        }
        return true;
    }
  // union-find 의 find 함수입니다. 
    static int find(int a){
        if(root[a] == a){
            return a;
        }

      // root 가 자기 자신이라면, 자기 자신을 리턴합니다. 
      // 그렇지 않다면, find 를 재귀를 돌며, 가장 위의 root 로 root[a] 를 업데이트 해 줍니다. (경로 압축)
      // 이후, 경로 압축한 root[a] 의 결과를 반환합니다.
        root[a] = find(root[a]);
        return root[a];
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);
      // x,y 두 개의 root를 찾아 줍니다. find 하는 과정에서 새로운 부모가 생겼다면, find() 함수 내에서
      // 경로 압축이 새로 일어날 것입니다.

      // y의 root 를 x로 설정해 줍니다.
        root[y] = x;
    }

  // 입력값을 받기 위해, Node 를 받아 옵니다. 
  // List<Integer> 로 해도 되긴 하는데,, 메모리 초과를 또 겪기 싫어서,, 많이 사용될 것들은
  // Arrays.asList() 를 좀 피해 줄까 합니다 ^^
    static class Node implements Comparable<Node>{
        int start;
        int end;
        int value;

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

      // compareTo 를 Override 합니다. 값으로 오름차순 처리해 주기 위함입니다.
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
