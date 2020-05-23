
import java.io.*;
import java.util.*;

public class recording2 {
	private static int[][] info;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("recording.in"));
		PrintWriter out = new PrintWriter(new File("recording.out"));
		int n = Integer.parseInt(in.readLine());
		info = new int[n ][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
		}
		Arrays.sort(info, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		int[] limits = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };
		for (int i = 0; i < info.length; i++) {
			int start = info[i][0];
			int end = info[i][1];
			
			Arrays.sort(limits);
			if(end<=limits[0]){
				limits[0]=start;
			}else if (end<=limits[1]){
				limits[1]=start;
			}else{
				n--;
				//System.out.println(start+" "+end);
			}
		}
		out.println(n);
		in.close();
		out.close();

	}

}
