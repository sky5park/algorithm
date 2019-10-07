package BJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 18년도 하반기 나무 제테크 문제
public class BJ_16235 {
	static class Tree {
		int r, c, age;
		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}
	static int N, M, K;
	static int plus[][];
	static int mat[][];
	static int dr[] = {-1, -1, -1, 1, 1, 1, 0, 0};
	static int dc[] = {-1, 0, 1, -1, 0, 1, -1, 1};
	static ArrayList<Tree> trees = new ArrayList<Tree>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		plus = new int[N+1][N+1];
		mat = new int[N+1][N+1];
		
		for(int i =1; i<=N; i++) {
			for(int j = 1; j<= N; j++) {
				plus[i][j] = sc.nextInt();
				mat[i][j] = 5;
			}
		}
		for(int i =0; i<M; i++) {
			int r, c, age;
			r = sc.nextInt();
			c = sc.nextInt();
			age = sc.nextInt();
			trees.add(new Tree(r, c, age));
		}
		
		for(int k=0; k<K; k++) {
			// 작은 순서대로
			Collections.sort(trees, new Comparator<Tree>() {
				@Override
				public int compare(Tree t1, Tree t2) {
					// TODO Auto-generated method stub
					if(t1.r == t2.r && t1.c == t2.c) {
						return t1.age - t2.age;
					}
					if(t1.r == t2.r) {
						return t1.c - t2.c;
					}
					return t1.r - t2.r;
				}
			});
			
			Queue<Tree> alive = new LinkedList<Tree>();
			Queue<Tree> dead = new LinkedList<Tree>();
			
			// 봄
			for(int i=0; i<trees.size(); i++) {
				Tree cur = trees.get(i);
				if(mat[cur.r][cur.c] >= cur.age) {
					mat[cur.r][cur.c] -= cur.age;
					cur.age += 1;
					alive.add(cur);
				}
				else {
					dead.add(cur);
				}
			}
			
			// 여름
			while(!dead.isEmpty()) {
				Tree t = dead.poll();
				mat[t.r][t.c] += t.age /2;
			}
			
			trees.clear();
			// 가을
			while(!alive.isEmpty()) {
				Tree cur = alive.poll();
				trees.add(cur);
				if(cur.age % 5 != 0) continue;
				for(int d =0; d<8; d++) {
					int nR = cur.r + dr[d];
					int nC = cur.c + dc[d];
					if(nR < 1 || nR > N || nC < 1 || nC > N) continue;
					trees.add(new Tree(nR, nC, 1));
				}
			}
			
			if(trees.size() == 0) break;
			// 겨울
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					mat[i][j] += plus[i][j];
				}
			}
		}
		
		System.out.println(trees.size());
 	}

}

