
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class photoN {

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("photo.in"));
		PrintWriter out = new PrintWriter(new FileWriter("photo.out"));
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		StringTokenizer read = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(read.nextToken());
		int line = Integer.parseInt(read.nextToken());
		for (int i = 0; i < line; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken());
			int b = Integer.parseInt(read.nextToken());
			int big = Math.max(a, b);
			int small = Math.min(a, b);
			if (!info.containsKey(small)) {
				info.put(small, big);
			} else
				info.put(small, Math.min(info.get(small), big));
		}
		int limit=Integer.MAX_VALUE;
		int photos=1;
		info.put(N,-1);
		for(Entry<Integer,Integer>a:info.entrySet()){
			if(limit==-1)break;
			if(a.getKey()<limit){
				limit=Math.min(a.getValue(), limit);
			}else{
				photos++;
				limit=a.getValue();
			}
		}
		out.println(photos);
		in.close();
		out.close();

	}

}
