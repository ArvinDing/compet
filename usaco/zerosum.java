
/*
ID: dingarv1
LANG: JAVA
TASK: zerosum
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class zerosum {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("zerosum.in"));
		PrintWriter out = new PrintWriter(new File("zerosum.out"));

		int len = in.nextInt();

		List<String> operators = new ArrayList<String>();
		operators.add(" ");
		operators.add("+");
		operators.add("-");
		operators = recursion(len, operators);

		for (int i = 0; i < operators.size(); i++) {
			String foo = "";
			for (int z = 0; z < len; z++) {
				if (z >= operators.get(i).length() || operators.get(i).substring(z, z + 1).equals(" ")) {
					foo += (z + 1) + "";
				} else {
					foo += "" + (z + 1) + operators.get(i).substring(z, z + 1);
				}
			}
			if (eval(foo) == 0) {
				for (int k = 0; k < foo.length() - 1; k++) {
					if (isNumeric(foo.substring(k, k + 1)) && isNumeric(foo.substring(k + 1, k + 2))) {
						foo = foo.substring(0, k + 1) + " " + foo.substring(k + 1);
					}
				}
				out.println(foo);
			}

		}

		

		in.close();
		out.close();
	}

	public static int eval(String foo) {
		int sum = 0;
		foo = "+" + foo;
		for (int i = 0; i < foo.length() - 1; i++) {
			int digit = 1;
			for (int o = i + 1; o < foo.length(); o++) {
				if (isNumeric(foo.substring(o, o + 1))) {
					digit++;
				} else {
					break;
				}
			}
			sum += Integer.parseInt(foo.substring(i, i + digit));
			i += digit - 1;

		}
		return sum;
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static List<String> recursion(int len, List<String> operators) {
		if (operators.get(operators.size() - 1).length() == len - 1) {
			return operators;
		}
		List<String> a = new ArrayList<String>();
		for (String one : operators) {
			a.add(one + " ");
			a.add(one + "+");
			a.add(one + "-");

		}
		return recursion(len, a);
	}

}
