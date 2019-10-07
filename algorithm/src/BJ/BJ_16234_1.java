package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BJ_16234 인구 이동 문제
public class BJ_16234_1 {
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, L, R;
	static int mat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		mat = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				mat[i][j] = sc.nextInt();
			}
		}
		int cnt = 0;
		boolean isNext = true;
		
		
		while(isNext) {
			isNext = false;
			/* (중요) 시간이 제일 중요한 이슈
			 * Swea8_1에서는 배열을 사용하여 배열의 최대 크기를 정의하고 사용(시간 가능) 
			 * Swea8_2에서는 ArrayList를 사용하여 연합의 정보를 다룸(시간초과)
			 * 즉, 메모리 기준 내에서 배열의 개수를 정의할 수 있을 때는 꼭 배열로 사용 할것  
			 */
			int sum[] = new int[N* N +1]; // 연합들의 합: 연합은 최대 N*N개(연합을 1부터 시작할거기 때문에 +1)
			int count[] = new int[N* N + 1]; // 연합 도시 개수: 연합은 최대 N*N개(연합을 1부터 시작할거기 때문에 +1)
			// 1. 국경선 비교 = union 정의
			int union[][] = new int[N][N]; // 각 점들이 어떤 연합 값을 가지는 지 저장하는 배열
			int num = 1;
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(union[i][j] != 0) continue;
					boolean visited[][] = new boolean[N][N];
					Queue<Point> q = new LinkedList<Point>();
					q.add(new Point(i, j));
					visited[i][j] = true;;
					while(!q.isEmpty()) {
						Point cur = q.poll();
						union[cur.r][cur.c] = num;
						sum[num] += mat[cur.r][cur.c];
						count[num]++;
						for(int k =0; k<4; k++) {
							int nR = cur.r + dr[k];
							int nC = cur.c + dc[k];
							if(nR < 0 || nR >= N || nC < 0 || nC >= N || union[nR][nC] != 0 || visited[nR][nC]) continue;
							int sub = Math.abs(mat[cur.r][cur.c] - mat[nR][nC]);
							if(sub >= L && sub <= R) {
								q.add(new Point(nR, nC));
								visited[nR][nC] = true;
							}
						}
					}
					num++;
				}
			}
			// 2. 연합 별 인구 이동
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int avg = sum[union[i][j]] / count[union[i][j]];
					if(avg != mat[i][j]) { // 기존의 인구수와 avg값이 다르면 이동한다는 의미 즉 횟수가 증가해야 함
						isNext = true;
						mat[i][j] = avg;
					}
				}
			}
			
			// 3. 인구이동 회수 증가
			if(isNext) {
				cnt++;
			}
		}
			
		
		System.out.println(cnt);
	}

}

