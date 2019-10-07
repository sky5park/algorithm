package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BJ_16234 �α� �̵� ����
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
			/* (�߿�) �ð��� ���� �߿��� �̽�
			 * Swea8_1������ �迭�� ����Ͽ� �迭�� �ִ� ũ�⸦ �����ϰ� ���(�ð� ����) 
			 * Swea8_2������ ArrayList�� ����Ͽ� ������ ������ �ٷ�(�ð��ʰ�)
			 * ��, �޸� ���� ������ �迭�� ������ ������ �� ���� ���� �� �迭�� ��� �Ұ�  
			 */
			int sum[] = new int[N* N +1]; // ���յ��� ��: ������ �ִ� N*N��(������ 1���� �����Ұű� ������ +1)
			int count[] = new int[N* N + 1]; // ���� ���� ����: ������ �ִ� N*N��(������ 1���� �����Ұű� ������ +1)
			// 1. ���漱 �� = union ����
			int union[][] = new int[N][N]; // �� ������ � ���� ���� ������ �� �����ϴ� �迭
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
			// 2. ���� �� �α� �̵�
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int avg = sum[union[i][j]] / count[union[i][j]];
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

