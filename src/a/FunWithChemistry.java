package a;

public class FunWithChemistry
{
    private static AtomicMassTable table = new AtomicMassTable();
    private static double moleculeWeight;
    public static boolean isNum(char n){
    	return Character.isDigit(n);
      
    }
    public static double elementWeight(String in){
      for (int i = 0; i<in.length();i++){
        if(isNum(in.charAt(i))){
          return table.get(in.substring(0,i))*Integer.parseInt(in.substring(i));
        }
      }
      return table.get(in);
    }
    
    public void parseMolecule(String in){
      double sum = 0;
      int place = 0;
      for (int i =1;i<in.length();i++){
        if (Character.isUpperCase(in.charAt(i))){
          sum += elementWeight(in.substring(place, i));
          place = i;
        }
      }
      sum += elementWeight(in.substring(place));
      moleculeWeight =sum;
    }
    
    public FunWithChemistry(String in){
      parseMolecule(in);
    }
    public double getMoleculeWeight(){
      return moleculeWeight;
    }
}