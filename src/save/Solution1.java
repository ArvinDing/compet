package save;
import java.util.*;
import java.io.*;
import java.math.*;


class Solution1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i=1; i<=n;i++){
            if(n%i==0){
                String a=i+"";
                int sum=0;
                for(int k=0;k<a.length();k++){
                    sum += Integer.parseInt(""+a.charAt(k));
                }
                if(i*sum==n)  System.out.println("true");
            }
    
    }
        System.out.println( "false");
}
}