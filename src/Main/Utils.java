package Main;

import java.io.*;

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
	
	
	public static Object loadObject(String path) {
        Object out = null;

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return out;
        }

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(inputStream);
            out = (Object) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (ois != null) {
                try {
                    ois.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return out;
    }


    public static boolean saveObject(String path, Object obj) {
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(path, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }

        return false;
    }
}
