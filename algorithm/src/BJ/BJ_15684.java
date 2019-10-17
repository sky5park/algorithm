package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BJ_15684 : 사다리 조작  브루트 포스, dfs, union-find
public class BJ_15684 {
	static int N, M, H, ans;
	static int parent[][];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		parent = new int[H + 1][N + 1]; // 각 가로 줄에서 세로 줄의 연결 여부를 파악하는 배열
		for(int i = 1; i<=H; i++) {
			for(int j=1; j<= N; j++) {
				parent[i][j] = j;
			}
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(b, b + 1, a); // 연결 시킴
		}
		
		ans = -1;
		
		// 첫 번째 가로의 세로 줄 부터
		dfs(1, 1, 0);
		
		System.out.println(ans);
	}
	
	private static void dfs(int r, int c, int cnt) {
		if(check()) { // 모든 세로줄의 도착지가 같은 세로 줄인지 확인
			ans = ans == -1 ? cnt : Integer.min(ans, cnt);
		}
		else {
			if(cnt < 3) { // 3보다 작은 경우만 dfs 탐색하면 됨
				for(int i=r; i<=H; i++) {
					for(int j=c; j<N; j++) {
						int right = j + 1;
						if(canLink(j, i)) {
							union(j, right, i);
							dfs(i, j + 1, cnt + 1); // 현재 행의 다음 세로 줄 바라보기
							parent[i][j] = j;
							parent[i][right] = right; 
						}
					}
					c = 1; // 다음 행으로 넘어갈 때는 c 변수를 다시 1(초기시작)부터
				}
			}
		}
	}
	
	// 자신의 오른쪽 세로줄과 연결 가능한지 확인
	private static boolean canLink(int a, int r) {
		int right = a + 1;
		int left = a - 1;
		boolean isOk = true;
		if(left > 0) { // 왼쪽줄에 이미 연결 됐는지 확인
			if(isLink(a, left, r)) isOk = false; // 왼쪽 거와 이미 연결돼 있으면 isOk = false
		}
		if(isOk) {
			if(!isLink(a, right, r)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean check() {
		for(int i=1; i<=N; i++) {
			int r = 1;
			int c = i;
			while(r <= H) {
				int right = c + 1;
				int left = c - 1;
				if(right <= N && isLink(c, right, r)) {
					c = right;
				}
				else if(left > 0 && isLink(c, left, r)) {
					c = left;
				}
				r++;
			}
			if(c != i) return false;
		}
		return true;
	}
	
	private static int find(int x, int h) {
		if(parent[h][x] == x) return x;
		return find(parent[h][x], h);
	}
	
	private static void union(int a, int b, int h) {
		a = find(a, h);
		b = find(b, h);
		if(a < b) parent[h][b] = a;
		else parent[h][a] = b;
	}
	
	private static boolean isLink(int a, int b, int h) {
		a = find(a, h);
		b = find(b, h);
		if(a == b) return true;
		return false;
	}

}
