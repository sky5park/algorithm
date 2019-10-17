package BJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_3055 {
	static class Point {
		int r, c, time;
		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int R, C;
	static int mat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Point rab;
	static Point house;
	static ArrayList<Point> water = new ArrayList<Point>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		mat = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String[] line = sc.next().split("");
			for(int j =0; j<line.length; j++) {
				if(line[j].equals(".")) {
					mat[i][j] = 0;
				}
				else if(line[j].equals("*")) {
					mat[i][j] = 1;
					water.add(new Point(i, j, 0));
				}
				else if(line[j].equals("X")) {
					mat[i][j] = 2;
				}
				else if(line[j].equals("D")) {
					mat[i][j] = 3;
					house = new Point(i, j, 0);
				}
				else if(line[j].equals("S")) {
					mat[i][j] = 4;
					rab = new Point(i, j, 0);
				}
				
			}
		}
		
		Queue<Point> q = new LinkedList<Point>();
		boolean visited[][] = new boolean[R][C];
		
		for(Point w: water) {
			visited[w.r][w.c] = true;
			q.add(w);
		}
		
		int wTime[][] = new int[R][C];
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			wTime[cur.r][cur.c] = cur.time;
			for(int k=0; k<4; k++) {
				int nR = cur.r + dr[k];
				int nC = cur.c + dc[k];
				if(nR < 0 || nR >= R || nC < 0 || nC >= C || visited[nR][nC]) continue;
				if(mat[nR][nC] == 2 || mat[nR][nC] == 3 || mat[nR][nC] == 4) continue;
				q.add(new Point(nR, nC, cur.time + 1));
				visited[nR][nC] = true;
			}
		}
		
		q = new LinkedList<Point>();
		visited = new boolean[R][C];
		q.add(rab);
		visited[rab.r][rab.c] = true;
		boolean isDes = false;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.r == house.r && cur.c == house.c) {
				rab = cur;
				isDes = true;
				break;
			}
			rab = cur;
			for(int k=0; k<4; k++) {
				int nR = cur.r + dr[k];
				int nC = cur.c + dc[k];
				if(nR < 0 || nR >= R || nC < 0 || nC >= C || visited[nR][nC]) continue;
				if(mat[nR][nC] == 2 || mat[nR][nC] == 1 || (1 <= wTime[nR][nC] && wTime[nR][nC] <= cur.time + 1)) continue;
				q.add(new Point(nR, nC, cur.time + 1));
				visited[nR][nC] = true;
			}
		}
		
		if(isDes) {
			System.out.println(rab.time);
		}
		else {
			System.out.println("KAKTUS");
		}
	}

}
