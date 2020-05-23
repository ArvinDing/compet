
/*
ID: dingarv1
LANG: JAVA
TASK: heritage
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class heritage {
	private static PrintWriter out;
	private static String inO;
	private static String preO;
	private static int preI;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("heritage.in"));
		out = new PrintWriter(new File("heritage.out"));
		inO = in.readLine();
		preO = in.readLine();

		postP(recursion(inO));
		out.println();
		in.close();
		out.close();
	}

	private static void postP(Node a) {
		if (a.left != null) {
			postP(a.left);
		}
		if (a.right != null) {
			postP(a.right);
		}
		out.print(a.value);
	}

	private static Node recursion(String inO) {
		if (inO.length() == 0) {
			return null;
		}
		int split = inO.indexOf(preO.charAt(preI));
		Node a = new Node(preO.charAt(preI));
		preI++;

		a.left = recursion(inO.substring(0, split));
		a.right = recursion(inO.substring(split + 1));
		return a;
	}

	public static class Node {
		Node left;
		Node right;
		Node parent;
		char value;

		public Node(char value) {
			this.value = value;

		}
	}
}