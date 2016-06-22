Code submission for the Rectangles exercise.

The purpose of the program is to be able to identify polygon features. The algorithm identifies the following features. A rectangle is a special case of a polygon where the opposite sides are equal and parallel. It also assumes that the polygon can have any orientation and not necessary be parallel to the x-axis.All logic have been implemented with a Base Polygon Class, which will allow user to apply this logic on most polygonal shapes (triangle, pentagon).
The Rectangle class is created as a helper class to easily create a rectangle. Other classes can be created in a similar way to represent other polygons. 

1. Containment :- The algorithm uses the convex hull algorithm to find out if one rectangle completely contains another rectangle.a.contains(b), where a and b are two convex polygons, will be true if all points of b are inside rectangle b. To calculate this, we get the Convex hull of all points of polygon a and then we get the convex hull of points of a and b. If the polygon formed the convexhull of a and the convexhull of a and b are equal, the b is completely contained in a.

2. Adjacent :- Next check implemented was for adjacency. To do this we loop over the edges of A and for each edge, we loop over edge of b. if edgeA and edgeB are collinear , and if some part of edgeA overlaps with edgeB, then we have found the common edge between the two polygons. If we find no other such edge, then polygon b is adjacent to polygon a

3. If polygon a does not contain b and if it is not adjacent to b, then a and b are either completely seperated or are intersecting. We again loop through the edges of Bm for each edge of A, and if we can find two unique points where the edge intersect, then polygon a intersects polygon b.
/*
         * Looking for proper intersection, 
         * 
         *          /
         *         /
         *        /\
         *       /  \
         *       No intersection.
         *       
         *       /\
         *      /  \
         *     /    \
         *     No Intersection
         *      
         *         
         *         /
         *       \/
         *       /\
         *      /  \
         *      Intersection
         * 
         */
 If polygon A touches polygon B on one single point, then for the purpose of this exercise it is treated as seperated.
 
     ----------
    |          |
    |          |
    |          |
     ----------  ----------
  				|          |
    			|          |     //These polygons are neither adjacent 
    			|          |     // nor intersecting
    			 ----------