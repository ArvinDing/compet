package save;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class compare {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("a"));
		BufferedReader in1 = new BufferedReader(new FileReader("b"));
		int lines = 49847;
		int min = Integer.MAX_VALUE;
		int minI = -1;
		for (int i = 1; i <= lines; i++) {
			String a = in.readLine();
			String b = in1.readLine();

			if (!a.equals(b)) {
				if (Integer.parseInt(a) < min) {
					min = Integer.parseInt(a);
					minI = i;
				}
			//	System.out.println(i);
			//	break;
			}
		}
		System.out.println(minI+" "+min);
	}
}
