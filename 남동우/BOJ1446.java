import java.io.*;
import java.util.*;

public class BOJ1446 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        Map<Integer, Map<Integer, Integer>> map = makeMap(br, n, d); // 입력으로부터, "유효한" 지름길 을 받아옵니다.
        
        System.out.println(getAnswer(map,d)); // 지름길로부터 도출되는 정답을 출력합니다.
    }
    static int getAnswer(Map<Integer, Map<Integer, Integer>> map, int dest) {
    // distance int array 를 초기화하고, 지름길을 타지 않았다면 가야 하는 distance 정보를 입력해 줍니다.
        int[] distance = new int[dest + 1]; 
        for(int i = 1; i <= dest; i++) {
            distance[i] = i;
        }

    // i = 0 부터 dest 지점까지 순차적으로 탐색해 줍니다. 어차피 d <= 10,000 이기 때문에, 시간초과가 나지 않을 것입니다.
        for(int i = 0; i <= dest; i++) {
            if(i >= 1) {
        // 현재 보고 있는 지점까지 가야 하는 거리가, 이전 지점 + 1 보다 더 멀다면 이전 지점 + 1 으로 업데이트 합니다.
        // 지름길로 인해, 현재 보고 있는 지점이 지름길로 인해 최소거리가 더 짧아졌는지 비교하는 것입니다.
                distance[i] = Math.min(distance[i], distance[i-1] + 1);
            }

      // distance[i] 가 업데이트되고 난 후, 그 지점을 start 로 하는 지름길이 있는지 체크하고 start 뒤의 지점에서의
      // distance 를 업데이트합니다.
            if(map.containsKey(i)) {
                for(Integer toGo : map.get(i).keySet()) {
          // distance[toGo] 의 현재 값과, 지름길로 인해 더 짧아진 값을 비교해 더 최소값을 업데이트 합니다.
                    distance[toGo] = Math.min(distance[toGo], distance[i] + map.get(i).get(toGo));
                }
            }
        }

    // 마지막으로 도출된 distance 최종 지점을 돌려줍니다.
        return distance[dest];
    }

  // start, end, value 를 입력받고, 지름길을 타지 않는 길보다 더 짧으면서 범위 밖으로 나가지 않을 때만 Map 에다 
  // 저장해서 최종적으로 Map 형태로 돌려주는 method 입니다.
    static Map<Integer, Map<Integer, Integer>> makeMap(BufferedReader br, int size, int max) throws IOException{
        Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer,Integer>>();
        
        for(int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
          // start, end, value 를 입력받습니다.

          // 도착 지점보다 end 가 더 뒤에 있거나, 지름길을 타는것보다 원래 길로 가는 것이 더 빠르다면,
          // Map 에다 저장하지 않습니다.
            if(end > max || value >= (end - start)) {
                continue;
            }

          // map 이 start 를 key 로 가지지 않을 때, start 를 key 로, value 를 HashMap 으로 하는
          // 구성을 map 에 넣어 줍니다.
            if(!map.containsKey(start)) {
                map.put(start, new HashMap<>());
            }

          // start 를 key 로 하는 hashMap 이 end 를 포함하고 있지 않거나, 포함하고 있더라도
          // 가지고 있는 값보다 value 가 더 작다면 start-end 를 키로 하는 값을 업데이트해 줍니다.
            if(!map.get(start).containsKey(end) || map.get(start).get(end) > value) {
                map.get(start).put(end, value);
            }
        }

        return map;
    }
}
