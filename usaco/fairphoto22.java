
import java.io.*;
import java.util.*;

public class fairphoto22 {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fairphoto.in"));
		PrintWriter out = new PrintWriter(new File("fairphoto.out"));
		int lines = Integer.parseInt(in.readLine());

		TreeMap<Integer, Boolean> info = new TreeMap<Integer, Boolean>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int index = Integer.parseInt(read.nextToken());
			boolean type = read.nextToken().equals("G");
			info.put(index, type);

		}
		int[][] save = new int[lines+1][2];
		Iterator<Boolean> temp = info.values().iterator();
		save[0][0] = 0;
		save[0][1] = 0;
		
		int index = 1;
		while (temp.hasNext()) {
			save[index][0] = save[index - 1][0];
			save[index][1] = save[index - 1][1];
			if (temp.next()) {
				save[index][0]++;
			} else {
				save[index][1]++;
			}
			index++;
		}
		int max = 0;
		Set<Integer> indexes = info.keySet();
		List<Integer> important = new ArrayList<Integer>();
		important.add(0);
		important.addAll(indexes);
		System.out.println(System.currentTimeMillis());
		outer: for (int i = lines; i >= 0; i--) {
			if(important.get(i) - important.get(1) < max){
				break outer;
			}
			for (int k = 0; k < i; k++) {
				if (important.get(i) - important.get(k+1) < max) {
					continue outer;
				}
				if (save[i][0] - save[k][0] == save[i][1] - save[k][1] || save[i][1] - save[k][1] == 0
						|| save[i][0] - save[k][0] == 0) {
					max = Math.max(max, important.get(i) - important.get(k+1));
					continue outer;
				}
			}
		}
		System.out.println(System.currentTimeMillis());
		out.println(max);
		in.close();
		out.close();
	}

}