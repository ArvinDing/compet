
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class wormhole {
	private static TreeMap<Integer, TreeSet<Integer>> yKey;
	private static int[][] wormhole;
	private static int wormholes;
	private static HashMap<custom, Integer> link;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter out = new PrintWriter(new File("wormhole.out"));
		wormholes = Integer.parseInt(in.readLine());
		yKey = new TreeMap<Integer, TreeSet<Integer>>();
		link =new HashMap<custom,Integer>();
		wormhole = new int[wormholes][2];
		for (int i = 0; i < wormholes; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			wormhole[i][0] = x;
			wormhole[i][1] = y;
			link.put(new custom(x,y), i);
			if (!yKey.containsKey(y)) {
				yKey.put(y, new TreeSet<Integer>());
			}
			yKey.get(y).add(x);
		}
		out.println(recursion(wormholes, new boolean[wormholes], new HashMap<Integer, int[]>()));
		out.close();
		in.close();
	}

	private static int recursion(int notDone, boolean done[], HashMap<Integer, int[]> curr) {
		if (notDone == 0) {
			// for (Entry<Integer, int[]> temp : curr.entrySet()) {
			// System.out.println(temp.getKey()+1 + " " +
			// (link[temp.getValue()[0]][temp.getValue()[1]]+1));
			// }

			if (isLoop(curr)) {
				// System.out.println("LOOP CONFIRMED");
				// System.out.println();
				return 1;

			}
			System.out.println();

		}
		int total = 0;
		for (int i = 0; i < done.length; i++) {
			if (!done[i]) {
				for (int k = i + 1; k < done.length; k++) {
					if (!done[k]) {
						boolean[] copy = Arrays.copyOf(done, done.length);
						copy[i] = true;
						copy[k] = true;
						HashMap<Integer, int[]> copyMap = new HashMap<Integer, int[]>();
						copyMap = curr;
						copyMap.put(i, wormhole[k]);
						copyMap.put(k, wormhole[i]);
						total += recursion(notDone - 2, copy, copyMap);
						curr.remove(i);
						curr.remove(k);
					}

				}
				break;
			}
		}
		return total;
	}

	private static boolean isLoop(HashMap<Integer, int[]> links) {
		for (int i = 0; i < wormholes; i++) {
			int x = wormhole[i][0] - 1;
			int y = wormhole[i][1];
			int wormholesVisted = 0;
			while (true) {
				if (wormholesVisted == wormholes + 1)
					return true;
				if (yKey.get(y).higher(x) == null) {
					break;
				} else {
					x = yKey.get(y).higher(x);
					int[] newArry = links.get(link.get(new custom(x,y)));
					x = newArry[0];
					y = newArry[1];
					wormholesVisted++;
				}
			}
		}
		return false;
	}

	private static class custom {
		int x;
		int y;

		private custom(int x, int y) {
			this.x = x;
			this.y = y;
		} 
		@Override
		 public int hashCode(){
		       
		        return x+y ;
		    }
		
		 @Override
		public boolean equals(Object obj){
			custom temp=(custom)obj;
			return((x==temp.x)&&(y==temp.y));
			
		}
	}
}
