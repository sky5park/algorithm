package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14501 {
	static int N;
	static int[] cache;
	static int [] T, P;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		T = new int[N];
		P = new int[N];
		cache = new int[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
			cache[i] = -1;
		}
		
		System.out.println(solve(0));
	}
	
	private static int solve(int day) {
		if(day > N) return -Integer.MAX_VALUE;
		if(day == N) return 0;
		if(cache[day] != -1) return cache[day];
		return Integer.max(solve(day + 1), solve(day + T[day]) + P[day]);
	}
	
}
