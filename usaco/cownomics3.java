
import java.io.*;
import java.util.*;

public class cownomics3 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new File("cownomics.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int genomes = Integer.parseInt(read.nextToken());
		int[][] spotty = new int[cows][genomes];
		int[][] notSpotty = new int[cows][genomes];
		for (int i = 0; i < cows; i++) {
			String curr = in.readLine();
			for (int k = 0; k < genomes; k++) {
				if (curr.charAt(k) == 'A') {
					spotty[i][k] = 0;
				} else if (curr.charAt(k) == 'T') {
					spotty[i][k] = 1;
				} else if (curr.charAt(k) == 'G') {
					spotty[i][k] = 2;
				} else {
					spotty[i][k] = 3;
				}
			}
		}
		for (int i = 0; i < cows; i++) {
			String curr = in.readLine();
			for (int k = 0; k < genomes; k++) {
				if (curr.charAt(k) == 'A') {
					notSpotty[i][k] = 0;
				} else if (curr.charAt(k) == 'T') {
					notSpotty[i][k] = 1;
				} else if (curr.charAt(k) == 'G') {
					notSpotty[i][k] = 2;
				} else {
					notSpotty[i][k] = 3;
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < genomes; i++) {
			for (int k = i + 1; k < genomes; k++) {
				outer: for (int j = k + 1; j < genomes; j++) {
					boolean[][][] spotted = new boolean[4][4][4];
					for (int z = 0; z < cows; z++) {
						spotted[spotty[z][i]][spotty[z][k]][spotty[z][j]] = true;
					}
					for (int a = 0; a < cows; a++) {
						if (spotted[notSpotty[a][i]][notSpotty[a][k]][notSpotty[a][j]]) {
							continue outer;
						}
					}
					cnt++;
				}
			}
		}
		out.println(cnt);
		in.close();
		out.close();

	}

}