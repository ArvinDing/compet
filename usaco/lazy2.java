
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class lazy2 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lazy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lazy.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int dimension = Integer.parseInt(read.nextToken());
		int reach = Integer.parseInt(read.nextToken());
		int[][] info = new int[dimension + dimension - 1][dimension + dimension - 1];
		for (int i = 0; i < dimension; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < dimension; k++) {
				info[i + k][dimension - i + k - 1] = Integer.parseInt(read.nextToken());
			}
		}
		reach=Math.min(reach, dimension-1);
		int max = 0;
		for (int i = reach; i < 2 * dimension - reach - 1; i++) {
			int sum = 0;
			for (int y = i - reach; y <= i + reach; y++) {
				for (int x = 0; x <= reach * 2; x++) {
					sum += info[y][x];

				}
			}

			max = Math.max(max, sum);
			for (int x = reach+1; x < 2 * dimension - 1 - reach; x++) {
				for (int y = i - reach; y <= i + reach; y++) {
					sum -= info[y][x - reach - 1];
					sum += info[y][x + reach];
				}
				max = Math.max(max, sum);
			}

		}
		out.println(max);
		in.close();
		out.close();
	}

}