import java.io.*;
import java.util.*;

public class BOJ20006 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int playerCount = Integer.parseInt(st.nextToken());
		int maxPeople = Integer.parseInt(st.nextToken());
		
		List<Room> roomList = new ArrayList<>();
		Person[] personArray = makeArray(br, playerCount); // 사람 정보에 대한 입력을 받아옵니다.
		
		out:for(Person person : personArray) {
			for(Room room : roomList) { 
        // room 이 하나도 없다면, 자동으로 통과할 것이고, roomList 에 room 이 존재한다면
        // 해당 person 이 room 에 들어갈 수 있는지 if 문을 통해 점검합니다
        // 들어갈 수 있다면, 해당 room 에 person 을 집어넣고, 바깥의 for 문을 다시 순회합니다(continue)
				if(room.set.size() < maxPeople && Math.abs(room.startLevel - person.level) <= 10) {
					room.set.add(person);
					continue out;
				}
			}
      // 위의 if 문에 걸리지 않았다면, 새로운 room 을 개설합니다. 
      // Room() 생성자 안에, 처음 만든 사람의 정보를 가지고 세팅 레발과 Set 을 만들어 줍니다.
			roomList.add(new Room(person));
		}
		
		for(Room room : roomList) {
      // 위의 for 문에서 만들어진 roomList 의 내용을 가지고 출력문을 준비합니다.
      // TreeSet 을 이용하였고, Person 이 Comparable interface 를 구현하였기에, 이름 오름차순으로 자동 정렬되었습니다.
      // room 정원이 가득 찼다면 Started!, 아니라면 Waiting! 을 출력하고, 
      // 이름 순으로 레벨과 이름을 출력하도록 했습니다.
			bw.write(room.set.size() == maxPeople ? "Started!\n" : "Waiting!\n");
			for(Person p : room.set) {
				bw.write(p.level + " " + p.name + "\n");
			}
		}

    // 결과를 출력합니다.
		bw.flush();
	}
	static Person[] makeArray(BufferedReader br, int size) throws IOException {
		Person[] array = new Person[size];
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int level = Integer.parseInt(st.nextToken());
			String name = st.nextToken().trim();
			
			array[i] = new Person(level, name);
		}
		
		return array;
	}
	static class Person implements Comparable<Person>{
		int level;
		String name;
		
		public Person(int level, String name) {
			this.level = level;
			this.name = name;
		}

		@Override
		public int compareTo(Person o) {
			return this.name.compareTo(o.name);
		}
	}
	static class Room{
		int startLevel;
		Set<Person> set;
		
		public Room(Person person) {
			this.startLevel = person.level;
			set = new TreeSet<>(Arrays.asList(person));
		}	
	}
}
