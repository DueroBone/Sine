import java.lang.Math;
public class Sine {
static double samplesTest = 0; //Adjustable
static double pi = 3.141592653589793238;
static double pi2 = pi/2;
static double samplesExp = 20;
static double x = 0;
static double exp = 0;
  public static void main(String[] args) {
    
  }
  public static double compSin(double input) {
    double n1 = ((Math.pow(((x%pi)-pi2),exp)/(pi2**exp)) * -1) + 1;
    return input;
  }
}