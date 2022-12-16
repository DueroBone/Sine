import java.lang.Math;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.MathContext;
public class Sine {
  static BigDecimal samplesTest = new BigDecimal(100); //Adjustable   <=100000000
  static BigDecimal pi = new BigDecimal(Math.PI);
  static BigDecimal halfPi = pi.divide(new BigDecimal(2));
  static int samplesExponent = 20;
  //static BigDecimal exponent = new BigDecimal("0.0");
    public static void main(String[] args) {
      BigDecimal var = new BigDecimal(1.88);
      System.out.println(computeSine(var, var));
      //System.out.println(computeExponent(var));
    }

    //This doesn't work, becuase the BigDecimal is unable to be raised to the power of another BigDecimal. At that point, might as well just do it with doubles...
    public static BigDecimal computeSine(BigDecimal x, BigDecimal exponent) {
      BigDecimal n2 = halfPi.pow(exponent.intValueExact(), MathContext.DECIMAL128);
      BigDecimal n1 = x.remainder(pi).subtract(halfPi).pow(exponent.intValueExact(), MathContext.DECIMAL128);
      n1 = n1.divide(n2, MathContext.DECIMAL128);
      n1 = n1.multiply(new BigDecimal("-1")).add(new BigDecimal("1"));
      return n1;
    }
    
    public static double computeExponent(BigDecimal exponent) {
      BigDecimal xIncrement = pi.divide(samplesTest);
      double differences[] = new double[samplesTest.intValue()];
      BigDecimal x = new BigDecimal("0");
      for (int i=0; i<samplesTest.intValue(); i++) {
        x = x.add(xIncrement);
        differences[i] = (computeSine(x, exponent).subtract(new BigDecimal(Math.sin(x.doubleValue())))).abs().doubleValue();
        System.out.println(differences[i]);
      }
      System.out.println("");
      return findAverageOfArray(differences);
    }
    
    public static double findAverageOfArray(double input[]) {
      double total = 0;
      for (int i = 0; i < input.length; i=i+1){
        total = total + input[i];
      }
      total = total/input.length;
      return total;
    }
    /*
    public static double findHighestOfArray(double input[]) {
      double highest = 0;
      for (int i=0; i<input.length; i++) {
        if (input[i] > highest) {
          highest = input[i];
        }
      }
      return highest;
    }

    /*
    public static double computeDigit() {
      double accuracy[] = new double[samplesExponent];
      double exponentIncrement = 1.0/samplesExponent;
      for (int i=0; i<samplesExponent; i++){
        accuracy[i] = computeExponent();
        exponent = exponent + exponentIncrement;
      }
      return findHighestOfArray(accuracy);
    }
    */
}