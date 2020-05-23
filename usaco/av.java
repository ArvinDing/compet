import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class av {
	static class Fraction {                  // Avoids floating-point trouble.
		  int num, denom;
		  static int gcd(int a, int b) {  // Greatest common divisor.
		    while (b != 0) {
		      int t = b;
		      b = a % b;
		      a = t;
		    }
		    return a;
		  }
		  Fraction(int num, int denom) {  // Makes a simplified fraction.
		    if (denom == 0) {             // Division by zero results in
		      this.num = this.denom = 0;  //  the fraction 0/0. We do not
		    } else {                      //  throw an error.
		      int x = Fraction.gcd(num, denom);
		      this.num = num / x;
		      this.denom = denom / x;     
		    }
		  }
		  Fraction plus(Fraction other) {
		    return new Fraction(this.num * other.denom + other.num * this.denom,
		        this.denom * other.denom);
		  }
		  Fraction minus(Fraction other) {
		    return this.plus(new Fraction(-other.num, other.denom));
		  }
		  Fraction times(Fraction other) {
		    return new Fraction(this.num * other.num, this.denom * other.denom);
		  }
		  Fraction divide(Fraction other) {
		    return new Fraction(this.num * other.denom, this.denom * other.num);
		  }
		  public String toString() {      // Omits the denominator if possible.
		    if (denom == 1) {
		      return ""+num;
		    }
		    return num+"/"+denom;
		  }
		}

		static class Expression {                // A tree node containing a value and
		  Fraction value;                 //  optionally an operator and its
		  String operator;                //  operands.
		  Expression left, right;
		  static int level(String operator) {
		    if (operator.compareTo("+") == 0 || operator.compareTo("-") == 0) {
		      return 0;                   // Returns the priority of evaluation,
		    }                             //  also known as operator precedence
		    return 1;                     //  or the order of operations.
		  }
		  Expression(int x) {             // Simplest case: a whole number.
		    value = new Fraction(x, 1);
		  }
		  Expression(Expression left, String operator, Expression right) {
		    if (operator == "+") {
		      value = left.value.plus(right.value);
		    } else if (operator == "-") {
		      value = left.value.minus(right.value);
		    } else if (operator == "*") {
		      value = left.value.times(right.value);
		    } else if (operator == "/") {
		      value = left.value.divide(right.value);
		    }
		    this.operator = operator;
		    this.left = left;
		    this.right = right;
		  }
		  public String toString() {     
		    if (operator == null) {       //  inserting parentheses only where
		      return value.toString();    //  necessary to avoid ambiguity.
		    }
		    int level = Expression.level(operator);
		    String a = left.toString(), aOp = left.operator,
		           b = right.toString(), bOp = right.operator;
		    if (aOp != null && Expression.level(aOp) < level) {
		      a = "("+a+")";              // Parenthesize the child only if its
		    }                             //  priority is lower than the parent's.
		    if (bOp != null && Expression.level(bOp) < level) {
		      b = "("+b+")";
		    }
		    return a + " " + operator + " " + b;
		  }
		}

		public static class Equation {

		
		  static int need = 4, min = 1, max = 13, target = 24;

		  int[] terms, permutation;
		  boolean[] used;
		  ArrayList<String> wins = new ArrayList<String>();
		  Set<String> winSet = new HashSet<String>();
		  String[] operators = {"+", "-", "*", "/"};

		  
		  ArrayList<Expression> make(int left, int right) {
		    ArrayList<Expression> result = new ArrayList<Expression>();
		    if (left+1 == right) {
		      result.add(new Expression(permutation[left]));
		    } else {
		      for (int i = left+1; i < right; ++i) {
		        ArrayList<Expression> leftSide = make(left, i);
		        ArrayList<Expression> rightSide = make(i, right);
		        for (int j = 0; j < leftSide.size(); ++j) {
		          for (int k = 0; k < rightSide.size(); ++k) {
		            for (int p = 0; p < operators.length; ++p) {
		              result.add(new Expression(leftSide.get(j),
		                    operators[p],
		                    rightSide.get(k)));
		            }
		          }
		        }
		      }
		    }
		    return result;
		  }

		 
		  void formulate() {
		    ArrayList<Expression> expressions = make(0, terms.length);
		    for (int i = 0; i < expressions.size(); ++i) {
		      Expression expression = expressions.get(i);
		      Fraction value = expression.value;
		      if (value.num == target && value.denom == 1) {
		        String s = expressions.get(i).toString();
		        if (!winSet.contains(s)) {
		          wins.add(s);            
		          winSet.add(s);         
		        }
		      }
		    }
		  }

		 
		  void permute(int termIx, int pos) {
		    if (pos == terms.length) {
		      return;
		    }
		    if (!used[pos]) {
		      permutation[pos] = terms[termIx];
		      if (termIx+1 == terms.length) {
		        formulate();
		      } else {
		        used[pos] = true;
		        if (terms[termIx+1] == terms[termIx]) {
		          permute(termIx+1, pos+1);
		        } else {
		          permute(termIx+1, 0);
		        }
		        used[pos] = false;
		      }
		    }
		    permute(termIx, pos+1);
		  }

		
		  void solve(int[] terms) throws NumberFormatException, ScriptException {
			  for (int i = 0; i < wins.size(); ++i) {
			    	 ScriptEngineManager mgr = new ScriptEngineManager();
			    	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
			    	   String foo= wins.get(i);
			    	    if(Integer.parseInt((String) (engine.eval(foo)))!= 24){
			    	    	wins.remove(i);
			    	    }
				    }
		    this.terms = terms;           
		    Arrays.sort(terms);         
		    permutation = new int[terms.length];
		    used = new boolean[terms.length];
		    permute(0, 0);
		    if (wins.size() == 0) {
		      System.out.println("There are no expressions.");
		    } else {
		      System.out.println("There are "+wins.size()+"  expressions:");
		    }
		   
		    for (int i = 0; i < wins.size(); ++i) {
		      System.out.println(wins.get(i) + " = " + target);
		    }
		  }

		
		  public static void main(String[] args) throws NumberFormatException, ScriptException {
		    if (args.length != need) {
		      System.out.println("must specify "+need+" digits");
		      return;
		    }
		    int digits[] = new int[need];
		    for (int i = 0; i < need; ++i) {
		      try {
		        digits[i] = Integer.parseInt(args[i]);
		      } catch (NumberFormatException e) {
		    	  
		        System.out.println("\""+args[i]+"\" is not an integer");
		        return;
		      }
		      if (digits[i] < min || digits[i] > max) {
		        System.out.println(digits[i]+" is outside the range ["+
		            min+", "+max+"]");
		        return;
		      }
		    }
		    (new Equation()).solve(digits);
		  }
		}
		
}