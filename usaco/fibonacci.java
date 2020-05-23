
public class fibonacci {
	public static void main(String[] args) {
//		System.out.println(oneTwo("abc"));
//		System.out.println(oneTwo("tca"));
		System.out.println(oneTwo("abcdefx"));
	}

	public static String oneTwo(String str) {
		String str1 = "";
		if (str.length() < 3) {
			return "";
		}
		if(str.length()%3!=0){
			str=str.substring(0,3*(str.length()/3));
		}
		for (int i = 0; i < str.length(); i=i+3) {
			 str1 = str1+str.substring(i+1,i+3)+str.substring(i,i+1);
			
		}
		return str1;
	}
}
