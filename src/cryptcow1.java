
/*
ID: dingarv1
LANG: JAVA
TASK: cryptcow
*/

import java.io.*;
import java.util.*;

public class cryptcow1 {
	static char[] goal = "Begin the Escape execution at the Break of Dawn".toCharArray();
	static BitSet[] hash;
	static final int mod = 1000007;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cryptcow.in"));
		PrintWriter out = new PrintWriter(new File("cryptcow.out"));
		char[] info = in.readLine().toCharArray();
		for (int i = 0; i < info.length; i++)
			if (info[i] == ' ')
				info[i] = '^';
		for (int i = 0; i < goal.length; i++)
			if (goal[i] == ' ')
				goal[i] = '^';
		hash = new BitSet[goal.length + 1];
		for (int i = 1; i <= goal.length; i++) {
			hash[i] = new BitSet(mod);
			int hashC = 0;
			int k = 0;
			int pow = 1;
			for (k = 0; k < i; k++) {
				pow = (pow * 57) % mod;
				hashC = (hashC * 57) % mod;
				hashC += (goal[k] - 65);
			}
			for (; k < goal.length; k++) {
				hash[i].set(hashC);
				;
				hashC = (hashC * 57) % mod;
				hashC = (hashC - (pow * (goal[k - i] - 65) % mod));
				hashC = (hashC + mod) % mod;
				hashC += (goal[k] - 65);
			}
			hash[i].set(hashC);
		}
		int[] number = new int[57];
		for (int i = 0; i < info.length; i++) {
			number[info[i] - 65]++;
		}
		for (int i = 0; i < goal.length; i++) {
			number[goal[i] - 65]--;
		}
		LinkedList<Character> input = new LinkedList<Character>();
		for (char a : info)
			input.add(a);
		if (!firstC(number, info)) {
			out.println(0 + " " + 0);
		} else {
			int encryptions = number[2];
			boolean res = recursion(0, goal.length - 1, input, encryptions);
			if (res) {
				out.println(1 + " " + encryptions);
			} else {
				out.println(0 + " " + 0);
			}
		}
		out.close();
		in.close();
	}

	static boolean firstC(int[] number, char[] info) {
		if (info.length < 47 || (info.length - 47) % 3 != 0) {
			return false;
		}
		for (int i = 0; i < 57; i++) {
			if (i == 2 || i == 14 || i == 22)
				continue;
			if (number[i] != 0)
				return false;
		}
		return number[2] == number[14] && number[14] == number[22];
	}

	static int[] check(LinkedList<Character> info, int encryptC) {
		int[] out = new int[encryptC * 3 + 2];
		out[0] = -1;
		int idx = 1;
		Iterator<Character> itr = info.iterator();
		int infoI = 0;
		while (itr.hasNext()) {
			char curr = itr.next();
			if (curr == 'C' || curr == 'O' || curr == 'W') {
				if (idx == 1 && curr != 'C') {
					return null;
				}
				if (idx == out.length - 2 && curr != 'W') {
					return null;
				}
				out[idx++] = infoI;
			}
			infoI++;
		}

		out[out.length - 1] = info.size();
		return out;
	}

	static boolean recursion(int goalS, int goalE, LinkedList<Character> input, int encryptC) {
		System.out.println(encryptC);
		int[] imp = check(input, encryptC);
		if (imp == null)
			return false;
		int inputI = 0;
		Iterator<Character> itr = input.iterator();
		for (int i = 0; i < imp.length - 1; i++) {
			int start = imp[i] + 1;
			int end = imp[i + 1] - 1;
			if (start < end) {
				int length = end - start + 1;
				if (length > goal.length)
					return false;
				int hashC = 0;
				while (inputI < start) {
					itr.next();
					inputI++;
				}
				for (int k = start; k <= end; k++) {
					hashC = (hashC * 57) % mod;
					char a = itr.next();
					// System.out.print(a);
					hashC += (a - 65);
					inputI++;
				}
				// System.out.println();
				if (!hash[length].get(hashC))
					return false;
			}
		}
		if (encryptC == 0)
			return true;
		int last = imp[imp.length - 2];
		int idx = input.size() - 1;
		while (idx > last) {
			if (input.peekLast() != goal[goalE])
				return false;
			goalE--;
			idx--;
			input.removeLast();
		}
		int first = imp[1];
		idx = 0;
		while (idx < first) {
			if (input.peek() != goal[goalS])
				return false;
			goalS++;
			idx++;
			input.removeFirst();
		}
		input.removeFirst();
		input.removeLast();

		LinkedList<Character> start = new LinkedList<Character>();
		while (!input.isEmpty()) {
			char curr = input.poll();
			if (curr == 'O') {
				LinkedList<Character> temp = new LinkedList<Character>();
				temp.addAll(input);
				temp.addAll(start);
				if (recursion(goalS, goalE, temp, encryptC - 1))
					return true;
			}
			start.add(curr);
		}
		return false;
	}

}
