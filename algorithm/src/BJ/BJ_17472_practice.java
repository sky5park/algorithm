package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17472_practice {
	static class Point {
		int r, c;
		public Point (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int a, b, dist;
		public Edge (int a, int b, int dist) {
			this.a = a;
			this.b = b;
			this.dist = dist;
		}
		@Override
		public int compareTo(Edge arg0) {
			// TODO Auto-generated method stub
			return this.dist - arg0.dist;
		}
	}
	static int N, M, unionNum;
	static int mat[][], union[][];
	static int parent[];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<Point> land = new ArrayList<Point>();
	static ArrayList<ArrayList<Point>> nodes = new ArrayList<ArrayList<Point>>();
	static ArrayList<Edge> edge = new ArrayList<Edge>();
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
				if(mat[i][j] == 1) {
					land.add(new Point(i, j));
				}
			}
		}
		
		union = new int[N][M];
		unionNum = 0;
		nodes.add(null);
		for(int i =0; i<land.size(); i++) {
			Point l = land.get(i);
			if(union[l.r][l.c] == 0) {
				Queue<Point> q = new LinkedList<Point>();
				ArrayList<Point> uList = new ArrayList<Point>();
				unionNum++;
				q.add(l);
				while(!q.isEmpty()) {
					Point cur = q.poll();
					union[cur.r][cur.c] = unionNum;
					uList.add(cur);
					for(int k=0; k<4; k++) {
						int nR = cur.r + dr[k];
						int nC = cur.c + dc[k];
						if(nR < 0 || nR >= N || nC < 0 || nC >= M || mat[nR][nC] == 0 || union[nR][nC] != 0) continue;
						q.add(new Point(nR, nC));
					}
				}
				
				nodes.add(uList);
			}
		}
		
		parent = new int[unionNum];
		for(int i=1; i<=unionNum; i++) {
			parent[i] = i;
			ArrayList<Point> uList = nodes.get(i);
			for(int j= i+1; j<= unionNum; j++) {
				int min = Integer.MAX_VALUE;
				for(int k =0; k<4; k++) {
					for(int p = 0; p< uList.size(); p++) {
						Point cur = uList.get(p);
						int r = cur.r;
						int c = cur.c;
						boolean isNext = true;
						int cnt = 0;
						while(isNext) {
							isNext = false;
							int nR = r + dr[k];
							int nC = c + dc[k];
							if(nR < 0 || nR <= N || nC < 0 || nC <= M || (mat[nR][nC] != 0 && union[nR][nC] != j)) {
								cnt = 0;
							}
							else {
								if(mat[nR][nC] == 0) {
									isNext = true;
									cnt++;
									r = nR;
									c = nC;
								}
							}
						}
						
						if(cnt >= 2) {
							min = Integer.min(min, cnt);
						}
					}
				}
				
				if(min != Integer.MAX_VALUE) {
					edge.add(new Edge(i, j, min));
				}
			}
			
		}
		
		if(edge.size() <= unionNum -1) {
			Collections.sort(edge);
			int bridgeSize = 0;
			for(int i=0; i<edge.size(); i++) {
				Edge e = edge.get(i);
				if(!isLink(e.a, e.b)) {
					union(e.a, e.b);
					bridgeSize += e.dist;
				}
			}
			
			if(lastCheck()) {
				System.out.println(bridgeSize);
			}
			else {
				System.out.println(-1);
			}
		}
		else {
			System.out.println(-1);
		}
		
	}
	private static boolean lastCheck() {
		int x = parent[1];
		for(int i = 2; i<= unionNum; i++) {
			int y = find(i);
			if(x != y) return false;
			
		}
		return true;
	}
	
	private static int find(int x) {
		if(parent[x] == x) return x;
		return find(parent[x]);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	private static boolean isLink(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return true;
		return false;
	}
	
}
