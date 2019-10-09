package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

// BJ_2589: º¸¹°¼¶
public class BJ_2589 {
	static class Point {
		int r, c, dist;
		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	static int R, C, ans;
	static boolean mat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<Point> allow = new ArrayList<Point>();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		mat = new boolean[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine().trim());
			String[] line = st.nextToken().split("");
			for(int j=0; j<C; j++) {				
				if(line[j].equals("L")) {
					mat[i][j] = true;
					allow.add(new Point(i, j, 0));
				}
				else {
					mat[i][j] = false;
				}
			}
		}
		
		ans = 0;
		for(int i=0; i<allow.size(); i++) {
			Queue<Point> q = new LinkedList<Point>();
			boolean visited[][] = new boolean[R][C];
			q.add(allow.get(i));
			visited[allow.get(i).r][allow.get(i).c] = true;
			int dist = 0;
			while(!q.isEmpty()) {
				Point cur = q.poll();
				dist = Integer.max(dist, cur.dist);
				for(int k=0; k<4; k++) {
					int nR = cur.r + dr[k];
					int nC = cur.c + dc[k];
					if(nR < 0 || nR >= R || nC < 0 || nC >= C) continue;
					if(visited[nR][nC] || !mat[nR][nC]) continue;
					q.add(new Point(nR, nC, cur.dist + 1));
					visited[nR][nC] = true;
				}
			}
			ans = Integer.max(ans, dist);
		}
		System.out.println(ans);
	}

}
