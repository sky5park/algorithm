package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// BJ_16236 : 아기상어 - 18년도 하반기 아기상어 문제
public class BJ_16236 {
	static class Shark {
		int r, c, time;
		public Shark(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int N, size, cnt;
	static int mat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Shark shark;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		mat = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j] == 9) {
					size = 2;
					cnt = 0;
					shark = new Shark(i, j, 0);
				}
			}
		}
		
		boolean isNext = true;
		while(isNext) {
			isNext = false;
			boolean visited[][] = new boolean[N][N];
			Queue<Shark> q = new LinkedList<Shark>();
			q.add(shark);
			visited[shark.r][shark.c] = true;
			Shark fish = new Shark(N, N, -1);
			while(!q.isEmpty()) {
				Shark cur = q.poll();
				if(fish.time != -1 && fish.time < cur.time) {
					// 이미 잡힌 물고기가 있을 때 (fish.time != -1 조건)
					// 이미 잡힌 물고기 보다 거리가 더 먼 곳인 경우 (fish.time < cur.time 조건)
					break;
				}
				
				if(mat[cur.r][cur.c] > 0 && size > mat[cur.r][cur.c]) {
					// 해당 위치에 잡을 수 있는 물고기가 있을 때
					isNext = true;
					if(cur.r < fish.r || (cur.r == fish.r && cur.c < fish.c)) {
						fish = cur;
					}
				}
				
				for(int k = 0; k<4; k++) {
					int nR = cur.r + dr[k];
					int nC = cur.c + dc[k];
					if(nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
					if(visited[nR][nC] || mat[nR][nC] > size) continue;
					visited[nR][nC] = true;
					q.add(new Shark(nR, nC, cur.time + 1));
				}
			}
			
			if(isNext) {
				shark = fish;
				cnt++;
				if(cnt == size) {
					size++;
					cnt = 0;
				}
				mat[shark.r][shark.c] = 0; 
			}
		}
		
		System.out.println(shark.time);
	}

}

