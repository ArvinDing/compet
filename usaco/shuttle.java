
/*
ID: dingarv1
LANG: JAVA
TASK: shuttle
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public class shuttle {
	private static int holeP;
	private static List<Integer>answer;

	public static void main(String[] args) throws Exception {
		// read
		BufferedReader in = new BufferedReader(new FileReader("shuttle.in"));
		PrintWriter out = new PrintWriter(new File("shuttle.out"));
		holeP = Integer.parseInt(in.readLine());
		 answer = new ArrayList<Integer>();
		char[] board = new char[2 * holeP + 1];
		for (int i = 0; i < board.length; i++) {
			if (i < holeP) {
				board[i] = 'W';
			} else if (i == holeP) {
				board[i] = ' ';
			} else {
				board[i] = 'B';
			}
		}
	
		while (true) {
			
			boolean[] whatWorks = isValid(board);
			int moves=howM(whatWorks);
			if ( moves== 0) {
				break;
			}
			if(whatWorks[2]){
				answer.add(holeP-2);
				skip(board,false);
				continue;
			}
			if(whatWorks[0]){
				answer.add(holeP-1);
				move(board,false);
				continue;
			}
			if(whatWorks[1]){
				answer.add(holeP+1);
				move(board,true);
				continue;
			}
		
			if(whatWorks[3]){
				answer.add(holeP+2);
				skip(board,true);
				continue;
			}
		
					
			
		}
		
		//for(int i=0;i<answer.size()-1;i++){
		for(int i=1;i<answer.size();i++){
			out.print(answer.get(i-1)+1);
			
			if(i%20==0&&i!=0){
				out.println();
			}else{
				out.print(" ");
			}
		}
		out.println(answer.get(answer.size()-1)+1);
		in.close();
		out.close();
	}
	private static int howM(boolean[]lol){
		int moves = 0;
		for (boolean a : lol) {
			if (a)moves++;
		}
		return moves;
	}
	
	private static boolean[] isValid(char[] a) {
		// 0-moveW, 1-moveB,2-skipW,3-skipB
		boolean[] unity = new boolean[4];
		boolean flag=false;
		if (holeP != 0 && a[holeP - 1] == 'W') {
		
			unity[0] = true;
			if(holeP>=3&&holeP<=a.length-2&&a[holeP-2]=='B'&&a[holeP-3]=='W'&&a[holeP+1]=='B'){
				unity[0]=false;
			}
		} else {
			if (holeP >= 2 && a[holeP - 2] == 'W') {
				unity[2] = true;
				 flag=true;
			}
		}
		if (holeP <= a.length-2 && a[holeP + 1] == 'B') {
			unity[1] = true;
			
		} else {
			if (holeP <= a.length - 3 && a[holeP + 2] == 'B') {
				unity[3] = true;
	
				unity[0]=false;
				unity[2]=false;
				
			}
		}
		if(flag){
			unity[1]=false;
			unity[3]=false;
		}
		return unity;
	}

	private static void move(char[] a, boolean black) {
		if (black) {
			a[holeP] = 'B';
			a[holeP + 1] = ' ';
			holeP++;
		} else {
			a[holeP] = 'W';
			a[holeP - 1] = ' ';
			holeP--;
		}
	}

	private static void skip(char[] a, boolean black) {
		if (black) {
			a[holeP] = 'B';
			a[holeP + 2] = ' ';
			holeP += 2;
		} else {
			a[holeP] = 'W';
			a[holeP - 2] = ' ';
			holeP -= 2;
		}
	}

}