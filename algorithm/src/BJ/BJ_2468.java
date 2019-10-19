package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468 {
	static class Point {
		int r, c;
		public Point (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N;
	static int mat[][], union[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		mat = new int[N][N];
		int max = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				max = Integer.max(max, mat[i][j]);
			}
		}
		
		int maxCnt = 0;
		for(int m = 0; m< max; m++) {
			int unionNum = 0;
			union = new int[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(mat[i][j] > m && union[i][j] == 0) {
						Queue<Point> q = new LinkedList<Point>();
						q.add(new Point(i, j));
						unionNum++;
						union[i][j] = unionNum;
						while(!q.isEmpty()) {
							Point cur = q.poll();
							for(int k=0; k<4; k++) {
								int nR = cur.r + dr[k];
								int nC = cur.c + dc[k];
								if(nR < 0 || nR >= N || nC < 0 || nC >= N || mat[nR][nC] <= m || union[nR][nC] != 0) continue;
								q.add(new Point(nR, nC));
								union[nR][nC] = unionNum;
							}
						}
					}
				}
			}
			
			maxCnt = Integer.max(maxCnt, unionNum);
		}
		
		System.out.println(maxCnt);
		
	}

}
