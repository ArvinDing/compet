package save;

/*
ID: dingarv1
LANG: JAVA
TASK: rectbarn
*/

import java.util.*;
import java.io.*;

public class rectbarn2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("rectbarn.in"));
		
		StringTokenizer read = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(read.nextToken()), c = Integer.parseInt(read.nextToken()),
				obstacles = Integer.parseInt(read.nextToken());
		boolean[][] done = new boolean[r][c];
		for (int i = 0; i < obstacles; i++) {
			read = new StringTokenizer(in.readLine());
			int row = Integer.parseInt(read.nextToken()) - 1;
			done[row][Integer.parseInt(read.nextToken()) - 1] = true;
		}
		for (int i = 0; i <=34; i++) {
			for (int k = 0; k <=147; k++) {
				System.out.print(done[i][k] ? "#" : ".");
			}
			System.out.println();
		}
		in.close();
	}
}