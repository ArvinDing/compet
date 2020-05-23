
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class circlecross {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("circlecross.out"));

		int N = Integer.parseInt(in.readLine());
		boolean[] done = new boolean[N+1];
		boolean[][] connections = new boolean[N+1][N+1];
		int[] info = new int[2 * N];
		int numbers = 2 * N;
		for (int i = 0; i < numbers; i++) {
			int a = Integer.parseInt(in.readLine());
			info[i] = a;
		}
		int sum = 0;
		for (int i = 0; i < numbers; i++) {
			int a = info[i];
			if(done[info[i]]==true){
				continue;
			}
			done[info[i]]=true;
			boolean[] link = new boolean[numbers];

			for (int k = i+1; k < numbers; k++) {
				if (info[k] == a) {
					for (boolean c : link) {
						if (c)
							sum++;
					}
					break;
				}
				if (connections[info[k]][a])
					continue;
				if (link[info[k]] == false) {
					link[info[k]] = true;
					connections[a][info[k]] = true;
				} else {
					link[info[k]] = false;
					connections[a][info[k]] = true;
				}

			}

		}
		out.println(sum);
		out.close();
		in.close();

	}
}
