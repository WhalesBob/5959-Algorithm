import java.io.*;
import java.util.*;

public class BOJ1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++){
            int personCount = Integer.parseInt(br.readLine());
            Person[] personInfo = getPersonInfo(br, personCount); // 신입사원의 정보를 입력으로 받아옵니다.

            Arrays.sort(personInfo); // 서류심사 성적을 기준으로 소팅합니다.
          // "지원자 A의 성적 중 하나가 다른 모든 지원자의 성적보다 좋아야 한다" 라는 조항이 있습니다. 
          // 여기서, 서류심사 석차별로 정렬해 두면, 면접 성적이 앞에 온 성적보다 좋으면 되기 때문에, 
          // 서류심사 석차별로 우선 정렬합니다.
            bw.write(getMaxOfPick(personInfo) + "\n");
        }
        bw.flush();
    }
    static int getMaxOfPick(Person[] personInfo){
        Stack<Person> stack = new Stack<>();
        stack.push(personInfo[0]); // 스택을 만들고, 서류심사 성적 1등을 스택에 푸시합니다.

        for(int personIndex = 1; personIndex < personInfo.length; personIndex++){
            if(stack.peek().faceIndex > personInfo[personIndex].faceIndex){ 
              // 스택 꼭대기에 있는 면접 성적이 다음 올 면접 성적보다 나쁘면
              // 즉, 스택 위에 있는 면접 성적 석차 값이 다음에 올 면접 석차값보다 높으면
              // 해당 지원자 정보를 스택에 넣습니다.
                stack.push(personInfo[personIndex]);
            }
        }
        return stack.size(); // 스택의 사이즈를 리턴합니다.
    }
    static Person[] getPersonInfo(BufferedReader br, int count) throws IOException {
        Person[] people = new Person[count];
        for(int i = 0; i < count; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int paper = Integer.parseInt(st.nextToken());
            int face = Integer.parseInt(st.nextToken());
            people[i] = new Person(paper, face);
        }
        return people;
    }
    static class Person implements Comparable<Person>{
        int paperIndex;
        int faceIndex;

        public Person(int paperIndex, int faceIndex) {
            this.paperIndex = paperIndex;
            this.faceIndex = faceIndex;
        }

        @Override
        public int compareTo(Person o) {
            if(this.paperIndex == o.paperIndex){
                return 0;
            }
            return this.paperIndex > o.paperIndex ? 1 : -1;
        }
    }
}
