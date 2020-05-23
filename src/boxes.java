import java.io.IOException;
import java.util.*;

public class boxes extends Grader {

	static int n, q;
	
	
	
  	public static void main(String args[]) throws IOException { new boxes().run(); }
	
	@Override
	public void addRoad(int a, int b) {
		n = getN();
		q = getQ();
		List<Integer>[] info = new List[n];
		if (info[a] == null)
			info[a] = new ArrayList<Integer>();
		if (info[b] == null)
			info[b] = new ArrayList<Integer>();
		info[a].add(b);
		info[b].add(a);
	}

	@Override
	public void buildFarms() {
		// Fill in code here
		for (int i = 0; i < n; i++)
			setFarmLocation(i, i+1, 1);
	}

	
	@Override
	public void notifyFJ(int a, int b) {
		// Fill in code here
		addBox(1, 1, n+1, 1);
	}
}