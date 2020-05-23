
/*
ID: dingarv1
LANG: JAVA
TASK: milk2
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

public class milk2N {

	public static void main(String[] argv) throws IOException {
		int answer = 0;
		int gap = 0;
		int a = 0;
		int c =2;

		BufferedReader in = new BufferedReader(new FileReader("milk2.in"));
		int cnt = Integer.parseInt(in.readLine());
		ArrayList<String[]> ar = new ArrayList<String[]>();
//if(ar.get(x)[y]%2)
		for (int i = 0; i < cnt; i++) {
			String line = in.readLine();
			String[] tokens = line.split(" ");
			ar.add(tokens);
			if (Integer.parseInt(tokens[1]) - Integer.parseInt(tokens[0]) > answer) {
				answer = Integer.parseInt(tokens[1]) - Integer.parseInt(tokens[0]);
			}

		}

		for (int z = 1; z < ar.size(); z++) {
			ar.add(new String []{ar.get(z-1)[0],ar.get(z)[1]});
			ar.remove(z);
			ar.remove(z - 1);
			
		}
		for (int z = 1; z < ar.size(); z++) {
			if ( Integer.parseInt(ar.get(z)[0]) - Integer.parseInt(ar.get(z - 1)[1]) > gap) {
				gap = Integer.parseInt(ar.get(z)[0]) - Integer.parseInt(ar.get(z - 1)[1]);
			}
			if ( Integer.parseInt(ar.get(z)[0]) - Integer.parseInt(ar.get(z - 1)[1]) > answer) {
				answer = Integer.parseInt(ar.get(z)[0]) - Integer.parseInt(ar.get(z - 1)[1]);
			}

		}

		BufferedWriter out = new BufferedWriter(new FileWriter("milk2.out"));
		
		out.write(answer + " " + gap);
		out.newLine();
		out.close();

	}
}
