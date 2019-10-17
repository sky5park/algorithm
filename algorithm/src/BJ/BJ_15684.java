package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BJ_15684 : ��ٸ� ����  ���Ʈ ����, dfs, union-find
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
		parent = new int[H + 1][N + 1]; // �� ���� �ٿ��� ���� ���� ���� ���θ� �ľ��ϴ� �迭
		for(int i = 1; i<=H; i++) {
			for(int j=1; j<= N; j++) {
				parent[i][j] = j;
			}
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(b, b + 1, a); // ���� ��Ŵ
		}
		
		ans = -1;
		
		// ù ��° ������ ���� �� ����
		dfs(1, 1, 0);
		
		System.out.println(ans);
	}
	
	private static void dfs(int r, int c, int cnt) {
		if(check()) { // ��� �������� �������� ���� ���� ������ Ȯ��
			ans = ans == -1 ? cnt : Integer.min(ans, cnt);
		}
		else {
			if(cnt < 3) { // 3���� ���� ��츸 dfs Ž���ϸ� ��
				for(int i=r; i<=H; i++) {
					for(int j=c; j<N; j++) {
						int right = j + 1;
						if(canLink(j, i)) {
							union(j, right, i);
							dfs(i, j + 1, cnt + 1); // ���� ���� ���� ���� �� �ٶ󺸱�
							parent[i][j] = j;
							parent[i][right] = right; 
						}
					}
					c = 1; // ���� ������ �Ѿ ���� c ������ �ٽ� 1(�ʱ����)����
				}
			}
		}
	}
	
	// �ڽ��� ������ �����ٰ� ���� �������� Ȯ��
	private static boolean canLink(int a, int r) {
		int right = a + 1;
		int left = a - 1;
		boolean isOk = true;
		if(left > 0) { // �����ٿ� �̹� ���� �ƴ��� Ȯ��
			if(isLink(a, left, r)) isOk = false; // ���� �ſ� �̹� ����� ������ isOk = false
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
