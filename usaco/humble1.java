
/*
ID: dingarv1
LANG: JAVA
TASK: humble
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class humble1 {

	public static void main(String[] args) throws Exception {
		   Long hum=System.currentTimeMillis();
		Scanner in = new Scanner(new File("humble.in"));
		PrintWriter out = new PrintWriter(new File("humble.out"));
		int k = in.nextInt();
		int n = in.nextInt();
		int o=n;
		int[] info = new int[k];
		for (int i = 0; i < k; i++) {
			info[i] = in.nextInt();
		}
		TreeMap<Long, Amazing> answer = new TreeMap<Long, Amazing>();
		for (int i = 0; i < info.length; i++) {
			Amazing a = new Amazing(i + 1, 0);
			answer.put((long) info[i], a);
		}
		
		int limit = 0;
		while (answer.size() < o) {
			limit += 10;
			
			abc: while (true) {
				for (Entry<Long, Amazing> entry : answer.entrySet()) {
					Long value = entry.getKey();
					Amazing aV = entry.getValue();
					int check = entry.getValue().check;
					int length = entry.getValue().length;
					if (check != length) {
						Amazing b = new Amazing(length, 0);
					
						if (info[check] * value > limit) {
						
							if (check == 0) {
								break abc;
							}
			
							continue;
						}	
					
						aV.check ++;
						answer.put(info[check] * value, b);
					
						break;
					}
				}

			}
		} 
		System.out.println( (System.currentTimeMillis()-hum)/1000+ "."+(System.currentTimeMillis()-hum)%1000);
		List<Long> wow = new ArrayList<Long>();
		for (Entry<Long, Amazing> entry : answer.entrySet()) {
			wow.add(entry.getKey());
		}
		System.out.println( (System.currentTimeMillis()-hum)/1000+ "."+(System.currentTimeMillis()-hum)%1000);
		
		out.println(wow.get(n - 1));
		out.close();
		in.close();
	}

	private static class Amazing {
		int length;
		int check;

		public Amazing(int length, int check) {
			this.length = length;
			this.check = check;
		}
	}
}
