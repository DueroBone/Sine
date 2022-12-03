import java.lang.Math;
//import java.lang.reflect.Array;
public class Sine {
  static int samplesTest = 1000; //Adjustable   <=100000000
  static double pi = 3.141592653589793238;
  static double pi2 = pi/2;
  static int samplesExponent = 20;
  //static double x = 0;
  static double exponent = 0;
    public static void main(String[] args) {
      exponent = 1;
      System.out.println(computeDigit());
    }


    public static double computeSine(double x) {
      double n1 = ((Math.pow(((x%pi)-pi2),exponent)/Math.pow(pi2,exponent))*-1) + 1;
      return n1;
    }

    public static double findAverageOfArray(double input[]) {
      double total = 0;
      for (int i = 0; i < input.length; i=i+1){
        total = total + input[i];
      }
      total = total/input.length;
      return total;
    }

    public static double findHighestOfArray(double input[]) {
      double highest = 0;
      for (int i=0; i<input.length; i++) {
        if (input[i] > highest) {
          highest = input[i];
        }
      }
      return highest;
    }

    public static double computeExponent() {
      double xIncrement = pi/samplesTest;
      double differences[] = new double[samplesTest];
      double x = 0;
      for (int i=0; i<samplesTest; i++) {
        x = x + xIncrement;
        differences[i] = Math.abs(computeSine(x) - Math.sin(x));
      }
      return findAverageOfArray(differences);
    }
    public static double computeDigit() {
      double accuracy[] = new double[samplesExponent];
      double exponentIncrement = 1.0/samplesExponent;
      for (int i=0; i<samplesExponent; i++){
        accuracy[i] = computeExponent();
        exponent = exponent + exponentIncrement;
        //System.out.println(exponent + " | " + accuracy[i]);
      }
      return findHighestOfArray(accuracy);
    }
    public static void zoomExponent() {
      
    }
}