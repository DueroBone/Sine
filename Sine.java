import java.lang.Math;
public class Sine {
  static int samplesTest = 10000; //Adjustable   <=100000000
  static int maxRepeats = 10000;
  static double pi = Math.PI;
  static double pi2 = pi/2;
  static boolean debugMode = false;
    public static void main(String[] args) {
      GradientAscent(1);
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
        double a = input[i];
        if (a == a) {
         total = total + input[i];
        }
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

    public static void GradientAscent(double startingExp) {
      double exponent = startingExp;
      double expIncrement = 0.01;
      double expTesting = exponent;
      double result1;
      double result2;
      boolean wasGoingUp = true;
      boolean isGoingUp = true;
      for (int i=0; i<maxRepeats; i=i+1) {
        result1 = computeExponent(expTesting);
        if (wasGoingUp) {
          expTesting = exponent - expIncrement;
        } else {
          expTesting = exponent + expIncrement;
        }
        result2 = computeExponent(expTesting);
        if (exponent == expTesting) {
          i = maxRepeats;
        }
        if (result1 < result2) { //if currently going up
          isGoingUp = true;
          if (wasGoingUp) {
            exponent = expTesting;
          } else { //was going down
            expIncrement = expIncrement/2;
            exponent = expTesting + expIncrement;
          }
          wasGoingUp = true;
        } else { //if currently going down
          isGoingUp = false;
          if (wasGoingUp) {
            expIncrement = expIncrement/2;
            exponent = expTesting - expIncrement;
          } else { //was going down
            exponent = expTesting;
          }
          wasGoingUp = false;
        }
      }
      System.out.println("Best exponent is between: " + exponent + " and " + expTesting);
    }
}