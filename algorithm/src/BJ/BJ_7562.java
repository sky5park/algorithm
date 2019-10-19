package BJ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7562 {
	static class Point {
		int r, c, time;
		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int T, N;
	static int dr[] = {-2, -2, 2, 2, 1, -1, 1, -1};
	static int dc[] = {1, -1, 1, -1, 2, 2, -2, -2};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			Point start = new Point(r, c, 0);
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			Point end = new Point(r, c, 0);
			
			Queue<Point> q = new LinkedList<Point>();
			boolean visited[][] = new boolean[N][N];
			q.add(start);
			visited[start.r][start.c] = true;
			int result = 0;
			while(!q.isEmpty()) {
				Point cur = q.poll();
				if(cur.r == end.r && cur.c == end.c) {
					result = cur.time;
					break;
				}
				for(int k=0; k<8; k++) {
					int nR = cur.r + dr[k];
					int nC = cur.c + dc[k];
					if(nR < 0 || nR >= N || nC < 0 || nC >= N || visited[nR][nC]) continue;
					visited[nR][nC] = true;
					q.add(new Point(nR, nC, cur.time + 1));
				}
			}
			ans.add(result);
		}
		for(Integer a: ans) {
			System.out.println(a);
		}
		
	}

}
