package howell008;
/**
 * @author Kevan Buckley, maintained by Matthew Howell
 * @version 2.0, 2014
 */

public class SpacePlace {
  private int xOrg;
  private int yOrg;
  private double theta;
  private double phi;
  
  public SpacePlace() {
    xOrg = 0;
    yOrg = 0;
  }

  public SpacePlace(double theta, double phi) {
    super();
    this.theta = theta;
    this.phi = phi;
  }

  public int getxOrg() {
    return xOrg;
  }

  public void setxOrg(int xOrg) {
    this.xOrg = xOrg;
  }

  public int getyOrg() {
    return yOrg;
  }

  public void setyOrg(int yOrg) {
    this.yOrg = yOrg;
  }

  public double getTheta() {
    return theta;
  }

  public void setTheta(double theta) {
    this.theta = theta;
  }

  public double getPhi() {
    return phi;
  }

  public void setPhi(double phi) {
    this.phi = phi;
  }
  
  public static int gecko(int ValuePass) {
	    if (ValuePass == (32 & 16)) {
	      return -7;
	    } else {
	      if (ValuePass < 0) {
	        return gecko(ValuePass + 1 | 0);
	      } else {
	        return gecko(ValuePass - 1 | 0);
	      }
	    }
	  }
  
}
