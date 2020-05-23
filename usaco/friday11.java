/*
ID: dingarv1
LANG: JAVA
TASK: friday
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

public class friday11 {

	public static void main(String[] argv) throws IOException {
		int mon = 0;
		int tue = 0;
		int wed = 0;
		int thu = 0;
		int fri = 0;
		int sat = 0;
		int sun = 0;
		int firstDay = 1;

		BufferedReader in = new BufferedReader(new FileReader("friday.in"));
		int years = Integer.parseInt(in.readLine());
		int[] months = new int[] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		for (int x = 1900; x < years + 1900; x++) {
			if (isLeapYear(x)) {
				months[1] = 29;
			} else {
				months[1] = 28;
			}
			for (int i = 0; i < 12; i++) {
				if (firstDay == 1) {
					sun++;
				} else if (firstDay == 2) {
					mon++;
				} else if (firstDay == 3) {
					tue++;
				} else if (firstDay == 4) {
					wed++;
				} else if (firstDay == 5) {
					thu++;
				} else if (firstDay == 6) {
					fri++;
				} else {
					sat++;
				}
				firstDay=(firstDay+months[i])%7;

			}
		}

		BufferedWriter out = new BufferedWriter(new FileWriter("friday.out"));
		out.write(sun + " " + mon + " " + tue + " " + wed + " " + thu + " " + fri+" "+sat );
		out.newLine();
		out.close();
	}

	public static boolean isLeapYear(int year) {
		if (year % 100 == 0 && year % 400 != 0)
			return false;
		if (year % 4 == 0)
			return true;
		return false;
	}

}
