package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1717 {
	static class Calc {
		int a, b;
		public Calc (int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	static int N, M;
	static int[] parent;
	static ArrayList<Calc> c0 = new ArrayList<Calc>();
	static ArrayList<Calc> c1 = new ArrayList<Calc>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		
		for(int i=0; i<= N; i++) {
			parent[i] = i;
		}
		
		ArrayList<String> ans = new ArrayList<String>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(type == 0) {
				union(a, b);
			}
			else {
				String output = "NO";
				if(isLink(a, b)) {
					output = "YES";
				}
				ans.add(output);
			}
		}
		
		for(String s: ans) {
			System.out.println(s);
		}
	}
	
	private static int find(int x) {
		if(parent[x] == x) return x;
		return find(parent[x]);
	}
	
	private static void union(int a, int b) {
		if(a == b) return;
		a = find(a);
		b = find(b);
		if(a< b) parent[b] = a;
		else parent[a] = b;
	}
	
	private static boolean isLink(int a, int b) {
		a = find(a);
		b = find(b);
		if( a == b) return true;
		return false;
	}
}
