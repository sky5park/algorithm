package BJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BJ_17144: 공기청정기 - 19년도 상반기 공기청정기
public class BJ_17144 {
	static class Dust {
		int r, c, amt;
		public Dust (int r, int c, int amt) {
			this.r = r;
			this.c = c;
			this.amt = amt;
		}
	}
	static int R, C, T;
	static int mat[][], cmat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int order1[] = {3, 0, 2, 1};
	static int order2[] = {3, 1, 2, 0};
	static ArrayList<Integer> machine = new ArrayList<Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		mat = new int[R][C];
		
		for(int i =0; i<R; i++) {
			for(int j=0; j<C; j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j] == -1) {
					machine.add(i);
				}
			}
		}
		
		for(int t=0; t<T; t++) {
			spread();
			
			cmat = new int[R][C];
			for(int i =0; i<R; i++) {
				for(int j=0; j<C; j++) {
					cmat[i][j] = mat[i][j];
				}
			}
			
			circulate(machine.get(0), order1);
			circulate(machine.get(1), order2);
		}
		
		
		int sum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(mat[i][j] != -1) {
					sum += mat[i][j];
				}
			}
		}
		
		System.out.println(sum);
	}
	
	private static void spread() {
		Queue<Dust> q = new LinkedList<Dust>();
		for(int i=0; i<R; i++) {
			for(int j =0; j<C; j++) {
				if(mat[i][j] > 4) {
					q.add(new Dust(i, j, mat[i][j]));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Dust cur = q.poll();
			int cnt = 0;
			int divide = cur.amt / 5;
			for(int k=0; k<4; k++) {
				int nR = cur.r + dr[k];
				int nC = cur.c + dc[k];
				if(nR < 0 || nR >= R || nC <0 || nC >= C || mat[nR][nC] == -1) continue;
				cnt++;
				mat[nR][nC] += divide;
			}
			mat[cur.r][cur.c] -= cnt * divide;
			
		}
	}
	
	private static void circulate(int mR, int order[]) {
		int mC = 0;
		int r = mR;
		int c = 1;
		mat[r][c] = 0;
		
		for(int k=0; k<4; k++) {
			while(true) {
				int nR = r + dr[order[k]];
				int nC = c + dc[order[k]];
				if(nR < 0 || nR >= R || nC < 0 || nC >= C || (nR == mR && nC == mC)) break;
				mat[nR][nC] = cmat[r][c];
				r = nR;
				c = nC;
			}
		}
	}
}

