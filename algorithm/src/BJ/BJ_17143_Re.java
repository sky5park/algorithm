package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_17143_Re {
	static class Shark {
		int r, c, s, d, z;
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	static int R, C, M, cnt;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	static Shark mat[][];
	static ArrayList<Shark> shark = new ArrayList<Shark>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new Shark[R][C];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int r, c, s, d, z;
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken()) - 1;
			z = Integer.parseInt(st.nextToken());
			Shark sh = new Shark(r, c, s, d, z);
			shark.add(sh);
			mat[r][c] = sh;
		}
		cnt = 0;
		for(int i =0; i<C; i++) {
			// 가까운 상어 잡기
			catchShark(i);
			
			// 상어들 이동
			moveShark();
			
			// 중복되는 상어 삭제
			removeShark();
		}
		
		System.out.println(cnt);
	}
	
	private static void catchShark(int c) {
		for(int i=0; i<R; i++) {
			if(mat[i][c] != null) {
				shark.remove(mat[i][c]);
				cnt += mat[i][c].z;
				mat[i][c] = null;
				return;
			}
		}
	}
	
	private static void moveShark() {
		for(Shark s: shark) {
			int end = C;
			if(s.d < 2) {
				end = R;
			}
			int len = 2 * (end -1);
			int loopSize = s.s % len;
			for(int i=0; i<loopSize; i++) {
				int nR = s.r + dr[s.d];
				int nC = s.c + dc[s.d];
				if(nR < 0 || nR >= R || nC < 0 || nC >= C) {
					if(s.d == 0) {
						s.d = 1;
					}
					else if(s.d == 1) {
						s.d = 0;
					}
					else if(s.d == 2) {
						s.d = 3;
					}
					else {
						s.d = 2;
					}
				}
				s.r += dr[s.d];
				s.c += dc[s.d];
			}
		}
	}
	
	private static void removeShark() {
		mat = new Shark[R][C];
		int size = shark.size();
		for(int i=size -1; i>=0; i--) {
			Shark s = shark.get(i);
			if(mat[s.r][s.c] == null) {
				mat[s.r][s.c] = s;
			}
			else {
				if(s.z > mat[s.r][s.c].z) {
					shark.remove(mat[s.r][s.c]);
					mat[s.r][s.c] = s; 
				}
				else {
					shark.remove(s);
				}
			}
		}
	}
}
