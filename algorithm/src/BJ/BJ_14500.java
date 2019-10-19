package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14500 {

	static int N, M, ans;
	static int mat[][];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N+6][M+6]; // 앞 뒤 3 씩 공간을 두고 그곳은 0값으로 
		for(int i=3; i<N + 3; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=3; j<M + 3; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		for(int i=3; i< N+3; i++) {
			for(int j=3; j<M+3; j++) {
				findMax(i, j);
			}
		}
		System.out.println(ans);
	}
	
	private static void findMax(int a, int b) {
		// 1-1
		int sum = 0;
		sum += mat[a][b];
		sum += mat[a][b+1];
		sum += mat[a][b+2];
		sum += mat[a][b+3];
		ans = Integer.max(ans, sum);
		
		// 1-2
		sum = 0;
		sum += mat[a][b];
		sum += mat[a+1][b];
		sum += mat[a+2][b];
		sum += mat[a+3][b];
		ans = Integer.max(ans, sum);

		// 2
		sum = 0;
		sum += mat[a][b];
		sum += mat[a+1][b];
		sum += mat[a][b+ 1];
		sum += mat[a+1][b+1];
		ans = Integer.max(ans, sum);
		
		// 3-1
		sum = 0;
		sum += mat[a][b];
		sum += mat[a+1][b];
		sum += mat[a+2][b];
		sum += mat[a+2][b + 1];
		ans = Integer.max(ans, sum);
		
		// 3-1-2
		sum = 0;
		sum += mat[a][b];
		sum += mat[a+1][b];
		sum += mat[a+2][b];
		sum += mat[a+3][b];
		ans = Integer.max(ans, sum);
		
		// 3- 2
		sum = 0;
		sum += mat[a][b];
		sum += mat[a+1][b];
		sum += mat[a+2][b];
		sum += mat[a+3][b];
		ans = Integer.max(ans, sum);
		
		// 3-2-2
		sum = 0;
		sum += mat[a][b];
		sum += mat[a+1][b];
		sum += mat[a+2][b];
		sum += mat[a+3][b];
		ans = Integer.max(ans, sum);
		
		sum = 0;
		sum += mat[a][b];
		sum += mat[a+1][b];
		sum += mat[a+2][b];
		sum += mat[a+3][b];
		ans = Integer.max(ans, sum);
		
		
	}
	

}
