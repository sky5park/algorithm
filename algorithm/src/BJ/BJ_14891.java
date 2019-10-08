package BJ;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ_14891 {
	static class Stuff {
		int num;
		int[] state = new int[8];
		
		public Stuff() {
			this.state = new int[8];
		}
		
		public Stuff(int num) {
			this.state = new int[8];
			this.num = num;
		}				
	}	
	
	static class Perfor {
		int num, dir;
		public Perfor() {
			
		}
		public Perfor(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
	static ArrayList<Stuff> list = new ArrayList<Stuff>();
	static int[] right = {1, 1, 1, 0};
	static int[] left = {0, 1, 1, 0};
	static int N;	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		
		for(int i =0; i<4; i++) {
			Stuff s = new Stuff(i);
			String line = keyboard.nextLine();
			for(int j=0; j<8; j++) {
				s.state[j] = line.charAt(j)- '0';				
			}			
			list.add(s);
		}
		N = keyboard.nextInt();
		for(int i=0; i<N; i++) {
			int n = keyboard.nextInt();
			int d = keyboard.nextInt();
			int cur = n - 1;
			int r = right[cur];
			int l = left[cur];
			Stuff curStuff = list.get(cur);
			if(r == 1) { // 오른쪽으로
				Stuff nextStuff = list.get(cur + 1);
				if(curStuff.state[2] != nextStuff.state[6]) {
					circulate(cur + 1, -d, true);
				}
			}
			if(l == 1) { // 왼쪽으로
				Stuff nextStuff = list.get(cur - 1);
				if(curStuff.state[6] != nextStuff.state[2]) {
					circulate(cur - 1, -d, false);
				}				
			}
			rotate(cur, d);
		}
		
		printResult();
	}
	
	public static void circulate(int cur, int dir, boolean isRight) {
		Stuff curStuff = list.get(cur);
		if(isRight) {
			int r = right[cur];
			if(r == 1) {
				Stuff nextStuff = list.get(cur + 1);
				if(curStuff.state[2] != nextStuff.state[6]) {
					circulate(cur + 1, dir * -1, isRight);
				}
			}
			rotate(cur, dir);
		}
		else {
			int l = left[cur];
			if(l == 1) {
				Stuff nextStuff = list.get(cur - 1);
				if(curStuff.state[6] != nextStuff.state[2]) {
					circulate(cur - 1, dir * -1, isRight);
				}
			}
			rotate(cur, dir);
		}
	}
	
	public static void rotate(int cur, int dir) {
		Stuff s = list.get(cur);
		if(dir == 1) {
			int temp = s.state[0];
			s.state[0] = s.state[7];
			s.state[7] = s.state[6];
			s.state[6] = s.state[5];
			s.state[5] = s.state[4];
			s.state[4] = s.state[3];
			s.state[3] = s.state[2];
			s.state[2] = s.state[1];
			s.state[1] = temp;
		}
		else {
			int temp = s.state[0];
			s.state[0] = s.state[1];
			s.state[1] = s.state[2];
			s.state[2] = s.state[3];
			s.state[3] = s.state[4];
			s.state[4] = s.state[5];
			s.state[5] = s.state[6];
			s.state[6] = s.state[7];
			s.state[7] = temp;
		}
	}
	
	public static void printResult() {
		int sum = 0;
		for(int i =0; i<list.size(); i++) {
			int top = list.get(i).state[0];
			sum += top == 0 ? 0 : (int)Math.pow(2, i);
		}
		System.out.println(sum);
	}

}
