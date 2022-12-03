import java.lang.Math;
//import java.lang.reflect.Array;
public class Sine {
  static double samplesTest = 100; //Adjustable
  static double pi = 3.141592653589793238;
  static double pi2 = pi/2;
  static double samplesExponent = 20;
  //static double x = 0;
  static double exponent = 0;
    public static void main(String[] args) {
      exponent = 2;
      System.out.println(compExponent());
    }


    public static double compSin(double x) {
      double n1 = ((Math.pow(((x%pi)-pi2),exponent)/Math.pow(pi2,exponent))*-1) + 1;
      return n1;
    }

    public static double findAverageOfArray(double input[]){
      double total = 0;
      for (int i = 0; i < input.length; i=i+1){
        total = total + input[i];
        System.out.println("Total = " + input[i] + " " + i);
      }
      total = total/input.length;
      return total;
    }

    public static double compExponent(){
      double xIncrement = pi/samplesTest;
      double differences[] = new double[(int) samplesTest];
      double x = 0;
      for (int i = 0; i<samplesTest; i++) {
        x = + xIncrement;
        differences[i] = Math.abs(compSin(x) - Math.sin(x));
        System.out.println("X = " + x + "  |  Sine = " + compSin(x));
      }
      return findAverageOfArray(differences);
    }
}