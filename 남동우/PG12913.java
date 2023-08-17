class Solution {
    int solution(int[][] land) {
      // 문제를 DP로 해결하기 위해, 따로 기록하기 위한 record 이차원 배열을 만들어 줍니다.
      // 이후, 0 번째 행에 있는 값들을 land 에서 record 로 복사해 줍니다.
		int[][] record = new int[land.length][land[0].length];
		for(int i = 0; i < 4; i++) {
			record[0][i] = land[0][i];
		}

      // 이후, 1번째 행부터 끝까지, Bottom-Up 방식으로 DP 를 적용합니다.
      // record[y][x] 는, land[y][x] 값에, 이전의 x 열 값을 제외하고 record[y-1] 1차원 배열에 있는 최대값을 가져오면 됩니다.
		for(int y = 1; y < record.length; y++) {
			for(int x = 0; x < 4; x++) {
				record[y][x] = land[y][x] + findMax(record[y-1], x);
			}
		}

      // 그렇게 해서, 맨 마지막 행의 최대값을 받아와, 리턴합니다.
		int max = -1;
		for(int i = 0; i < 4; i++) {
			if(max < record[record.length - 1][i]) {
				max = record[record.length - 1][i];
			}
		}
		return max;
	}

  // 최대값 찾는 메소드입니다.
	static int findMax(int[] array, int currentX) {
		int max = -1;
		for(int i = 0; i < array.length; i++) {
			if(i != currentX && array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}
