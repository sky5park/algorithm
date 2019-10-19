package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5427 {
	static class Point {
		int r, c, dist;
		public Point (int r, int c, int dist) { 
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	static int T, W, H;
	static char mat[][];
	static int fmat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Point sang;
	static ArrayList<Point> fire;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int t = 0; t< T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			fire = new ArrayList<Point>();
			mat = new char[H][W];
			
			for(int i=0; i<H; i++) {
				char temp[] = br.readLine().trim().toCharArray();
				for(int j=0; j<W; j++) {
					mat[i][j] = temp[j];
					if(mat[i][j] == '@') {
						sang = new Point(i, j, 0);
					}
					else if(mat[i][j] == '*') {
						fire.add(new Point(i, j, 0));
					}
				}
			}
			
			Queue<Point> q = new LinkedList<Point>();
			boolean visited[][] = new boolean[H][W];
			for(Point f: fire) {
				q.add(f);
				visited[f.r][f.c] = true; 
			}
			
			fmat = new int[H][W];
			
			while(!q.isEmpty()) {
				Point cur = q.poll();
				fmat[cur.r][cur.c] = cur.dist; 
				for(int k=0; k<4; k++) {
					int nR = cur.r + dr[k];
					int nC = cur.c + dc[k];
					if(nR < 0 || nR >= H || nC < 0 || nC >= W || mat[nR][nC] != '.' || visited[nR][nC]) continue;
					visited[nR][nC] = true;
					q.add(new Point(nR, nC, cur.dist + 1));
				}
			}
			
			q = new LinkedList<Point>();
			visited = new boolean[H][W];
			q.add(sang);
			visited[sang.r][sang.c] = true;
			boolean isExit = false;
			int result = 0;
			outer:
			while(!q.isEmpty()) {
				Point cur = q.poll();
				for(int k=0; k<4; k++) {
					int nR = cur.r + dr[k];
					int nC = cur.c + dc[k];
					if(nR < 0 || nR >= H || nC < 0 || nC >= W) {
						isExit = true;
						result = cur.dist + 1;
						break outer;
					}
					if(mat[nR][nC] == '#' || mat[nR][nC] == '*' || fmat[nR][nC] <= cur.dist + 1 || visited[nR][nC]) continue;
					visited[nR][nC] = true;
					q.add(new Point(nR, nC, cur.dist + 1));
				}
			}
			
			if(isExit) {
				ans.add(result);
			}
			else {
				ans.add(-1);
			}
		}
		
		for(Integer i: ans) {
			if(i == -1) {
				System.out.println("IMPOSSIBLE");
			}
			else {
				System.out.println(i);
			}
		}
		
	}

}
