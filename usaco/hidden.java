
/*
ID: dingarv1
LANG: JAVA
TASK: hidden
*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class hidden {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hidden.in"));
		PrintWriter out = new PrintWriter(new File("hidden.out"));
		int characters = Integer.parseInt(in.readLine());
		StringBuilder read = new StringBuilder();
		String text = in.readLine();
		while (text != null) {
			read.append(text);
			text = in.readLine();
		}
		read.append(read);
		char[] info = read.toString().toCharArray();
		int min = 0;
		int length = 0;
		int index = 1;
		while (length < characters && index < characters) {
			// System.out.println(index);
			if (info[min + length] > info[index + length]) {
				min = index;
				index++;
				length = 0;
			} else if (info[min + length] == info[index + length]) {
				length++;
			} else if (info[min + length] < info[index + length]) {
				index += length + 1;
				length = 0;
			}
		}
		out.println(min);
		in.close();
		out.close();

	}
}
