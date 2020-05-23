package camp;
import java.io.*;
import java.util.*;

public class lazyCow {
	private static int [] segmentT;
	public static void main(String[]args) throws Exception {
		BufferedReader in =new BufferedReader(new FileReader ("lazy.in"));
		PrintWriter out =new PrintWriter(new File("lazy.out"));
		StringTokenizer read=new StringTokenizer(in.readLine());
		int n=Integer.parseInt(read.nextToken());
		int k=Integer.parseInt(read.nextToken());
		int info[][]=new int [n][3];
		segmentT=new int [n];
		for(int i=0;i<n;i++) {
			read=new StringTokenizer(in.readLine());
			info[i][0]=Integer.parseInt(read.nextToken());
			info[i][1]=Integer.parseInt(read.nextToken());
			info[i][2]=Integer.parseInt(read.nextToken());
		}
		Arrays.sort(info,new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1]-arg1[1];
			}
			
		});
	}
	public static void query(int start,int end,int idx) {
		
	}
	
}
