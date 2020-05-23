package usaco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class angryCow {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("angry.in"));
		String line;
		String info;
		int a = 0;
		int power = 0;
		int v = 1;

		info = in.readLine();
		int space = info.indexOf(" ");
		int cows = Integer.parseInt(info.substring(space + 1));
		ArrayList<Integer> hi = new ArrayList<Integer>();
		while ((line = in.readLine()) != null) {

			hi.add(Integer.parseInt(line.substring(0, line.indexOf(" "))));

			a++;
		}
		Collections.sort(hi);
		int minimum = hi.get(0);

		for (int i = 0; i <= cows; i++) {

			while (minimum + 2 * power >= hi.get(v)) {
				hi.remove(v);
				minimum = hi.get(v);
				v++;
			}
			if(hi.size()==0){
System.out.print(power);
			}
			power++;
			
		}

	}
	// in.close();
}
