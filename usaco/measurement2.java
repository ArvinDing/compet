
import java.io.*;
import java.util.*;

public class measurement2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("measurement.in"));
		PrintWriter out = new PrintWriter(new File("measurement.out"));
		int lines = Integer.parseInt(in.readLine());
		HashMap<String, Integer> link = new HashMap<String, Integer>();
		link.put("Mildred", 0);
		link.put("Bessie", 1);
		link.put("Elsie", 2);
		int[][] info = new int[101][3];
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[Integer.parseInt(read.nextToken())][link.get(read.nextToken())] += Integer.parseInt(read.nextToken());
		}
		int []board=new int [3];
		List<Integer>temp=new ArrayList<Integer>();
		board[0]=7;
		board[1]=7;
		board[2]=7;
		TreeSet<Integer>leader=new TreeSet<Integer>();
		leader.add(0);
		leader.add(1);
		leader.add(2);
		int cnt=0;
		for (int i = 0; i <= 100; i++) {
			boolean change=false;
			for(int k=0;k<3;k++){
				if(info[i][k]!=0){
					board[k]+=info[i][k];
					change=true;
				}
			}
			if(change){
				int greatest=-1;
				for(int k=0;k<3;k++){
					greatest=Math.max(greatest, board[k]);
				}
				TreeSet<Integer>curr=new TreeSet<Integer>();
				for(int k=0;k<3;k++){
					if(board[k]==greatest){
						curr.add(k);
					}
				}
				if(!curr.equals(leader)){
					leader=new TreeSet<Integer>(curr);
					cnt++;
				}
				
			}
			
		}
		out.println(cnt);
		out.close();
		in.close();
	}
}