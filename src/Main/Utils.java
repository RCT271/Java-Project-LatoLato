package Main;

public abstract class Utils {
	
	public static void sayHello( ) {
		System.out.println("Hello World");
		
	}
	
	public static double getDistance(double[] p1, double[] p2) {
		// distance formula sqrt( (x2 - x1)^2 + (y2 - y1)^2 )
		
		return Math.sqrt( Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2) );
	}
	
	
	public static double getDegreeAngleOf2Points(double[] topPoint, double[] botPoint) {
		
		double angle = Math.atan2(topPoint[1] - botPoint[1], botPoint[0] - topPoint[0]);
		
		return Math.toDegrees(angle);
	}
	
	
	public static double getAngleOf2Points(double[] topPoint, double[] botPoint) {
		
		return Math.atan2(topPoint[1] - botPoint[1], botPoint[0] - topPoint[0]);
		
	}
}
