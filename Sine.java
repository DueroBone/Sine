import java.lang.Math;
//import java.lang.reflect.Array;
import java.util.Scanner;
public class Sine {
  static int samplesTest = 1000; //Adjustable   <=100000000
  static double pi = Math.PI;
  static double pi2 = pi/2;
  static boolean debugMode = true;
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Input exponent: ");
      double var = scanner.nextDouble();
      System.out.println(computeExponent(var));
    }

    public static double computeSine(double x, double exponent) {
      double n1 = ((Math.pow(((x%pi)-pi2),exponent)/Math.pow(pi2,exponent))*-1) + 1;
      if (debugMode) {
        System.out.println("");
        System.out.print("Custom sine of x: ");
        System.out.print(x);
        System.out.print(", exponent is: ");
        System.out.print(exponent);
        System.out.print(", result is: ");
        System.out.print(n1);
      }
      return n1;
    }

    public static double computeExponent(double exponent) {
      double xIncrement = pi/samplesTest;
      double differences[] = new double[samplesTest];
      double x = 0;
      for (int i=0; i<samplesTest; i++) {
        x = x + xIncrement;
        differences[i] = Math.abs(computeSine(x, exponent) - Math.sin(x));
      }
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

    public static double findHighestOfArray(double input[]) {
      double highest = 0;
      for (int i=0; i<input.length; i++) {
        if (input[i] > highest) {
          highest = input[i];
        }
      }
      return highest;
    }

}