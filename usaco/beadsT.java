
/*
ID: dingarv1
LANG: JAVA
TASK: beads
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
import java.util.TreeSet;

public class beadsT {

	public static void main(String[] argv) throws IOException {
		int top = 0;
		int bottom = 0;

		int Oanswer = 0;
		int Canswer = 0;

		BufferedReader in = new BufferedReader(new FileReader("beads.in"));
		int terms = Integer.parseInt(in.readLine());
		String beads = in.readLine();
		beads += beads + beads;
		for (int i = terms; i < 2 * terms; i++) {
			 bottom = 0;
			if (beads.charAt(i) == 'b') {
				top = beads.indexOf('r', i ) - i ;
			} else if (beads.charAt(i ) == 'w') {
				int a = i ;
				while (beads.charAt(a) == 'w'&&a<3*terms-1) {
					a++;
				}
				if (beads.charAt(a) == 'b') {
					top = beads.indexOf('r', a ) - i;

				} else {
					top = beads.indexOf('b', a ) - i;
				}
			} else if (beads.charAt(i ) == 'r') {
				top = beads.indexOf('b', i ) - i ;
			}
			
			if (beads.charAt(i - 1) == 'b') {
				for (int z = i - 1; z > 0; z--) {
					if (beads.charAt(z) == 'r') {
						break;
					}
					bottom++;
				}
			} else if (beads.charAt(i - 1) == 'w') {
				int b = i - 1;
				while (beads.charAt(b) == 'w'&&b>1) {
					b--;
				}
				if (beads.charAt(b) == 'b') {
					for (int z = b; z > 0; z--) {
						if (beads.charAt(z) == 'r') {
							break;
						}
						bottom++;
					}
				} else {
					for (int z =b; z > 0; z--) {
						if (beads.charAt(z) == 'r') {
							break;
						}
						bottom++;
					}
				}
			} else if (beads.charAt(i - 1) == 'r') {
				for (int z = i - 1; z > 0; z--) {
					if (beads.charAt(z) == 'b') {
						break;
					}
					bottom++;
				}
			}
			if(top<0||bottom<0){
				Oanswer=terms;
				break;
			}
			Canswer = top + bottom;
			
			if (Canswer > Oanswer) {
				Oanswer = Canswer;
			}
		}
		BufferedWriter out = new BufferedWriter(new FileWriter("beads.out"));
		if(Oanswer>terms){
			Oanswer=terms;
		}
		out.write(String.valueOf(Oanswer));
		out.newLine();
		out.close();

	}
}
