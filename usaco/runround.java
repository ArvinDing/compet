
/*
ID: dingarv1
LANG: JAVA
TASK: runround
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class runround {
	public static void main(String[] args) throws IOException {
		

		Scanner in = new Scanner(new File("runround.in"));
		PrintWriter pw = new PrintWriter(new File("runround.out"));
		int N = in.nextInt();
		in.close();
	
				
		int i;
		for(i = N + 1; ! isRunRound(Integer.toString(i)); i++);
		pw.println(i);
		pw.close();
		

	}

	private static boolean isRunRound(String num) {
		HashSet <Integer> covered = new HashSet <Integer> ();
		int cnt = 0;
		int digitPos = 0;
		while(cnt < num.length()) {
			int digit = Character.getNumericValue(num.charAt(digitPos));
			int newPos = (digit % num.length() + digitPos) % num.length();
			if(covered.contains(Character.getNumericValue(num.charAt(newPos)))) return false;
			digitPos = newPos;
			cnt++;
			covered.add(Character.getNumericValue(num.charAt(newPos)));
		}
		return true;
	}
}
