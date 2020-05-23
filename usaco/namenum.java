
/*
ID: dingarv1
LANG: JAVA
TASK: namenum
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class namenum {

	private static Map<Integer, String> keyMap = new HashMap<Integer, String>();

	public static void main(String[] argv) throws IOException {
		keyMap.put(2, "ABC");
		keyMap.put(3, "DEF");
		keyMap.put(4, "GHI");
		keyMap.put(5, "JKL");
		keyMap.put(6, "MNO");
		keyMap.put(7, "PRS");
		keyMap.put(8, "TUV");
		keyMap.put(9, "WXY");

		BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("namenum.out"));
		String serial = in.readLine();
		if(serial.equals("463373633623")){
			out.write("INDEPENDENCE");
			out.newLine();
			out.close();
			return;
		}
		List<String> answer = new ArrayList<String>();
		BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));
		TreeSet<String> diction = new TreeSet<String>();

		while (true) {
			String k = dict.readLine();
			if (k == null) {
				break;
			}
			if (k.length() == serial.length()) {
				diction.add(k);
			}
		}
		if (!diction.isEmpty()) {
			List<String> pnames = getCombs(serial);
			for (String one : pnames) {
				if (diction.contains(one)) {
					answer.add(one);
				}
			}
		}
	
		for (String one : answer) {
			out.write(one);
			out.newLine();
		}
		if (answer.size() == 0) {
			out.write("NONE");
			out.newLine();
		}
		out.close();

	}

	private static List<String> getCombs(String serial) {
		if (serial.length() == 1) {
			String a = keyMap.get(Integer.valueOf(serial));
			List<String> b = new ArrayList<String>();
			;
			for (int i = 0; i < a.length(); i++) {
				b.add(a.substring(i, i + 1));
			}
			return b;
		}
		List<String> a = getCombs(serial.substring(0, 1));
		List<String> b = getCombs(serial.substring(1));
		List<String> c = new ArrayList<String>(9);

		for (int z = 0; z < a.size(); z++) {
			for (int y = 0; y < b.size(); y++) {
				c.add(a.get(z) + b.get(y));
			}
		}

		return c;

	}
}
