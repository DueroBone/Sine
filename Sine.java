import java.lang.Math;
public class Sine {
  //Adjustable
  static int samplesTest = 1000; // <=100000000
  static int maxRepeats = 500; // >124
  static int learningRate = 2; //multiplier
  //Constants
  static double pi = Math.PI;
  static double pi2 = pi/2;

    public static void main(String[] args) {
      GradientAscent(1);
    }

    //Function that computes my custom sine
    public static double computeSine(double x, double exponent) {
      double n1 = ((Math.pow(Math.abs((x%pi)-pi2),exponent)/Math.pow(pi2,exponent))*-1) + 1;
      return n1;
    }

    //Finds the accuracy of an exponent over array of x values
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

    //Finds the average of numbers in the array
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

    //Adjusts exponent in order to optimize for best possible exponent
    public static void GradientAscent(double startingExp) {
      double exponent = startingExp;
      double expIncrement = 0.01;
      double expTesting = exponent;
      double result1;
      double result2;
      boolean wasGoingDown = true;

      for (int i=0; i<maxRepeats; i=i+1) {
        result1 = computeExponent(expTesting);
        if (wasGoingDown) {
          expTesting = exponent + expIncrement;
        } else {
          expTesting = exponent - expIncrement;
        }
        result2 = computeExponent(expTesting);

        if (exponent == expTesting) { //if there is no change in the exponent
          i = i + (maxRepeats/1);
        }

        if (result1 > result2) { //if currently going up
          if (wasGoingDown) {
            exponent = expTesting;
          } else { //was going down
            expIncrement = expIncrement/learningRate; //go up, but slower
            exponent = expTesting + expIncrement;
          }
          wasGoingDown = true;
        } else { //if currently going down
          if (wasGoingDown) {
            expIncrement = expIncrement/learningRate; //go down, but slower
            exponent = expTesting - expIncrement;
          } else { //was going down
            exponent = expTesting;
          }
          wasGoingDown = false;
        }
      }
      System.out.println("Best exponent is: " + exponent + " with an accuracy of: " + (100-(computeExponent(exponent)*100) + "%"));
    }
}