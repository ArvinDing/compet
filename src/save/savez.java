package save;

import java.io.*;
import java.util.*;
import java.math.*;

public class savez {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		PrintWriter out = new PrintWriter(new File("output"));

		in.close();
		out.close();
	}

	public class node {
		node child;
		char curr;
		int cnt;

		public node() {
			this.cnt = 0;
		}
	}
}
