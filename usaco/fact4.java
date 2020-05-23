/*
ID: dingarv1
LANG: JAVA
TASK: fact4
*/

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class fact4 {
	private static List<Integer> answer;
	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new File("fact4.out"));

		int n = in.nextInt();
		 answer = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			if(i%10!=0){
			answer.add(i);
			}
		}
		int abc=purge52();
		int last=1;
		for(int i:answer){
			if(i==1){
				continue;
			}
			last=last*i;
			if(last>=10){
				last=last%10;
			}
		}
		while ((abc--) > 0) {
			last=last*2;
			if(last>=10){
				last=last%10;
			}
		}
		last=last%10;
		out.println(last);
		in.close();
		out.close();

	}
	public static int purge52(){
		int cnt5 = 0;
		int cnt2=0;
		for(int i=0;i<answer.size();i++){
			int value;
			while((value = answer.get(i))%2==0){
				answer.set(i, value/2);
				cnt2++;
			}
			while((value = answer.get(i))%5==0){
				answer.set(i, value/5);
				cnt5++;
			}
		}
		return cnt2-cnt5;
	}
}