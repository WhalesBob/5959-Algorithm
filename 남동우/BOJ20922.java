import java.io.*;
import java.util.*;

public class BOJ20922 {
	static PriorityQueue<Element> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] array = makeArray(br, n);
        System.out.println(getMaxLength(array, k)); // 입력값을 받고, 정답을 도출합니다.
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException {
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < size; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        return array;
    }
    static int getMaxLength(int[] array, int maxHave){
        int left = 0, right = 0, maxLength = 1;
        Map<Integer, Element> haveMap = new HashMap<>(); // 가지고 있는 요소를 체크하는 Map 입니다.
        queue = new PriorityQueue<>(); // 가장 많이 들고 있는 값이 무엇인지 체크하는 priorityQueue 입니다.
        putInMap(haveMap, array[0]); // 가지고 있는 map 에 집어넣어 줍니다.

        while(left <= right && right < array.length - 1){ // 투 포인터로 진행합니다. 
            if(canPlus(haveMap, array[right + 1], maxHave)){ // 단순히 left 를 옮기지 않고 right 만 옮길 수 있는지 판단하는 메소드입니다.
                putInMap(haveMap, array[++right]); // right 를 한 칸 옮겨준 뒤, map 에 집어넣어 줍니다.
                if(right - left + 1 > maxLength){
                    maxLength = right - left + 1; // maxLength 보다 right - left + 1 이 크다면, 갱신합니다.
                }
            }else{
                removeInMap(haveMap, array[left++]); // map 에서 가지고 있는 element 의 count 를 1 감소시키며,
              // 그로 인해 가지고 있는 다른 정보도 업데이트합니다. 
                putInMap(haveMap, array[++right]); // 슬라이딩 윈도우 방식으로, 그 다음 숫자를 map 에 넣어 줍니다.
            }
        }

        return maxLength; // 길이의 최대값을 돌려줍니다.
    }
    static void putInMap(Map<Integer, Element> haveMap, int number){
        if(haveMap.containsKey(number)){ // map 에서 이미 해당 number 를 가지고 있을 때,
        	Element element = haveMap.get(number);
          element.count++;
          queue.remove(element);
          queue.add(element); // element 를 받아와서 count 를 하나 늘려 주고, 다시 priorityQueue 를 heapify 합니다.
        }else{
        	Element element = new Element(number);
          haveMap.put(number, element);
          queue.add(element); // element 를 생성해 map 과 queue 에 넣어 줍니다.
        }
    }
    static void removeInMap(Map<Integer, Element> haveMap, int number){
    	Element element = haveMap.get(number); 
    	element.count--;
    	queue.remove(element); // number 를 받아 map 내의 element 에 넣어 주고, queue 에서 일단 element 를 삭제합니다.
      // 어차피 element.count == 0 이면, map 에서도 지워줄 것이고, 아니라면 다시 넣어줌으로써 heapify 를 해 줄 것입니다.
    	
    	if(element.count == 0) {
    		haveMap.remove(number);
    	}else {
    		queue.add(element);
    	}
    }
    static boolean canPlus(Map<Integer, Element> haveMap, int number, int limit){
    	if(number == queue.peek().data) { // priorityQueue 의 가장 위에 있는 데이터와 같은지 비교합니다.
    		return queue.peek().count + 1 <= limit; // 같다면, 가질 수 있는 갯수와 queue의 가장 위 count + 1 limit 이하인지 검사합니다.
    	}else {
    		boolean elementLimit = !haveMap.containsKey(number) || haveMap.get(number).count + 1 <= limit;
    		return queue.peek().count <= limit && elementLimit; 
        // number 와 queue 의 맨 위 데이터가 같지 않다면, 이미 queue 의 맨 위 데이터가 limit 를 초과했는지
        // 아니면 새로 추가하는 data 의 count 가 limit 이하인지 같이 검사합니다.
    	}
    }
    static class Element implements Comparable<Element>{
    	int data;
    	int count;
    	
  		public Element(int data) {
  			this.data = data;
  			this.count = 1;
  		}
  
  		@Override
  		public int hashCode() {
  			return data;
  		}
  
  		@Override
  		public boolean equals(Object obj) {
  			return obj instanceof Element && this.data == ((Element)obj).data;
  		}
  
  		@Override
  		public int compareTo(Element o) {
  			return Integer.compare(o.count, this.count);
  		}
    }
}
