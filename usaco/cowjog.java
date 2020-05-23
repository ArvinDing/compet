
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowjog {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowjog.in"));
		PrintWriter out = new PrintWriter(new File("cowjog.out"));
		int length = Integer.parseInt(in.readLine());
		int speed = Integer.MAX_VALUE;
		int answer = length;
		int[] info = new int[100000];
		for (int i = 0; i < length; i++) {

			StringTokenizer read = new StringTokenizer(in.readLine());
			read.nextToken();
			info[i] = Integer.parseInt(read.nextToken());

		}
		for(int i=length-1;i>=0;i--){
			int one=info[i];
			if(one<=speed){
				speed=one;
			}else{
				answer--;
			}
		}
		out.println(answer);
		out.close();
		in.close();
	}
}