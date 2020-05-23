
/*
ID: dingarv1
LANG: JAVA
TASK: comehome
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class comehome {
	private static Map<String, List<String>> info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new File("comehome.out"));
		info = new HashMap<String, List<String>>();
		int a = Integer.parseInt(in.readLine());
		
		List<String> cba = new ArrayList<String>();
		for (int i = 0; i < a; i++) {
			String z = in.readLine();
			String[] rio = z.split(" ");
			String k = rio[0];
			if (isUpperCased(k)) {
				cba.add(k);
			}
			List<String> abc = new ArrayList<String>();
			abc.add(rio[1]);
			abc.add(rio[2]);
			info.put(k, abc);
		}
		String o="";
	int min=Integer.MAX_VALUE;
		for(String d:cba){
			if(min>recipocal(d,0)){
				o=d;
				min=recipocal(d,0);
			}
			
		}

		in.close();
		out.println(o+" "+min);
		out.close();
		}

	

	public static int recipocal(String a, int b) {
		

		List<String> bobs = info.get(a);
		String newa = bobs.get(0);
		int value = Integer.parseInt(bobs.get(1));
		if(newa.equals("Z")){
			return b+value;
		}
		
		return recipocal(newa, value + b);

	}

	public static boolean isUpperCased(String a) {
		if (a.toUpperCase() == a) {
			return true;
		}
		return false;
	}
}
