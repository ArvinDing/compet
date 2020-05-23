import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

class SubsetGenerator {
	private String str;

	public SubsetGenerator(String input) {
		this.str = input;
	}

	public List<String> getSubsets() {
		if (str.length() == 0) {
			List<String> lol = new ArrayList<String>();
			lol.add("");
			return lol;
		}
		List<String> all = new ArrayList<String>();
		List<String> temp = new SubsetGenerator(str.substring(1)).getSubsets();
		for (String a : temp) {
			all.add(a);
			all.add(str.substring(0, 1) + a);
		}
		return all;
	}
}