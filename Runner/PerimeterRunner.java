
/**
 * Write a description of voi here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
     //method getNumPoints has one parameter s type Shape returns int numPoints
   //Iterate over all the points in Shape s and count them
  public int getNumPoints(Shape s){
 	//start with numPoints = 0
	int numPoints = 0;
	for(Point currPt : s.getPoints()){
	//update numPoints
          numPoints = numPoints +1;
        }
	//numPoints is the answer
	return numPoints;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
        int numPoints = 0;
        //print numPoints
        System.out.println("Number of points = " + numPoints);
    }
}