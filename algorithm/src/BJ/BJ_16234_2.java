package BJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* ���� ���� ---> (�߿�) �迭�� ArrayList�� �ӵ����̷� �ð� �ʰ� �� */
public class BJ_16234_2 {
	
	static class Union {
		int n, cnt, sum;
		public Union(int n ,int cnt, int sum) {
			this.n = n;
			this.cnt = cnt;
			this.sum = sum;
		}
	}
	
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
			// 1. ���漱 �� = union ����
			int union[][] = new int[N][N];
			ArrayList<Union> uList = new ArrayList<Union>();
			uList.add(new Union(0, 0, 0)); // ������ ��ȣ�� 1������ ������ �ű� ������ �ε����� ���߱� ���� add
			int num = 1;
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(union[i][j] != 0) continue;
					int unionCnt = 0;
					int unionSum = 0;
					boolean visited[][] = new boolean[N][N];
					Queue<Point> q = new LinkedList<Point>();
					q.add(new Point(i, j));
					visited[i][j] = true;;
					while(!q.isEmpty()) {
						Point cur = q.poll();
						union[cur.r][cur.c] = num;
						unionCnt++;
						unionSum += mat[cur.r][cur.c];
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
					uList.add(new Union(num, unionCnt, unionSum));
					num++;
				}
			}
			// 2. ���� �� �α� �̵�
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					Union u = uList.get(union[i][j]);
					int avg = u.sum / u.cnt;
					if(avg != mat[i][j]) { // ������ �α����� avg���� �ٸ��� �̵��Ѵٴ� �ǹ� �� Ƚ���� �����ؾ� ��
						isNext = true;
						mat[i][j] = avg;
					}
				}
			}
			
			// 3. �α��̵� ȸ�� ����
			if(isNext) {
				cnt++;
			}
		}
			
		
		System.out.println(cnt);
	}

}
