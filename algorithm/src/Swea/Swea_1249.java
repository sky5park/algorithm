package Swea;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Swea_1249 {
	static class Point {
		int r, c, time;
		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int ans, N;
	static int mat[][];
	static boolean visited[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t<= T; t++) {
			N = sc.nextInt();
			mat = new int[N][N];
			for(int i=0; i<N; i++) {
				String[] line = sc.next().split("");
				for(int j=0; j<N; j++) {
					mat[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			visited = new boolean[N][N];
			ans = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			System.out.println("#" + t + " " + ans);
		}
	}
	
	private static void dfs(int r, int c, int time) {
		if(r == N-1 && c == N-1) {
			ans = Integer.min(ans, time);
		}
		else {
			for(int k=0; k<4; k++) {
				int nR = r + dr[k];
				int nC = c + dc[k];
				if(nR < 0 || nR >= N || nC < 0 || nC >= N || visited[nR][nC] || ans <= mat[nR][nC] || ans <= (time + mat[nR][nC])) continue;
				visited[nR][nC] = true;
				dfs(nR, nC, time + mat[nR][nC]);
				visited[nR][nC] = false;
			}
		}
	}

}
