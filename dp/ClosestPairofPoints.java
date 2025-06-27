import java.util.Arrays;
import java.util.Comparator;
public class ClosestPairofPoints {
    
    // Class to represent a Point
    static class Point {
        double x, y;
        
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // Utility function to compute the Euclidean distance between two points
    static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
    
    // Brute-force method for small cases
    static double bruteForce(Point[] points, int n) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double d = distance(points[i], points[j]);
                minDist = Math.min(minDist, d);
            }
        }
        return minDist;
    }
    
    // Merge step: Check points in the strip
    static double stripClosest(Point strip[], int size, double d) {
        double minDist = d;
        
        // Sort strip points by y-coordinate
        Arrays.sort(strip, 0, size, Comparator.comparingDouble(p -> p.y));
        
        // Check for a closer pair in the strip
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < minDist; j++) {
                minDist = Math.min(minDist, distance(strip[i], strip[j]));
            }
        }
        return minDist;
    }
    
    // Recursive function to find the closest pair
    static double closestUtil(Point[] points, int left, int right) {
        if (right - left <= 3) { // Base case
            return bruteForce(Arrays.copyOfRange(points, left, right + 1), right - left + 1);
        }
        
        int mid = (left + right) / 2;
        Point midPoint = points[mid];
        
        double dLeft = closestUtil(points, left, mid);
        double dRight = closestUtil(points, mid + 1, right);
        double d = Math.min(dLeft, dRight);
        
        // Build strip[] array
        Point[] strip = new Point[right - left + 1];
        int j = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < d) {
                strip[j++] = points[i];
            }
        }
        
        return Math.min(d, stripClosest(strip, j, d));
    }
    
    // Main function to find the closest pair
    static double closestPair(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x)); // Sort by x-coordinate
        return closestUtil(points, 0, points.length - 1);
    }
    
    public static void main(String[] args) {
        Point[] points = { new Point(2, 3), new Point(12, 30), new Point(40, 50),
                           new Point(5, 1), new Point(12, 10), new Point(3, 4) };
        
        System.out.println("The smallest distance is: " + closestPair(points));
    }
}
