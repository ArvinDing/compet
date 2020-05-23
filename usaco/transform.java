
/*
ID: dingarv1
LANG: JAVA
TASK: transform
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class transform {

	private static void printArray(int[][] array) {
		for (int [] row : array) {
			for (int one : row) {
				System.out.print(new Character((char) one));
			}
			System.out.println();
		}
	}
	
	public static void main(String[] argv) throws IOException {
		int answer = 7;

		BufferedReader in = new BufferedReader(new FileReader("transform.in"));
		int terms = Integer.parseInt(in.readLine());
		int[][] before = new int[terms][terms];
		int[][] after = new int[terms][terms];
		for (int i = 0; i < terms; i++) {
			String now = in.readLine();
			for (int k = 0; k < terms; k++) {
				before[i][k] = now.charAt(k);
			}
		}
		

		for (int i = 0; i < terms; i++) {
			String now = in.readLine();
			for (int k = 0; k < terms; k++) {
				after[i][k] = now.charAt(k);
			}
		}
		
		while (true) {
			rotate90(before, terms);
			if (isSame(before, after, terms)) {
				answer = 1;
				break;
			}
			rotate90(before, terms);
			if (isSame(before, after, terms)) {
				answer = 2;
				break;
			}
			rotate90(before, terms);
			if (isSame(before, after, terms)) {
				answer = 3;
				break;
			}
			rotate90(before, terms);
			reflect(before, terms);
			if (isSame(before, after, terms)) {
				answer = 4;
				break;
			}
			rotate90(before, terms);
			if (isSame(before, after, terms)) {
				answer = 5;
				break;
			}
			rotate90(before, terms);
			if (isSame(before, after, terms)) {
				answer = 5;
				break;
			}
			rotate90(before, terms);
			if (isSame(before, after, terms)) {
				answer = 5;
				break;
			}
			rotate90(before, terms);
			reflect(before, terms);

			if (isSame(before, after, terms)) {
				answer = 6;
				break;
			}
			break;
		}
		BufferedWriter out = new BufferedWriter(new FileWriter("transform.out"));

		out.write(String.valueOf(answer));
		out.newLine();
		out.close();
	}

	public static boolean rotate90(int[][] a, int cnt) {
		// 0,0 2,0
		// 0,1 1,0
		// 0,2 0,0
		// 1,0 2,1
		// 2,0 2,2
		int[][] fort = new int[cnt][cnt];
		for (int i = 0; i < cnt; i++) {
			for (int k = 0; k < cnt; k++) {
				fort[i][k]=a[i][k];
			}
		}
	
	
		for (int i = 0; i < cnt; i++) {
			for (int k = 0; k < cnt; k++) {
				a[i][k] = fort[cnt - k - 1][i];
			}
		}
		return true;
	}

	public static boolean reflect(int[][] a, int cnt) {
		int[][] fort = new int[cnt][cnt];
		for (int i = 0; i < cnt; i++) {
			for (int k = 0; k < cnt; k++) {
				fort[i][k]=a[i][k];
			}
		}
	
		for (int i = 0; i < cnt; i++) {
			for (int k = 0; k < cnt; k++) {
				a[i][k] = fort[i][cnt-k-1];
			}
		}
		return true;
	}

	public static boolean isSame(int[][] a, int[][] b, int cnt) {
		for (int i = 0; i < cnt; i++) {
			for (int k = 0; k < cnt; k++) {
				if (a[i][k] != b[i][k]) {
					return false;
				}
			}
		}
		return true;
	}
}
