package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_2151 {
	static class Point implements Comparable<Point> {
		int r, c, d, cnt;
		public Point(int r, int c, int d, int cnt) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Point arg0) {
			// TODO Auto-generated method stub
			return this.cnt - arg0.cnt;
		}
	}
	static int N, ans;
	static char mat[][];
	static boolean visited[][][]; // 가는 방향대로 방문여부 확인해야 함
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<Point> door = new ArrayList<Point>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		mat = new char[N][N];
		for(int i=0; i<N; i++) {
			char input[] = br.readLine().trim().toCharArray(); 
			
			for(int j=0; j<N; j++) {
				mat[i][j] = input[j];
				if(input[j] == '#') {
					door.add(new Point(i, j, 5, 0));
				}
			}
		}
		
		Point s = door.get(0);
		Point e = door.get(1);
		PriorityQueue<Point> q = new PriorityQueue<Point>();
		visited = new boolean[4][N][N];
		
		for(int k=0; k<4; k++) {
			q.add(new Point(s.r, s.c, k, 0));
		}
		
		ans = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(visited[cur.d][cur.r][cur.c]) continue;
			visited[cur.d][cur.r][cur.c] = true;
			if(cur.r == e.r && cur.c == e.c) {
				ans = cur.cnt;
				break;
			}
			int nR = cur.r + dr[cur.d];
			int nC = cur.c + dc[cur.d];
			if(nR < 0 || nR >= N || nC < 0 || nC >= N || mat[nR][nC] == '*') continue;
			
			if(mat[nR][nC] == '!') { // 거울을 하나 추가하는 경우 q에 add 거울 개수++
				if(cur.d < 2) { // 좌 우 방향으로 큐 추가
					q.add(new Point(nR, nC, 2, cur.cnt + 1));
					q.add(new Point(nR, nC, 3, cur.cnt + 1));
				}
				else { // 상 하 방향으로 큐 추가
					q.add(new Point(nR, nC, 0, cur.cnt + 1));
					q.add(new Point(nR, nC, 1, cur.cnt + 1));
				}
			}
			q.add(new Point(nR, nC, cur.d, cur.cnt)); // 거울에 추가를 안하는 경우 거울의 개수 그대로
			
		}
		System.out.println(ans);
	}
	
}
