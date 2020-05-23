
import java.io.*;
import java.util.*;

public class cmexpr {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			String info = in.readLine();
			node tree = recursion(info);
			System.out.println(addParanthesis(tree));

		}
		in.close();
	}

	private static String addParanthesis(node tree) {
		if (tree.left == null)
			return tree.value + "";
		String ans = addParanthesis(tree.left);
		if (tree.value == '*') {
			if (tree.left.value == '+' || tree.left.value == '-')
				ans = add(ans);
			String right = addParanthesis(tree.right);
			if (tree.right.value == '+' || tree.right.value == '-')
				right = add(right);
			ans += tree.value + "";
			ans += right;
			return ans;
		} else if (tree.value == '/') {
			if (tree.left.value == '+' || tree.left.value == '-')
				ans = add(ans);
			String right = addParanthesis(tree.right);
			if (tree.right.value == '+' || tree.right.value == '-' || tree.right.value == '/'
					|| tree.right.value == '*')
				right = add(right);
			ans += tree.value + "";
			ans += right;
			return ans;
		} else if (tree.value == '+') {
			ans += tree.value + "";
			ans += addParanthesis(tree.right);
			return ans;
		} else if (tree.value == '-') {
			String right = addParanthesis(tree.right);
			if (tree.right.value == '-' || tree.right.value == '+')
				right = add(right);
			ans += tree.value + "";
			ans += right;
			return ans;
		}
		return "";
	}

	private static String add(String a) {
		return "(" + a + ")";
	}

	private static node recursion(String info) {

		if (info.length() == 1) {
			return new node(info.charAt(0));
		}
		node curr = null;

		int brackets = 0;
		for (int i = info.length() - 1; i >= 0; i--) {
			if (info.charAt(i) == '(') {
				brackets++;
			} else if (info.charAt(i) == ')') {
				brackets--;
			} else if (brackets == 0) {
				if (info.charAt(i) == '-' || info.charAt(i) == '+') {
					curr = new node(info.charAt(i));
					curr.left = recursion(info.substring(0, i));
					curr.right = recursion(info.substring(i + 1));
					return curr;
				}
			}
		}
		for (int i = info.length() - 1; i >= 0; i--) {
			if (info.charAt(i) == '(') {
				brackets++;
			} else if (info.charAt(i) == ')') {
				brackets--;
			} else if (brackets == 0) {
				if (info.charAt(i) == '*' || info.charAt(i) == '/') {
					curr = new node(info.charAt(i));
					curr.left = recursion(info.substring(0, i));
					curr.right = recursion(info.substring(i + 1));
					return curr;
				}
			}
		}
		return recursion(info.substring(1, info.length() - 1));
	}

	private static class node {
		char value;
		node left, right;

		node(char value) {
			this.value = value;
			left = right = null;
		}
	}

}