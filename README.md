**ConvexHull Problem** solved with QuickHull Algorithm.


Convex Hull definition: Given a set of points in a 2D space,  we have to find  the convex hull of those points. (shape that includes all of the points that in the given set.)

Real world applications:
Collision Meshes In computer graphics.

QuickHull Algorithm definition: A method of computing the convex hull of a finite set of points in n-dimensional space. It uses a divide and conquer method.
Worst Case = O(n²)
Best Case = O(nlogn)

There are two main methods:

1. quickHull(set)

   1. find left and right most points furthest each other A&B
   2. groups the points that stay right and left of the A-B line
   3. call findHull(set, A,B) method
   4. call findhull(set,B,A) method
   5. return convexPoints
2. hullSet(A,B,set,convexPoints),
   1.if set no point return
   2.find furthest point from A – B say it C
   3.groups points as outside of the A – C and outside of the
   C – B, call hullSet(A,C,leftAC,convexPoints) and
   hullset(C,B, leftCB, convexPoints) itself. Recursive part.
