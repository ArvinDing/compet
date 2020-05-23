
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class lostcow {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lostcow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lostcow.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int farmer = Integer.parseInt(read.nextToken());
		int cow = Integer.parseInt(read.nextToken());
		boolean temp = cow > farmer;
		int total = 0;
		int move = 1;
		while (farmer != cow) {
			farmer += move;
			total+=Math.abs(move);
			if (farmer > cow == temp) {
				total-=Math.abs(farmer-cow);
				break;
			}
			farmer-=move;
			total+=Math.abs(move);
			move *= -2;
		
		}
		out.println(total);
		out.close();
		in.close();
	}

}