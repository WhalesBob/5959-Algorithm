import java.io.*;
import java.util.*;

public class BOJ1713 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int suggestCount = sc.nextInt();

        PriorityQueue<Person> queue = new PriorityQueue<>();
      // 사진 틀을 PriorityQueue 를 활용해 구현합니다.

        int[] personNumber = new int[suggestCount];
        for(int i = 0; i < personNumber.length; i++){
            personNumber[i] = sc.nextInt();
        }

        Person[] haveArray = new Person[101];
      // 이미 PriorityQueue 에 존재한다는 사실을 Person[] 배열을 통해 알 수 있도록
      // 101 칸의 Person[] 배열을 만듭니다.

        for(int i = 0; i < suggestCount; i++){

            // 1. 먼저, 사진틀에 들어있다면, 해당 학생의 추천을 하나 늘립니다.
            // 2. 사진틀에 들어있지 않은데, 학생 수가 n 이상이라면, queue 에서 하나 뺀 후
            // 추가로 사진을 넣습니다.
            // 3. 그냥 사진이 다 채워지지 않았다면, 그냥 하나 넣습니다.
            int target = personNumber[i]; // 타겟을 받아옵니다.

          // haveArray[target] 이 null 일때, 한번도 target 이 불려진 적이 없다는 의미가 됩니다.
          // haveArray[target].suggestCount 가 0 일 때, target 이 한번 추천인에 들어갔다가 지워졌다는 의미가 됩니다.
          // 둘 다 아닐때, 업데이트해주기 위해, haveArray[target] 을 한번 지워 주었다가 suggestCount 를 하나 증가시키고
          // 다시 넣어 주기 위해 PriorityQueue 에서 지워 줍니다.
            if(haveArray[target] != null && haveArray[target].suggestCount != 0){
                queue.remove(haveArray[target]);
            }else{
                if(queue.size() == n){
                  // 위의 조건에 부합하지 않다는 의미는, PriorityQueue 에 들어 있지 않다는 의미입니다.
                  // PriorityQueue 가 가득 찼다면, 지워지는 우선순위에서 가장 높은 것에 있는 인물을 지워주고,
                  // suggestCount 또한 0으로 만들어 줍니다.
                    Person removedPerson = queue.remove();
                    removedPerson.suggestCount = 0;
                }


              // haveArray[target] 이 null 이라는 의미는, 한번도 아직 불려지지 않았다는 의미입니다.
              // 새로운 Person 을 넣어 줍니다.
                if(haveArray[target] == null){
                    haveArray[target] = new Person(target);
                }
              // 불려진 시간을 i로 만들어 줍니다.
                haveArray[target].time = i;
            }

          // 위에서 지워졌든 아니든, suggestCount 를 1 증가시켜주고, queue 에 넣어 줍니다.
          // 새로 만들어진 것이라면, suggestCount 가 0 으로 초기화됩니다. 그래서 하나 증가시켜 줌으로써 1로 만들어
          // 주고, 이미 추가할 것이라면, 이미 위에서 지워 주었으므로 추천 수를 하나 증가시키고 PriorityQueue
          // 에 다시 넣어 줍니다.
            haveArray[target].suggestCount++;
            queue.add(haveArray[target]);
        }

      // 위의 과정을 끝내고, 사람 데이터만 ArrayList 에 담아 준 후 오름차순으로 정렬합니다.
        List<Integer> result = new ArrayList<>();
        queue.forEach(p -> result.add(p.data));
        Collections.sort(result);

      // 깔끔한 출력을 위해, StringJoiner 를 사용해 문자열을 만들어 주고, 출력합니다.
        StringJoiner joiner = new StringJoiner(" ");
        for(Integer i : result) {
            joiner.add(Integer.toString(i));
        }
        System.out.println(joiner);
    }
  // 사람의 데이터를 표기하고, PriorityQueue 에 넣어 주기 위해 Person class 를 만들어 줍니다.
    static class Person implements Comparable<Person>{
        int data;
        int time;
        int suggestCount;

        public Person(int data) {
            this.data = data;
            this.suggestCount = 0;
        }

        @Override
        public int compareTo(Person o) {
            if(o.suggestCount == this.suggestCount){
                return Integer.compare(this.time, o.time);
            }
            return Integer.compare(this.suggestCount, o.suggestCount);
        }
    }
}
