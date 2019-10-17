package Swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Swea_6782 {
	static int N;
	static class Number implements Comparable<Number>{
		int num, cnt;
		public Number (int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Number arg0) {
			// TODO Auto-generated method stub
			return this.cnt - arg0.cnt;
		}
	}
	static ArrayList<Integer> ans = new ArrayList<Integer>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			
			PriorityQueue<Number> q = new PriorityQueue<Number>();
			q.add(new Number(N, 0));
			while(!q.isEmpty()) {
				Number cur = q.poll();
				if(cur.num == 2) {
					ans.add(cur.cnt);
					break;
				}
				
				
				Double root = Math.sqrt(cur.num);
				int temp = root.intValue();
				if(cur.num == temp * temp) {
					q.add(new Number(root.intValue(), cur.cnt+1));
				}
				q.add(new Number(cur.num +1, cur.cnt + 1));
			}
		}
		
		int num = 1;
		for(Integer a: ans) {
			System.out.println("#" + num++ + " " + a);
		}
	}
}
