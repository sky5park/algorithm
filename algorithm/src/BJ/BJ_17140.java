package BJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

// BJ_17140: 이차원 배열과 연산 - 19년도 상반기 정렬 문제
public class BJ_17140 {
	static int mat[][] = new int[101][101];
	static int rowSize, colSize;
	static int r, c, k, time;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		k = sc.nextInt();
		for(int i=1; i<=3; i++) {
			for(int j=1; j<=3; j++) {
				mat[i][j] = sc.nextInt();
			}
		}
		rowSize = 3;
		colSize = 3;
		time = 0;
		
		while(true) {
			if(time > 100) {
				time = -1;
				break;
			}
			if(mat[r][c] == k) {
				break;
			}
			if(rowSize >= colSize) {
				sortR();
			}
			else {
				sortC();
			}
			time++;
		}
		
		System.out.println(time);
		
	}
	
	private static void sortR() {
		int max = 0;
		for(int i =1; i<= rowSize; i++) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			for(int j=1; j<= colSize; j++) {
				int cnt = 0;
				int key = mat[i][j];
				if(key == 0) continue;
				if(map.containsKey(key)) {
					cnt = map.get(key);
				}
				else {
					list.add(key);
				}
				map.put(key, cnt +1);
			}
			
			Collections.sort(list, new Comparator<Integer> () {
				@Override
				public int compare(Integer arg0, Integer arg1) {
					int a0 = map.get(arg0);
					int a1 = map.get(arg1);
					return a0 != a1 ? a0 - a1 : arg0 - arg1 ;
				}
			});
			if(list.size() > 50) {
				list = new ArrayList<Integer>(list.subList(0, 50));
			}
			max = Integer.max(max, 2 * list.size());
			mat[i] = new int[101];
			int idx = 1;
			for(int k =0; k< list.size(); k++) {
				mat[i][idx] = list.get(k);
				mat[i][idx + 1] = map.get(list.get(k));
				idx += 2;
			}
		}
		colSize = max;
	}
	
	private static void sortC() {
		int max = 0;
		for(int i =1; i<= colSize; i++) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			for(int j=1; j<= rowSize; j++) {
				int cnt = 0;
				int key = mat[j][i];
				if(key == 0) continue;
				if(map.containsKey(key)) {
					cnt = map.get(key);
				}
				else {
					list.add(key);
				}
				map.put(key, cnt +1);
			}
			
			Collections.sort(list, new Comparator<Integer> () {
				@Override
				public int compare(Integer arg0, Integer arg1) {
					int a0 = map.get(arg0);
					int a1 = map.get(arg1);
					return a0 != a1 ? a0 - a1 : arg0 - arg1;
				}
			});
			if(list.size() > 50) {
				list = new ArrayList<Integer>(list.subList(0, 50));
			}
			max = Integer.max(max, 2 * list.size());
			for(int d = 1; d<= 100; d++) {
				mat[d][i] = 0;
			}
			int idx = 1;
			for(int k =0; k< list.size(); k++) {
				mat[idx][i] = list.get(k);
				mat[idx + 1][i] = map.get(list.get(k));
				idx += 2;
			}
		}
		rowSize = max;
	}
}
