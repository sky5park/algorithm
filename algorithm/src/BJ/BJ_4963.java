package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4963 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int W, H;
	static int dr[] = {-1, 1, 0, 0, 1, -1, 1, -1};
	static int dc[] = {0, 0, -1, 1, 1, -1, -1, 1};
	static ArrayList<Integer> ans = new ArrayList<Integer>();
	static int mat[][], union[][];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(W == 0 && H == 0) break;
			mat = new int[H][W];
			ArrayList<Point> land = new ArrayList<Point>();
			for(int i=0; i < H; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for(int j=0; j < W; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
					if(mat[i][j] == 1) {
						land.add(new Point(i, j));
					}
				}
			}
			
			union = new int[H][W];
			int unionNum = 0;
			for(int i =0; i<land.size(); i++) {
				Point p = land.get(i);
				if(union[p.r][p.c] == 0 ) {
					Queue<Point> q = new LinkedList<Point>();
					q.add(p);
					unionNum++;
					while(!q.isEmpty()) {
						Point cur = q.poll();
						union[cur.r][cur.c] = unionNum;
						for(int k=0; k<8; k++) {
							int nR = cur.r + dr[k];
							int nC = cur.c + dc[k];
							if(nR < 0 || nR >= H || nC < 0 || nC >= W || mat[nR][nC] == 0 || union[nR][nC] != 0) continue;
							q.add(new Point(nR, nC));
						}
					}
				}
			}
			ans.add(unionNum);
		}
		
		for(Integer i: ans) {
			System.out.println(i);
		}
		
	}

}
