
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class greetings {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("greetings.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("greetings.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int bessieL = Integer.parseInt(read.nextToken());
		int elsieL = Integer.parseInt(read.nextToken());
		int[] bessie = new int[1000001];
		int bessieIndex = 0;
		int bessieOld = 0;
		for (int i = 0; i < bessieL; i++) {
			read = new StringTokenizer(in.readLine());

			int a = Integer.parseInt(read.nextToken());
			if (read.nextToken().equals("L")) {
				for (int k = 0; k < a; k++) {
					bessie[bessieIndex] = bessieOld--;

					bessieIndex++;
				}
			} else {
				for (int k = 0; k < a; k++) {
					bessie[bessieIndex] = bessieOld++;

					bessieIndex++;
				}
			}
		}
		int[] elsie = new int[1000001];
		int elsieIndex = 0;
		int elsieOld = 0;
		for (int i = 0; i < elsieL; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken());
			if (read.nextToken().equals("L")) {
				for (int k = 0; k < a; k++) {
					elsie[elsieIndex] = elsieOld--;

					elsieIndex++;
				}
			} else {
				for (int k = 0; k < a; k++) {
					elsie[elsieIndex] = elsieOld++;

					elsieIndex++;
				}
			}
		}
		bessie[bessieIndex] = bessieOld;
		elsie[elsieIndex] = elsieOld;
		bessieIndex++;
		elsieIndex++;
		if (elsieIndex < bessieIndex) {
			for (int i = elsieIndex; i < bessieIndex; i++) {
				elsie[i] = elsie[elsieIndex - 1];
			}
		} else if (bessieIndex < elsieIndex) {
			for (int i = bessieIndex; i < elsieIndex; i++) {
				bessie[i] = bessie[bessieIndex - 1];
			}
		}
		int answer = 0;
		boolean old = elsie[0] == bessie[0];
		int max = Math.max(elsieIndex, bessieIndex);
		for (int i = 1; i < max; i++) {
			if (!old && bessie[i] == elsie[i]) {
				answer++;
		//		System.out.println(i);
			}
			old = elsie[i] == bessie[i];
		}

		out.println(answer);
		in.close();
		out.close();
	}
}
