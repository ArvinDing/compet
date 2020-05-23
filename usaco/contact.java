
/*
ID: dingarv1
LANG: JAVA
TASK: contact
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class contact {
	private static HashMap<String, Integer> solution = new HashMap<String, Integer>();
	private static String info;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new File("contact.out"));
		String [] lso=in.readLine().split(" ");
		int a = Integer.parseInt(lso[0]);
		int b = Integer.parseInt(lso[1]);
		int n = Integer.parseInt(lso[2]);
		
		
		StringBuilder info1=new StringBuilder();

		  String line;
		  while((line = in.readLine())!=null){
			  info1.append(line);
		  }
		info=info1.toString();	  
	
		for (int i = a; i <= b; i++) {
			solve(i);
		}
		TreeMap<Integer, List<String>> abc = new TreeMap<Integer, List<String>>();

		for (Entry<String, Integer> entry : solution.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (abc.containsKey(value)) {
				List<String> g = abc.get(value);
				g.add(key);
				abc.put(value, g);
				continue;
			}
			List<String> g = new ArrayList<String>();
			g.add(key);
			abc.put(value, g);
		}
		
		for (Map.Entry<Integer, List<String>> e : abc.descendingMap().entrySet()) {
			if (n == 0) {
				break;
			}
			out.println(e.getKey());
			TreeMap<Integer, String> scope = new TreeMap<Integer, String>();
			for (String no : e.getValue()) {

				scope.put(Integer.parseInt("1"+no, 2), no);
			}
			int cnt = 1;
		
			for (String value : scope.values()) {
			
				out.print(value);
				if (cnt % 6 == 0) {
					out.println();
					cnt++;
				} else {
					if (cnt<scope.size()){
					out.print(" ");
					}
					cnt++;
				}
			}
			if(cnt%6!=1){
			out.println();
			}
			n--;
		}

		out.close();
		in.close();

	}

	public static void solve(int a) {
		for (int i = 0; i <= info.length() - a ; i++) {
			if (!solution.containsKey(info.substring(i, i + a ))) {
				solution.put(info.substring(i, i + a ), 1);
				continue;
			}
			solution.put(info.substring(i, i + a ), solution.get(info.substring(i, i + a )) + 1);
		}
	}
}