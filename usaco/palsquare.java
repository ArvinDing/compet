
/*
ID: dingarv1
LANG: JAVA
TASK: palsquare
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class palsquare {

	
	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("palsquare.out"));
		int base = Integer.parseInt(in.readLine());
		/* the hexadecimal system (base 16), which is used a lot in computer 
		science, the letters A through F are used for digits (since we need 
		digits for each number from 0 to 15).*/
		char[] rep = new char[] {'0','1','2','3','4','5','6','7','8','9',
                'A','B','C','D','E','F','G','H','I','J'};
		
		for(int i=1; i<=300; i++) {
            String square = toBase(i*i,base,rep);
            if (isPalindrome(square)) {
                out.write(toBase(i,base,rep) + " " + square);
                out.newLine();
            }
        }
		out.close();
	}

	private static boolean isPalindrome(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) != input.charAt(input.length() - i - 1))
				return false;
		}
		return true;
	}

	private static String toBase(int i, int base, char[] rep) {
		StringBuilder sb = new StringBuilder();
		int val = i;
		while (val >= base) {
			sb.insert(0, rep[val % base]);
			val = val / base;
		}
		return sb.insert(0, rep[val]).toString();
	}
}
