import java.util.*;
import java.io.*;

public class parathenses {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		 for (int i = 0; i < test; i++) {
			char[] info = in.readLine().toCharArray();
			LinkedList<Integer> stack = new LinkedList<Integer>();
			int curr = 0;
			int max = 0;
			for (char a : info) {
				if (a == '(') {	
					stack.push(curr);
					curr = 0;
				} else if (a == ')' && stack.size() > 0) {
					curr +=stack.pop()+2;
				
					max = Math.max(max, curr);
				} else if (a == ')' && stack.isEmpty())
					curr = 0;
			}
			System.out.println(max);
		}
		in.close();
	}
}
