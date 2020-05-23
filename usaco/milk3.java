
/*
ID: six.sal2
LANG: JAVA
TASK: milk3
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class milk3 {

	boolean[][] searched;
	ArrayList<Integer> cAmount;
	int[] bucketFull;
	
	public milk3(int []buckets){
		bucketFull = new int[3];
		System.arraycopy(buckets, 0, bucketFull, 0, 3);
		searched = new boolean[bucketFull[0]+1][bucketFull[1]+1];
		cAmount = new ArrayList<Integer>();
		
	}
	
	private void pour(int[] buckets, int from, int to){
		if(buckets[from]==0 || buckets[to]==bucketFull[to]){
			return;
		}
		int[] newBuckets = new int[3];
		System.arraycopy(buckets, 0, newBuckets, 0, 3);
		
		int eAmount = Math.min(bucketFull[to]-buckets[to], buckets[from]);
		newBuckets[to] += eAmount;
		newBuckets[from] -= eAmount;
		dfs(newBuckets);
	}
	
	private void dfs(int[] buckets){
		int a = buckets[0];
		int b = buckets[1];
		int c = buckets[2];
		if(searched[a][b]){
			return;
		}
		searched[a][b] = true;
		if(a==0){
			cAmount.add(c);
		}
		pour(buckets, 0, 1);
		pour(buckets, 0, 2);
		pour(buckets, 1, 0);
		pour(buckets, 1, 2);
		pour(buckets, 2, 0);
		pour(buckets, 2, 1);
	}
	
	public String getPossibleAmounts(){
		int[] initialBuckets = new int[3];
		initialBuckets[2] = bucketFull[2];
		dfs(initialBuckets);
		Collections.sort(cAmount);
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<cAmount.size(); i++){
			sb.append(cAmount.get(i)).append(" ");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	
	
	public static void main(String[] args) throws IOException{
		

		Scanner in = new Scanner(new File("milk3.in"));
		
		
		int[] buckets = new int[3];
		for(int i = 0; i<3; i++){
			buckets[i] = in.nextInt();
		}
		String possibleAmounts = new milk3(buckets).getPossibleAmounts();
		BufferedWriter out = new BufferedWriter(new FileWriter("milk3.out"));
		out.write(possibleAmounts);
		out.newLine();
		out.close();
		in.close();
		System.exit(0);
		
	}

	   

}
