import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,-1),
			Arrays.asList(1,-1), Arrays.asList(1,0), Arrays.asList(1,1), 
			Arrays.asList(0,1), Arrays.asList(-1, 1), Arrays.asList(-1,0), Arrays.asList(-1,-1));
	
	static Set<Fireball> fireballSet;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int mapLength = Integer.parseInt(st.nextToken());
		int fireballCount = Integer.parseInt(st.nextToken());
		int orderCount = Integer.parseInt(st.nextToken());
    // N, M, K 에 의미를 두어, mapLength, fireballCount, orderCount 로 입력을 받아 줍니다.
		
		fireballSet = new HashSet<>();
    // fireball 을 기준으로 반복문을 돌며 과정을 처리하기 위해, HashSet 을 전역으로 선언해 줍니다.
    
		Set<Fireball>[][] map = makeFireballMap(br, mapLength, fireballCount);
    // 2차원 배열을 모두 Set<Fireball> 로 선언해 주고, 파이어볼 정보를 입력받아 해당 좌표의 Set에 넣어 가져옵니다.
    // 위의 메소드 내에서, fireballSet 에 fireball 들의 정보를 모두 넣어 저장해 줍니다.
    
		System.out.println(getRemainFireballMass(map, orderCount));
    // order 를 반복해서 실행하고, 남은 fireball 질량 정보를 출력합니다.
	}
	static int getRemainFireballMass(Set<Fireball>[][] map, int orderCount) {

    // 어느 좌표에서 fireball 이 중첩해 존재하는지 정보를 담아 주는 set 입니다.
		Set<Integer> duplicateSet = new HashSet<>();
		
		for(int i = 0; i < orderCount; i++) {
      // fireballSet 에 있는 Fireball 을 반복문을 돌면서 처리합니다
      // 먼저 (Set<Fireball>) 타입을 갖고 있는 map[y][x] 에서 ball 을 빼주고, ball.move() 를 수행합니다.
      // Fireball 이라는 클래스 내에, move 로직을 구현해 두었습니다!
			for(Fireball ball : fireballSet) {
				map[ball.y][ball.x].remove(ball);
				ball.move(map.length);
				
			}

      // 새로 Fireball 을 2차원 배열에 배치해 주기 전에, fireball 중첩 좌표 정보 Set 을 비워 줍니다. 
			duplicateSet.clear();

      // 이동한 fireball 들을 2차원 배열에 배치해 줍니다.
      // 해당 좌표의 Set 에 이미 다른 fireball 이 들어 있다면, fireball 중첩좌표 Set 에 해당 좌표를 기록해 줍니다.
      // 이후, 해당 좌표의 Set 에 fireball 을 추가해 줍니다. 
			for(Fireball ball : fireballSet) {
				if(map[ball.y][ball.x].size() >= 1) {
					duplicateSet.add(getKey(ball.x, ball.y));
				}
				
				map[ball.y][ball.x].add(ball);
			}

      // 중첩된 fireball 들을 처리합니다.
			for(Integer key : duplicateSet) {

        // 중첩 좌표 Set<Integer> 에 있는 정수 정보에서, x,y 를 추출합니다.
				int x = key % 1000;
				int y = key / 1000;

        // 홀수 방향이 존재하는지, 짝수 방향이 존재하는지 체크하는 변수를 만들어 주고, 
        // 질량, 스피드의 합계를 만들어 줄 변수를 각각 선언합니다.
        // 몇 개의 fireball 이 카운트되었는지 체크하는 변수도 만들어 줍니다.
				boolean haveOdd = false;
				boolean haveEven = false;
				int massSum = 0;
				int speedSum = 0;
				int count = 0;

        // 해당 좌표에 중첩되어 있는 fireball 을 순회하며 정보를 처리합니다.
				for(Fireball ball : map[y][x]) {

          // 방향이 홀수라면, haveOdd 를 true로, 아니라면 haveEven 을 true로 바꿉니다.
					if(ball.direct % 2 == 1) {
						haveOdd = true;
					}else {
						haveEven = true;
					}

          // 질량 합계와 스피드 합계를 만들어 줍니다.
					massSum += ball.mass;
					speedSum += ball.speed;

          // fireballSet 에서 해당 fireball 자체를 지워 줍니다.
          // 이후, map[y][x] 에서도 지워질 것이지만, ConcurrentModificationException 을 방지하기 위해
          // 해당 for 문에서는 지우지 않습니다.
          // 해당 fireball 을 아예 삭제하고, 새로운 4개의 fireball 을 생성하기 위해 이렇게 처리해 줍니다.
					fireballSet.remove(ball);
					count++; // 처리한 fireball 의 갯수를 세어 줍니다.
				}

        // (Set<Integer>) 타입의 map[y][x] 을 비워 줍니다.
				map[y][x].clear();

        // "질량이 0인 파이어볼은 소멸되어 없어진다." 라는 조항을 만족시키기 위해, 
        // massSum 이 5 이상일 때에만 새로운 fireball 을 생성합니다.
        // 만약, massSum 이 5 미만일 때에는, 새로운 fireball 을 생성하지 않습니다.
        // massSum 이 5 미만일 때에는, 기존의 중첩된 fireball 이 모두 소멸한다고 가정하는 것입니다.
				if(massSum >= 5) {

          // 기존 fireball 들의 direction 을 체크한 정보를 바탕으로, 새로운 fireball 들의 direction 을 생성해 줍니다.
          // 여기서는 시작 지점만 만들어 주고, 아래의 for 문에서 +2 씩 해 주면서 fireball 들의 방향을 넣어 줄 것입니다.
					int newDirection = haveOdd && haveEven ? 1 : 0;

          // 새로운 fireball 을 생성해서, map[y][x] 와 fireballSet 에 넣어 줍니다.
					for(; newDirection < 8; newDirection += 2) {
						Fireball newBall = new Fireball(x, y, massSum / 5, newDirection, speedSum / count);
						map[y][x].add(newBall);
						fireballSet.add(newBall);
					}
				}
			}
		}

    // order 를 주어진 입력만큼 실행한 후, 남아 있는 fireball 들의 질량 합을 만들어 줍니다.
		int sum = 0;
		for(Fireball ball : fireballSet) {
			sum += ball.mass;
		}

    // 계산해 준 fireball 의 질량 합을 리턴합니다.
		return sum;
	}

  // 중복 fireball 이 생기는 좌표를 단순히 int 값으로 변환하기 위해 만든 메소드입니다.
	static int getKey(int x, int y) {
		return 1000 * y + x;
	}

  // Set<fireball> 2차원 배열을 만들어주는 메소드입니다.
  // 2차원 배열에 모두 HashSet 을 선언해 넣어 주고, 입력을 비탕으로 HashSet 에 fireball 들을 넣어 줍니다.
  // 전역 변수 fireballSet 에도 fireball 정보를 넣어주어, 이후 fireball 처리를 용이하게 합니다.
	static Set<Fireball>[][] makeFireballMap(BufferedReader br, int size, int count) throws IOException{
		Set<Fireball>[][] map = new Set[size][size];
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				map[y][x] = new HashSet<>();
			}
		}
		for(int i = 0; i < count; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			int y = Integer.parseInt(st.nextToken()) - 1; // -1 씩 해 줌으로써, 0부터 시작하는 index 에 맞게 적용해 줍니다.
			int x = Integer.parseInt(st.nextToken()) - 1; // 위와 마찬가지입니다.
			int m = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direct = Integer.parseInt(st.nextToken());
			
			Fireball ball = new Fireball(x, y, m, direct, speed);
			map[y][x].add(ball);
			fireballSet.add(ball);
		}
		return map;
	}

  // Fireball 클래스를 정의합니다.
	static class Fireball{
		int x;
		int y;
		int mass;
		int direct;
		int speed;
		
		public Fireball(int x, int y, int mass, int direct, int speed) {
			this.x = x;
			this.y = y;
			this.mass = mass;
			this.direct = direct;
			this.speed = speed;
		}

    // fireball 에 주어진 정보에 따라, x,y 좌표를 변환하는 메소드입니다.
    public void move(int bound) {
        x = Fireball.moveOneAxis(x, direction.get(direct).get(0) * speed, bound);
        y = Fireball.moveOneAxis(y, direction.get(direct).get(1) * speed, bound);
    }

    // 위의 move() 메소드 내부에서 사용되는 로직입니다. 현재 위치, 어디로 얼마나 이동하는지, map의 bound 는 얼마인지 입력받습니다.
    // a가 bound 값을 벗어나는 양수라면, 단순히 나머지 연산을 통해 a를 보정해 주고
    // a가 음수라면, n * bound > -a 에 맞는 n 은 Math.ceil((-1 * a) / bound) 이므로, 구한 n 에 bound 를 곱해 a 에 더해 줍니다. 
    static int moveOneAxis(int a, int togo, int bound){
        a += togo;

        if(a < 0){
            a += (bound * Math.ceil((-1D * a) / (double)bound));
        }else if(a >= bound){
            a %= bound;
        }

        return a;
    }
	}
}
