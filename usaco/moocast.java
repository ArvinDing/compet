
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class moocast {
	private static boolean[][] connections;
	private static int size;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		 size = Integer.parseInt(in.readLine());
		List<cow> info = new ArrayList<cow>();
		for (int i = 0; i < size; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info.add(new cow(Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()),
					Integer.parseInt(read.nextToken())));
		}
		connections = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				if (i == k)
					continue;
				int dX = (info.get(k).x - info.get(i).x);
				int dY = (info.get(k).y - info.get(i).y);
				if ((dX * dX + dY * dY) <= info.get(i).power * info.get(i).power) {
					connections[i][k] = true;
				}
			}
		}
		int max = Integer.MIN_VALUE;
		dp=new HashSet[size];
		for (int start = 0; start < size; start++) {
			HashSet<Integer>name=total(start);
			name.add(start);
			max = Math.max(name.size(), max);
		}
		out.println(max);
		in.close();
		out.close();
	}


	private static HashSet<Integer>[] dp; 
	private static HashSet<Integer> total(int num) {
		if(dp[num]!=null){
			return dp[num];
		}
		dp[num] = new HashSet<Integer>();
		for(int i=0;i<size;i++){
			if(connections[num][i]) {
				dp[num].add(i);
				dp[num].addAll(total(i));
			}
			
		}
		return dp[num];
	}

	private static class cow {
		int x, y, power;

		cow(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}

}