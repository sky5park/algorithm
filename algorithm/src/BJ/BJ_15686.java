package BJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BJ_15686: 치킨 배달
public class BJ_15686 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, cDist;
	static int mat[][];
	static ArrayList<Point> allow = new ArrayList<Point>();
	static ArrayList<Point> house = new ArrayList<Point>();
	static ArrayList<Point> store;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j] == 1) {
					house.add(new Point(i, j));
				}
				else if(mat[i][j] == 2) {
					allow.add(new Point(i, j));
				}
			}
		}
		
		cDist = Integer.MAX_VALUE;
		for(int i =0; i<allow.size(); i++) {
			store = new ArrayList<Point>();
			store.add(allow.get(i));
			dfs(i, 1);
		}
		
		System.out.println(cDist);
	}
	
	private static void dfs(int now, int cnt) {
		if(cnt == M) {
			bfs();
		}
		else {
			for(int i = now +1; i<allow.size(); i++) {
				store.add(allow.get(i));
				dfs(i, cnt + 1);
				store.remove(store.size()-1);
			}
		}
	}
	
	private static void bfs() {
		int sum = 0;
		for(Point h: house) {
			Queue<Point> q = new LinkedList<Point>();
			q.addAll(store);
			int min = Integer.MAX_VALUE;
			while(!q.isEmpty()) {
				Point cur = q.poll();
				int dist = Math.abs(h.r - cur.r) + Math.abs(h.c - cur.c);
				min = Integer.min(min, dist);
			}
			sum += min;
		}
		
		cDist = Integer.min(cDist, sum);
	}

}

