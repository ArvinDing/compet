
/*
ID: dingarv1
LANG: JAVA
TASK: fence6
*/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class fence6 {
	private static Line[] info;
	private static Line[] copy;
	public static void main(String[] argv) throws IOException {
		Scanner in = new Scanner(new File("fence6.in"));
		PrintWriter out = new PrintWriter(new File("fence6.out"));
		int lines = in.nextInt();
		info = new Line[lines + 1];

		for (int i = 0; i < lines; i++) {
			int name = in.nextInt();
			int length = in.nextInt();
			int flength = in.nextInt();
			int blength = in.nextInt();
			List<Integer> Fneighbors = new ArrayList<Integer>();
			List<Integer> Bneighbors = new ArrayList<Integer>();
			for (int k = 0; k < flength; k++) {
				Fneighbors.add(in.nextInt());
			}
			for (int g = 0; g < blength; g++) {
				Bneighbors.add(in.nextInt());
			}
			info[name] = new Line(Fneighbors, Bneighbors, length);

		}
		
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < lines + 1; i++) {
			copy=new Line[lines + 1];
			for(int j=1;j<info.length;j++){
				copy[j]=new Line(new ArrayList(info[j].Fneighbors),new ArrayList(info[j].Bneighbors), 
						info[j].length);
			//	copy[j]=info[j];
			}
			
			for(int k:info[i].Bneighbors){
				if(copy[k].Bneighbors.contains(i)){
					copy[k].Bneighbors.remove(new Integer(i));
				}else{
					copy[k].Fneighbors.remove(new Integer(i));
				}
			}
			int current=Dijstraik(i)+info[i].length;
			if(current<min){
				min=current;
			}
			
			
		}

		out.println(min);
		in.close();
		out.close();
	}

	public static int Dijstraik(int index) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		HashMap<List<Integer>, Integer> replace = new HashMap<List<Integer>, Integer>();
		HashMap<List<Integer>, Boolean> done = new HashMap<List<Integer>, Boolean>();
		Line a = copy[index];
		Collections.sort(a.Bneighbors);
		queue.add(new Node(0, a.Bneighbors));
		replace.put(a.Bneighbors, 0);

		
		while (true) {
			 Node o = queue.poll();
			if (o == null) {
				return 1000000;
			}
			int distance = o.distance;
			List<Integer> inter = o.intersections;
			if (done.containsKey(inter))
				continue;
			Collections.sort(inter);
			if (inter.contains(index)) {
				return distance;
			}
			for (int i : inter) {
				
				Line c = copy[i];
				Collections.sort(c.Fneighbors);

				if (!(replace.containsKey(c.Fneighbors)) || (distance + c.length) < replace.get(c.Fneighbors)) {
					Node b = new Node(distance + c.length, c.Fneighbors);
					queue.add(b);
				}
				c = copy[i];
				Collections.sort(c.Bneighbors);

				if (!(replace.containsKey(c.Bneighbors)) || (distance + c.length) < replace.get(c.Bneighbors)) {
					Node b = new Node(distance + c.length, c.Bneighbors);
					queue.add(b);
				}
			}
			done.put(inter, true);
		}

	}

	public static class Node implements Comparable {
		int distance;
		List<Integer> intersections;

		public Node(int distance, List<Integer> intersections) {
			this.distance = distance;
			this.intersections = intersections;
		}

		public int compareTo(Object o) {
			return distance - ((Node) o).distance;
		}
	}

	public static class Line {
		List<Integer> Fneighbors;
		List<Integer> Bneighbors;
		int length;

		public Line(List<Integer> Fneighbors, List<Integer> Bneighbors, int length) {
			this.Fneighbors = Fneighbors;
			this.Bneighbors = Bneighbors;
			this.length = length;
		}
	}

}
