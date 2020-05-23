
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class auto {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("auto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int dictLines = Integer.parseInt(read.nextToken());
		int request = Integer.parseInt(read.nextToken());
		TreeMap<String, Integer> info = new TreeMap<String, Integer>();
		for (int i = 0; i < dictLines; i++) {
			String temp = in.readLine();
			info.put(temp, i + 1);
		}
		outer:
		for (int i = 0; i < request; i++) {
			read = new StringTokenizer(in.readLine());
			int find = Integer.parseInt(read.nextToken());
			String easy = read.nextToken();
			Collection<String> temp = info.tailMap(easy).keySet();
			Iterator<String> idk = temp.iterator();
			for (int k = 1; k < find; k++) {
				if(!idk.hasNext()){
					out.println("-1");
					continue outer;
				}
				idk.next();
			}
			if(!idk.hasNext()){
				out.println("-1");
				continue;
			}
			String a = idk.next();
			if (a.length()>=easy.length()&&a.startsWith(easy)) {
				out.println(info.get(a));
			} else {
				out.println("-1");
			}

		}
		in.close();
		out.close();
	}
}