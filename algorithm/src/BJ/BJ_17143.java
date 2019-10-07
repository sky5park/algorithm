package BJ;

import java.util.ArrayList;
import java.util.Scanner;

// BJ_17143: 낚시왕 - 19년도 상반기 낚시왕 문제
public class BJ_17143 {
	static class Shark {
		int r, c, s, d, z;
		public Shark() {
			
		}
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	static int R, C, M, sum;
	static ArrayList<Shark> sharks = new ArrayList<Shark>();
	static Shark mat[][];
	// 순서: 상 하 우 좌
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		
		mat = new Shark[R+1][C+1];
		for(int i=0; i<M; i++) {
			Shark input = new Shark();
			input.r = sc.nextInt();
			input.c = sc.nextInt();
			input.s = sc.nextInt();
			input.d = sc.nextInt();
			input.z = sc.nextInt();
			sharks.add(input);
			mat[input.r][input.c] = input;
		}
		
		sum = 0;
		
		for(int c =1; c<=C; c++) {
			// 잡고
			catchShark(c);
			// 이동하고
			moveShark();
			// 잡아먹고
			survive();
		}
		
		System.out.println(sum);
	}
	
	private static void catchShark(int c) {
		for(int r= 1; r<=R; r++) {
			if(mat[r][c] != null) { // 포인트 1: col 을 넘겨서 해당 줄에서 가장 가까운 상어 잡기
				sum += mat[r][c].z;
				sharks.remove(mat[r][c]);
				mat[r][c] = null;
				break;
			}
		}
	}
	
	private static void moveShark() {
		for(Shark s: sharks) {
			int end = C;
			if(s.d < 3) {
				end = R;
			}
			// 포인트 2: 위아래인면 행의 개수, 좌우면 열의 개수를 연산(2 * (end - 1)) --> 결국 다시 제자리로 오는 수를 속도만큼 %연산하면 실제 반복해야 하는 수 나옴
			int length = 2 * (end -1);
			int loopSize = s.s % length;
			for(int i =0; i<loopSize; i++) {
				// 포인트3: 상어의 방향이 언제인지에 따라 방향이 바껴야 하는지 확인
				if(s.d < 3) { // 상하로 움직일 때
					if((s.d == 1 && s.r == 1) || (s.d == 2 && s.r == R)) {
						s.d = (s.d == 1) ? 2 : 1;
					}
					s.r += dr[s.d - 1];
				}
				else { // 좌우로 움직일 때
					if((s.d == 3 && s.c == C) || (s.d == 4 && s.c == 1)) {
						s.d = (s.d == 3) ? 4 : 3;
					}
					s.c += dc[s.d - 1];
				}
			}
			
		}
	}
	
	private static void survive() {
		int size = sharks.size();
		mat = new Shark[R+1][C+1];
		for(int i = size -1; i>= 0; i--) {
			Shark s = sharks.get(i);
			if(mat[s.r][s.c] == null) { // 목적위치에 다른 상어가 없으면
				mat[s.r][s.c] = s; 
			}
			else { // 다른 상어가 이미 있으면
				if(s.z > mat[s.r][s.c].z) {
					sharks.remove(mat[s.r][s.c]);
					mat[s.r][s.c] = s;
				}
				else {
					sharks.remove(s);
				}
			}
		}
	}
}

