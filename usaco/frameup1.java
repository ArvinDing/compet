
/*
ID: dingarv1
LANG: JAVA
TASK: frameup
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class frameup1 {
	private static ArrayList<char[][]> parrallelUniverses;

	private static ArrayList<String> changes;

	public static void main(String[] args) throws Exception {
		// read
		BufferedReader in = new BufferedReader(new FileReader("frameup.in"));
		PrintWriter out = new PrintWriter(new File("frameup.out"));
		StringTokenizer yay = new StringTokenizer(in.readLine());
		int height = Integer.parseInt(yay.nextToken());
		int width = Integer.parseInt(yay.nextToken());
		parrallelUniverses = new ArrayList<char[][]>();
		changes = new ArrayList<String>();
		char[][] info = new char[height][width];
		for (int i = 0; i < height; i++) {
			String abc = in.readLine();
			for (int k = 0; k < width; k++) {
				info[i][k] = abc.charAt(k);
			}
		}
		TreeSet<String> answer = new TreeSet<String>();
		parrallelUniverses.add(info);
		changes.add("");
		while (!parrallelUniverses.isEmpty()) {

			char[][] current = parrallelUniverses.get(0);
			String change = changes.get(0);
			parrallelUniverses.remove(0);
			changes.remove(0);
			int sum = 0;
			for (int i = height - 1; i >= 0; i--) {
				for (int k = 0; k < width; k++) {
					if (checkRectangle(i, k, current, change))
						sum++;
				}
			}
			if (sum == 0 && isEmpty(current)) {
				answer.add(change);
			}

		}
		for (String a : answer) {
			out.println(a);
		}
		out.close();
		in.close();

	}

	public static boolean checkRectangle(int y, int x, char[][] info, String change) {
		int height = 1;
		int width = 1;

		char corner = info[y][x];

		int currX = x;
	
		while (currX + 1 != info[0].length && (info[y][currX + 1] == corner || info[y][currX + 1] == ' ')) {
			
			width++;
			currX++;

		}

		int currY = y;
		while (currY + 1 != info.length && (info[currY + 1][x] == corner || info[currY + 1][x] == ' ')) {
		
			height++;
			currY++;

		}

		if (height < 3 || width < 3) {
			return false;
		}

		for (int i = 0; i < height; i++) {
			if (!((info[y + i][currX] == corner) || info[y + i][currX] == ' ')) {
				
				return false;
			}
		}

		

		for (int i = 0; i < width; i++) {
			if (!(info[currY][x + i] == corner || info[currY][x + 1] == ' ')) {
			
				return false;
			}
		}

	
		char[][] copy = new char[info.length][info[0].length];
		for (int i = 0; i < info.length; i++) {
			for (int k = 0; k < info[0].length; k++) {
				if (((i == y || i == currY) && (k >= x && k <= currX))
						|| ((k == x || k == currX) && (i <= currY && i >= y))) {
					
						copy[i][k] = ' ';
					

				} else {
					copy[i][k] = info[i][k];
				}
			
			}
	
		}
	
		for(int i=0;i<change.length();i++){
			if(change.charAt(i)==corner){
				return false;
			}
		}
		changes.add(change + corner);
		for(int i=0;i<copy.length;i++){
			for(int k=0;k<copy[0].length;k++){
				System.out.print(copy[i][k]);
			}
			System.out.println();
		}
		parrallelUniverses.add(copy);

		return true;

	}

	public static boolean isEmpty(char[][] info) {
		for (int i = 0; i < info.length; i++) {
			for (int k = 0; k < info[0].length; k++) {
				if (!(info[i][k] == '.' || info[i][k] == ' ')) {
					return false;
				}
			}
		}
		return true;
	}
}