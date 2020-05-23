
package camp;

import java.io.*;
import java.util.*;

public class dijamant2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int n = Integer.parseInt(in.readLine());
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		LinkedList<Integer>[] parent = new LinkedList[n];
		LinkedList<Integer>[] derived = new LinkedList[n];
		outer: for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			String name = read.nextToken();
			if (map.containsKey(name)) {
				System.out.println("greska");
				continue;
			}
			map.put(name, i);
			read.nextToken();
			LinkedList<Integer> parents = new LinkedList<Integer>();
			while (true) {
				String par = read.nextToken();
				if (par.equals(";"))
					break;
				if (!map.containsKey(par)) {
					System.out.println("greska");
					map.remove(name);
					continue outer;
				}
				parents.add(map.get(par));
			}
			parent[i] = parents;
			derived[i] = new LinkedList<Integer>();
			for (int par : parent[i]) {
				derived[i].add(par);
				derived[i].addAll(derived[par]);
			}
			Collections.sort(derived[i]);
			thing: {
				for (int par : derived[i]) {
					out: for (int par1 : derived[i]) {
						if (par1 == par)
							continue;
						boolean shareP = false;
						Iterator<Integer> itr = derived[par].iterator();
						Iterator<Integer> itr1 = derived[par1].iterator();
						if (!itr.hasNext() || !itr1.hasNext()) {
							continue out;
						}
						int curr = itr.next();
						int curr1 = itr1.next();
						while (itr.hasNext() || itr1.hasNext()) {
							if (curr == par1||curr1==par)
								continue out;
							if (curr == curr1)
								shareP = true;
							if (curr < curr) {
								if (!itr.hasNext())
									break;
								curr = itr.next();
							} else {
								if (!itr1.hasNext())
									break;
								curr1 = itr1.next();
							}
						}
						if (curr == curr1)
							shareP = true;
						if (curr == par1)
							continue out;
						if (curr1 == par)
							continue out;
						if (shareP) {
							System.out.println("greska");
							map.remove(name);
							break thing;
						}
					}
				}
				System.out.println("ok");
			}
		}
		in.close();
	}
}
