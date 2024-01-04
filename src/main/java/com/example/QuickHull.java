package com.example;

import java.util.*;

/**
 * ConvexHull Problem
 * QuickHull Algorithm
 * QuickHull algorithm is a divide and conquer technique. Finds two furthest points each other. After that find third one that furthest
 * from first and second points. It creates a triangle and we make this steps until there is no point outside the lines.
 */
public class QuickHull {
   
    public ArrayList<Point> quickHull(ArrayList<Point> points) {
        ArrayList<Point> convexHull = new ArrayList<Point>();
        if (points.size() < 3)
            return (ArrayList) points.clone();

        int minPoint = -1, maxPoint = -1;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x < minX) {
                minX = points.get(i).x;
                minPoint = i;
            }
            if (points.get(i).x > maxX) {
                maxX = points.get(i).x;
                maxPoint = i;
            }
        }
        Point A = points.get(minPoint);
        Point B = points.get(maxPoint);
        convexHull.add(A);
        convexHull.add(B);
        points.remove(A);
        points.remove(B);

        ArrayList<Point> leftSet = new ArrayList<Point>();
        ArrayList<Point> rightSet = new ArrayList<Point>();

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (pointLocation(A, B, p) == -1)
                leftSet.add(p);
            else if (pointLocation(A, B, p) == 1)
                rightSet.add(p);
        }

        
        hullSet(A, B, rightSet, convexHull); // clockwise area of the A-B 
        hullSet(B, A, leftSet, convexHull);  // counter clockwise area of the A-B

        return convexHull;
    }

    /*
     * Recursive part of the QuickHull Algorithm. It takes two point that min and max,
     *  left/right set and convexHull Points to update
     * if there is no elm in set means A and B consist all of the points
     * if there is one element in the set that means there is a only one 
     * point outside of the line A-B and this point replaced with B
     * 
     * if set has more than one elements, we find furthest distance and we 
     * are gonna create new triangle with A-B-C and method call
     * itself again on this triangle and points that left and right side of A-C line.
     * 
     */
    public void hullSet(Point A, Point B, ArrayList<Point> set, ArrayList<Point> hull) {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0)
            return;
        
        if (set.size() == 1) {
            Point p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }
        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++) {
            Point p = set.get(i);
            int distance = distance(A, B, p);
            if (distance > dist) {
                dist = distance;
                furthestPoint = i;
            }
        }
        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);

        // Determine who's to the left of A-P
        ArrayList<Point> leftSetAP = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++) {
            Point M = set.get(i);
            if (pointLocation(A, P, M) == 1) {
                //set.remove(M);
                leftSetAP.add(M);
            }
        }

        // Determine who's to the left of P-B
        ArrayList<Point> leftSetPB = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++) {
            Point M = set.get(i);
            if (pointLocation(P, B, M) == 1) {
                //set.remove(M);
                leftSetPB.add(M);
            }
        }
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);

    }





     /*
     * Returns distance between A-B points and C point.
     */

     public int distance(Point A, Point B, Point C) {
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        if (num < 0)
            num = -num;
        return num;
    }


    
    /*
     * Returns the points that are in the convex hull.
     * If point is in the triange(A-B-P) then returns 1.
     * If point is outside of the line(A-B-P) then returns -1.
     */
    public int pointLocation(Point A, Point B, Point P) {
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        if (cp1 > 0)
            return 1;
        else if (cp1 == 0)
            return 0;
        else
            return -1;
    }

    /*
     * Point class has (x,y)
     */
    public static class Point {
        int x, y;
    
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public  void run() {
        System.out.println("Quick Hull Convex Hull Example");
        ArrayList<Point> points = new ArrayList<Point>();
        // Add points here
        // Result should be outer points. (1,1), (2,5), (4,2), (4,0)
        points.add(new Point(2, 5));
        points.add(new Point(2,3));
        points.add(new Point(2, 1));
        points.add(new Point(3, 2));
        points.add(new Point(4, 0));
        points.add(new Point(1, 1));
        points.add(new Point(4, 2));
        QuickHull qh = new QuickHull();
        ArrayList<Point> p = qh.quickHull(points);
        for (int i = 0; i < p.size(); i++)
            System.out.println("(" + p.get(i).x + ", " + p.get(i).y + ")");

    }
    
}
