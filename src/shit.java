import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class shit {

	public static List<Integer> findSubsequence(List<Integer> arr) {
		TreeMap<Integer, LinkedList<Integer>> info = new TreeMap<Integer, LinkedList<Integer>>();
		int n = arr.size();
		for (int i = 0; i < n; i++) {
			if (!info.containsKey(arr.get(i)))
				info.put(i, new LinkedList<Integer>());
			info.get(arr.get(i)).add(i);
		}
		LinkedList<Integer> ans = new LinkedList<Integer>();
		int last = -1;
		for (Entry<Integer, LinkedList<Integer>> curr : info.entrySet()) {
			LinkedList<Integer> temp = (curr.getValue());
			if (temp.size() <= 1)
				continue;
			int nextLast = last;
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) > last) {
					nextLast = temp.get(i);
					ans.add(curr.getKey());
				}
			}
			
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(findSubsequence(Arrays.asList(new Integer[] { 2, 1, 3, 1, 4, 1, 3 })));
	}
}
