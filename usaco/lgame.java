
/*
ID: dingarv1
LANG: JAVA
TASK: lgame
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public class lgame {
	private static int values[] = { 2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7 };
	private static String info;
	private static List<String>[][] dictionary;

	public static void main(String[] args) throws Exception {
		// read
		BufferedReader in = new BufferedReader(new FileReader("lgame.in"));
		BufferedReader dict = new BufferedReader(new FileReader("lgame.dict"));
		PrintWriter out = new PrintWriter(new File("lgame.out"));
		info = in.readLine();
		int[] input = new int[26];
		for (int i = 0; i < info.length(); i++) {
			input[info.charAt(i) - 'a']++;
		}
		dictionary = new ArrayList[26][26];
		for (int i = 0; i < 26; i++) {
			for (int k = 0; k < 26; k++) {
				dictionary[i][k] = new ArrayList<String>();
			}
		}
		String abc = dict.readLine();
		while (!abc.equals(".")) {
			dictionary[abc.charAt(0) - 'a'][abc.charAt(1) - 'a'].add(abc);
			abc = dict.readLine();
		}

		// main alg
		List<string2> answers = new ArrayList<string2>();
		boolean[][] flagged = new boolean[26][26];
		for (int i = 0; i < info.length(); i++) {
			for (int k = 0; k < info.length(); k++) {
				if (i == k || flagged[info.charAt(i) - 'a'][info.charAt(k) - 'a']) {
					continue;
				}
				for (String curr : dictionary[info.charAt(i) - 'a'][info.charAt(k) - 'a']) {
					System.out.println(curr);
					if (checkMatch(curr, input)) {
						List<String> addition = new ArrayList<String>();
						if (info.length() - curr.length() >= 3) {

							addition = bestMatch(curr, input);

						}
						for (String add : addition) {
							answers.add(new string2(curr, add));
						}
						if (addition.size() == 0) {
							answers.add(new string2(curr, ""));
						}
					}
				}

				flagged[info.charAt(i) - 'a'][info.charAt(k) - 'a'] = true;
			}
		}

		// finding the best
		int bestscore = Integer.MIN_VALUE;
		TreeSet<String> best = new TreeSet<String>();
		for (string2 i : answers) {
			String a = i.a;
			String b = i.b;
			String end = "";
			if (a.length() == 0) {
				end = b;
			} else if (b.length() == 0) {
				end = a;
			} else {
				end = a +" "+ b;
			}
			int score = caculateScore(a);
			score += caculateScore(b);
			if (score > bestscore) {
				bestscore = score;
				best = new TreeSet<String>();
				best.add(end);
			} else if (score == bestscore) {
				best.add(end);
			}
		}

		// printing everything
		out.println(bestscore);
		for (String i : best) {
			out.println(i);

		}
		out.close();
		in.close();
		dict.close();
	}

	private static List<String> bestMatch(String curr, int[] input) {

		int[] abc = Arrays.copyOf(input, input.length);
		for (int i = 0; i < curr.length(); i++) {
			abc[curr.charAt(i) - 'a']--;
		}

		String modified = "";
		for (int i = 0; i < abc.length; i++) {
			for (int k = 0; k < abc[i]; k++) {
				modified += (char) (i + 97);

			}
		}
		List<String> answers = new ArrayList<String>();
		for (int i = 0; i < modified.length(); i++) {
			for (int k = 0; k < modified.length(); k++) {

				if (i == k) {
					continue;
				}
				for (String name : dictionary[modified.charAt(i) - 'a'][modified.charAt(k) - 'a']) {
					if (checkMatch(name, abc))
						answers.add(name);
				}

			}
		}
		int bestScore = Integer.MIN_VALUE;
		List<String> best = new ArrayList<String>();
		for (String z : answers) {
			int o = caculateScore(z);
			if (o > bestScore) {
				best = new ArrayList<String>();
				best.add(z);
				bestScore = o;
			} else if (o == bestScore) {
				best.add(z);
			}
		}
		return best;
	}

	private static class string2 {
		String a;
		String b;

		private string2(String a, String b) {
			if (a.compareTo(b) < 0) {
				this.a = a;
				this.b = b;
			} else {
				this.b = a;
				this.a = b;
			}
		}
	}

	private static int caculateScore(String str) {
		int answer = 0;
		for (int i = 0; i < str.length(); i++) {
			answer += values[str.charAt(i) - 'a'];
		}
		return answer;
	}

	private static boolean checkMatch(String str, int[] input) {
		int[] abc = Arrays.copyOf(input, input.length);
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i) - 'a';
			if (abc[index] <= 0) {
				return false;
			}
			abc[index]--;
		}
		return true;
	}

}