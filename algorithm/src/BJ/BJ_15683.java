package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BJ_15683 감시 : 브루트 포스, dfs, 시뮬레이션
public class BJ_15683 {
	static class Cctv {
		int n, r, c;
		public Cctv (int n, int r, int c) {
			this.n = n;
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, ans;
	static int mat[][];
	static int dr[] = {-1, 0, 1, 0}; // 상 좌 하 우
	static int dc[] = {0, -1, 0, 1};
	static ArrayList<Cctv> cctv = new ArrayList<Cctv>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				if(mat[i][j] != 0 && mat[i][j] != 6) {
					cctv.add(new Cctv(mat[i][j], i, j));
				}
			}
		}
		
		ans = Integer.MAX_VALUE;
		
		dfs(0, mat);
		System.out.println(ans);
	}
	
	private static void dfs(int idx, int[][] prev) {
		if(idx == cctv.size()) {
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(prev[i][j] == 0) {
						cnt++;
					}
				}
			}
			ans = Integer.min(ans, cnt);
		}
		else {
			Cctv cur = cctv.get(idx);
			int cmat[][] = new int[N][M];
			if(cur.n == 1) {
				for(int k=0; k<4; k++) {
					for(int i=0; i<N; i++) {
						cmat[i] = prev[i].clone();
					}
					move(k, cur.r, cur.c, cmat);
					
					dfs(idx + 1, cmat);
				}
			}
			else if(cur.n == 2) {
				for(int k=0; k<2; k++) {
					for(int i=0; i<N; i++) {
						cmat[i] = prev[i].clone();
					}
					move(k, cur.r, cur.c, cmat);
					move(k + 2, cur.r, cur.c, cmat);
					
					dfs(idx + 1, cmat);
				}
			}
			else if(cur.n == 3) {
				for(int k=0; k<4; k++) {
					for(int i=0; i<N; i++) {
						cmat[i] = prev[i].clone();
					}
					
					move(k, cur.r, cur.c, cmat);
					move((k + 1) % 4, cur.r, cur.c, cmat);
					
					dfs(idx + 1, cmat);
				}
			}
			else if(cur.n == 4) {
				for(int k=0; k<4; k++) {
					for(int i=0; i<N; i++) {
						cmat[i] = prev[i].clone();
					}
					move(k, cur.r, cur.c, cmat);
					move((k + 1) % 4, cur.r, cur.c, cmat);
					move((k + 2) % 4, cur.r, cur.c, cmat);
					
					dfs(idx + 1, cmat);
				}
			}
			else if(cur.n == 5) {
				for(int i=0; i<N; i++) {
					cmat[i] = prev[i].clone();
				}
				
				for(int k=0; k<4; k++) {
					move(k, cur.r, cur.c, cmat); 
				}
				dfs(idx + 1, cmat);
			}
		}
	}
	
	private static void move(int dir, int r, int c, int[][] cmat) {
		boolean isNext = true;
		while(isNext) {
			isNext = false;
			int nR = r + dr[dir];
			int nC = c + dc[dir];
			if(nR < 0 || nR >= N || nC < 0 || nC >= M || cmat[nR][nC] == 6) continue;
			isNext = true;
			cmat[nR][nC] = -1;
			r = nR;
			c = nC;
		}
	}

}
