package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_7576 {
	static class Tomato {
		int r, c, time;
		public Tomato (int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int M, N;
	static int mat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Queue<Tomato> q = new LinkedList<Tomato>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		mat = new int[N][M];
		boolean isFirst = true;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j] == 1) {
					q.add(new Tomato(i, j, 0));
				}
				else if (mat[i][j] == 0) {
					isFirst = false;
				}
			}
		}
		int ans = 0;
		if(!isFirst) {
			ans = -1;
			while(!q.isEmpty()) {
				Tomato cur = q.poll();
				ans = Integer.max(ans, cur.time);
				for(int k =0; k<4; k++) {
					int nR = cur.r + dr[k];
					int nC = cur.c + dc[k];
					if(nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
					if(mat[nR][nC] != 0) continue;
					q.add(new Tomato(nR, nC, cur.time + 1));
					mat[nR][nC] = 1;
				}
			}
			outer:
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(mat[i][j] == 0) {
						ans = -1;
						break outer;
					}
				}
			}
		}
		
		System.out.println(ans);
	}

}


