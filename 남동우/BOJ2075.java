import java.io.*;
import java.util.*;

public class BOJ2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] matrix = makeMatrix(br,n); // 2차원 배열을 입력받습니다.
        List<Queue<Integer>> queueList = makeQueueList(matrix); 
      // 2차원 배열에서, Column(세로 줄) 한줄마다 아래 를 먼저 넣고 
      // 위를 나중에 넣는 식으로 Queue 를 만들고, 리스트에 다 넣어 줍니다. 
      
        PriorityQueue<List<Integer>> priorityQueue = priorityQueueInit(queueList);
      // 큐 리스트에서 맨 처음 dequeue 로 뽑아 준 요소들을 가지고 priorityQueue 를 만들어 줍니다.
      // Python 유저들을 기준으로는, Tuple 을 저장한다고 생각하면 편할 것 같습니다. 
      
        int value = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            value = getNumberFromHeap(priorityQueue, queueList); 
          // priorityQueue 에서 제일 큰 수를 뽑아 줍니다. n번 반복하면, n번째 큰 수가 나올 것입니다.
          
        }
        System.out.println(value); 
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < size; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return matrix;
    }
  
    static List<Queue<Integer>> makeQueueList(int[][] matrix){
        List<Queue<Integer>> queueList = new ArrayList<>();
        for(int x = 0; x < matrix[0].length; x++){
            Queue<Integer> queue = new LinkedList<>();
            for(int y = matrix.length - 1; y >= 0; y--){
                queue.add(matrix[y][x]); 
              // (x,y) => (0,0) 부터 (0,n까지) 역순으로 queue에 넣어줍니다. 
              // " 모든 수는 자신의 한 칸 위에 있는 수보다 크다는 것이다 " 라는 특성을 활용하기 위해, 큐에 다 넣어 줍니다. 
              
            }
            queueList.add(queue); // 완성된 Queue 를 List 에 넣어 줍니다.
        }
        return queueList; // 완성된 리스트를 리턴합니다.
    }
    static PriorityQueue<List<Integer>> priorityQueueInit(List<Queue<Integer>> queueList){ 
      // 처음 PriorityQueue 를 초기화해 주는 메소드입니다.
      
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>((a,b) -> Integer.compare(b.get(1),a.get(1)));
      // List<Integer> 의 0번째 요소에는 어떤 queueList 에서 뽑은 숫자인지 그 인덱스를 넣고, 1번째 요소에는 값을 넣어 줄 것입니다.
      // 그래서 Comparator 를 람다식으로 만들어서, 정렬 조건으로 넣어 줍니다.
      
        for(int i = 0; i < queueList.size(); i++){
            priorityQueue.add(Arrays.asList(i, queueList.get(i).remove()));
          // priorityQueue 에, 각 queueList 에서 첫 번째로 빼 준 값과, 해당 큐의 인덱스를 함께 정보를 넣어 줍니다.
          
        }
        return priorityQueue;
    }
    static int getNumberFromHeap(PriorityQueue<List<Integer>> priorityQueue, List<Queue<Integer>> queueList){
        List<Integer> element = priorityQueue.remove();
      // priorityQueue 에서 가장 큰 값을 빼 줍니다.
      
        int removedIndex = element.get(0); // 빼 준 값이 어떤 queue 에서 오는 것인지 확인하기 위해, queue 의 인덱스를 확인합니다.
        if(queueList.get(removedIndex).size() > 0){ // 해당 인덱스의 queue 가 비어있지 않다면,
            Integer toAdd = queueList.get(removedIndex).remove();  
            priorityQueue.add(Arrays.asList(removedIndex, toAdd));

          // 해당 큐에서 다시 요소 하나를 deque 하여, priorityQueue 에 index 정보와 함께 넣어 줍니다.
        }
        return element.get(1);

      // 맨 처음 빼 주었던 숫자를 리턴합니다.
    }
}
