
import java.io.*;
import java.util.*;

public class shuffle4 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new File("shuffle.out"));
		int cows=Integer.parseInt(in.readLine());
		int []shuffle=new int [cows];
		StringTokenizer read=new StringTokenizer(in.readLine());
		for(int i=0;i<cows;i++){
			shuffle[i]=Integer.parseInt(read.nextToken())-1;
		}
		int []original=new int [cows];
		for(int i=0;i<cows;i++){
			original[i]=i;
		}
		
		for(int i=0;i<3;i++){
			int []old=Arrays.copyOf(original, cows);
			for(int k=0;k<cows;k++){
				original[shuffle[k]]=old[k];
			}
		}
		int []indexes=new int [cows];
		read=new StringTokenizer(in.readLine());
		for(int i=0;i<cows;i++){
			
			int curr=Integer.parseInt(read.nextToken());
			indexes[i]=curr;
		}
		int []ans=new int [cows];
		for(int i=0;i<cows;i++){
			ans[original[i]]=indexes[i];
		}
		for(int i=0;i<cows;i++){
			out.println(ans[i]);
		}
		out.close();
		in.close();
	}
}