
import java.util.*;
import java.io.*;

public class cownomics {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int genomes = Integer.parseInt(read.nextToken());
		Set<Character>[] spotty = new Set[genomes];
		for (int i = 0; i < cows; i++) {
			String curr = in.readLine();
			for (int k = 0; k < genomes; k++) {
				if(spotty[k]==null){
					spotty[k]=new HashSet<Character>();
				}
				spotty[k].add(curr.charAt(k));
			}
		}
		boolean[] notPossible = new boolean[genomes];
		for (int i = 0; i < cows; i++) {
			String curr = in.readLine();
			for (int k = 0; k < genomes; k++) {
				if (spotty[k].contains(curr.charAt(k))) {
					notPossible[k] = true;
				}
			}
		}
		int cnt = 0;
		for (boolean a : notPossible) {
			if (!a)
				cnt++;
		}
		out.println(cnt);
		out.close();
		in.close();
	}

}