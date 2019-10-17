package Swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_3349 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int T, W, H, N;
	static Point node[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			node = new Point[N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				node[i] = new Point(r, c);
			}
			int cnt = 0;
			for(int i=0; i<N -1; i++) {
				Point cur = node[i];
				Point des = node[i+1];
				int R = des.r - cur.r;
				int C = des.c - cur.c;
				if(R * C > 0) {
					if(Math.abs(C) > Math.abs(R)) {
						cnt += Math.abs(R) + (Math.abs(C) - Math.abs(R));
					}
					else {
						cnt += Math.abs(C) + (Math.abs(R) - Math.abs(C));
					}
				}
				else {
					cnt += (Math.abs(C) + Math.abs(R));
				}
			}
			System.out.println("#" + t + " " + cnt);
		}
	}
	

}
