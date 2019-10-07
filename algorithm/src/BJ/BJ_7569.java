package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BJ_7569: 3차원 토마토
public class BJ_7569 {
	static class Tomato {
		int h, r, c, time;
		public Tomato(int h, int r, int c, int time) {
			this.h = h;
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int M, N, H;
	static int mat[][][];
	static int dh[] = {1, -1, 0, 0, 0, 0};
	static int dr[] = {0, 0, 1, -1, 0, 0};
	static int dc[] = {0 ,0 ,0, 0, 1, -1};
	static Queue<Tomato> q = new LinkedList<Tomato>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		mat = new int[H][N][M];
		boolean isFirst = true;
		for(int z=0; z<H; z++) {
			for(int y=0; y<N; y++) {
				for(int x=0; x<M; x++) {
					mat[z][y][x] = sc.nextInt();
					if(mat[z][y][x] == 1) {
						q.add(new Tomato(z, y, x, 0));
					}
					else if(mat[z][y][x] == 0) {
						isFirst = false;
					}
				}
			}
		}
		int ans = 0;
		if(!isFirst) {
			ans = -1;
			while(!q.isEmpty()) {
				Tomato cur = q.poll();
				ans = Integer.max(ans, cur.time);
				for(int k=0; k<6; k++) {
					int nH = cur.h + dh[k];
					int nR = cur.r + dr[k];
					int nC = cur.c + dc[k];
					if(nH < 0 || nH >= H || nR < 0 || nR >= N || nC < 0 || nC >= M || mat[nH][nR][nC] != 0) continue;
					q.add(new Tomato(nH, nR, nC, cur.time + 1));
					mat[nH][nR][nC] = 1;
				}
			}
			outer:
			for(int z =0; z<H; z++) {
				for(int y = 0; y<N; y++) {
					for(int x = 0; x<M; x++) {
						if(mat[z][y][x] == 0) {
							ans = -1;
							break outer;
						}
					}
				}
			}
		}
		System.out.println(ans);
	}

}

