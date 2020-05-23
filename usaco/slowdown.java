
/*
ID: dingarv1
LANG: JAVA
TASK: skidesign
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class slowdown {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("slowdown.in"));
		PrintWriter out = new PrintWriter(new File("slowdown.out"));
		int events = Integer.parseInt(in.readLine());
		List<Integer> times = new ArrayList<Integer>();
		List<Integer> distances = new ArrayList<Integer>();
		for (int i = 0; i < events; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			if (read.nextToken().equals("T")) {
				times.add(Integer.parseInt(read.nextToken()));
			} else {
				distances.add(Integer.parseInt(read.nextToken()));
			}
		}
		Collections.sort(times);
		Collections.sort(distances);
		double time = 0;
		double distance = 0;
		int rate = 1;
		while (!(times.isEmpty() && distances.isEmpty())) {
			if (times.isEmpty()
					|| (!distances.isEmpty() && (time + (distances.get(0) - distance) * rate) < (times.get(0)))) {
				time += (distances.get(0) - distance) * rate;
				distance = distances.get(0);
				distances.remove(0);

			} else if (!times.isEmpty()) {
				distance += ((double) 1 / rate) * (times.get(0) - time);
				time = times.get(0);
				times.remove(0);
			}
			rate++;
		}
		out.println(Math.round(((1000 - distance) * rate) + time));
		in.close();
		out.close();
	}

}
