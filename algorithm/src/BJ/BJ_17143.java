package BJ;

import java.util.ArrayList;
import java.util.Scanner;

// BJ_17143: ���ÿ� - 19�⵵ ��ݱ� ���ÿ� ����
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
	// ����: �� �� �� ��
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
			// ���
			catchShark(c);
			// �̵��ϰ�
			moveShark();
			// ��Ƹ԰�
			survive();
		}
		
		System.out.println(sum);
	}
	
	private static void catchShark(int c) {
		for(int r= 1; r<=R; r++) {
			if(mat[r][c] != null) { // ����Ʈ 1: col �� �Ѱܼ� �ش� �ٿ��� ���� ����� ��� ���
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
			// ����Ʈ 2: ���Ʒ��θ� ���� ����, �¿�� ���� ������ ����(2 * (end - 1)) --> �ᱹ �ٽ� ���ڸ��� ���� ���� �ӵ���ŭ %�����ϸ� ���� �ݺ��ؾ� �ϴ� �� ����
			int length = 2 * (end -1);
			int loopSize = s.s % length;
			for(int i =0; i<loopSize; i++) {
				// ����Ʈ3: ����� ������ ���������� ���� ������ �ٲ��� �ϴ��� Ȯ��
				if(s.d < 3) { // ���Ϸ� ������ ��
					if((s.d == 1 && s.r == 1) || (s.d == 2 && s.r == R)) {
						s.d = (s.d == 1) ? 2 : 1;
					}
					s.r += dr[s.d - 1];
				}
				else { // �¿�� ������ ��
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
			if(mat[s.r][s.c] == null) { // ������ġ�� �ٸ� �� ������
				mat[s.r][s.c] = s; 
			}
			else { // �ٸ� �� �̹� ������
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

