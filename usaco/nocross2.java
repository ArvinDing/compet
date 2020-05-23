
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class nocross2 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("nocross.out"));
	
		int N = Integer.parseInt(in.readLine());
			int []road1=new int [N];
			int []road2=new int [N];
			for(int i=0;i<N;i++){
				road1[i]=Integer.parseInt(in.readLine());
			}
			for(int i=0;i<N;i++){
				road2[i]=Integer.parseInt(in.readLine());
			}
		
			int sum=0;
			for(int i=0;i<road1.length;i++){
				int num=road1[i];
				for(int k=0;k<road2.length;k++){
					if(Math.abs(road2[k]-num)<=4){
						sum++;
					}
				}
			}
		//incomplete
			
			out.println(sum);
			out.close();
			in.close();
			
	}

}
