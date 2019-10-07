package BJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// BJ_2667: 단지번호 붙이기
public class BJ_2667 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N;
	static int mat[][];
	static ArrayList<Point> house = new ArrayList<Point>();
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		mat = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] line = sc.next().split("");
			for(int j=0; j<line.length; j++) {
				mat[i][j] = Integer.parseInt(line[j]);
				if(mat[i][j] == 1) {
					house.add(new Point(i, j));
				}
			}
		}
		int union[][] = new int[N][N];
		int num = 1;
		ArrayList<Integer> cntList = new ArrayList<Integer>();
		for(Point h: house) {
			if(union[h.r][h.c] == 0) {
				Queue<Point> q = new LinkedList<Point>();
				boolean visited[][] = new boolean[N][N];
				q.add(new Point(h.r, h.c));
				visited[h.r][h.c] = true;
				int cnt = 0;
				while(!q.isEmpty()) {
					Point cur = q.poll();
					union[cur.r][cur.c] = num; 
					cnt++;
					for(int k=0; k<4; k++) {
						int nR = cur.r + dr[k];
						int nC = cur.c + dc[k];
						if(nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
						if(visited[nR][nC] || mat[nR][nC] == 0) continue;
						q.add(new Point(nR, nC));
						visited[nR][nC] = true;
					}
				}
				
				num++;
				cntList.add(cnt);
			}
		}
		
		Collections.sort(cntList, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		System.out.println(cntList.size());
		for(Integer c: cntList) {
			System.out.println(c);
		}
	}

}

