package org.apprenda.oriented;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

import org.apprenda.common.Coordinate2D;
import org.apprenda.common.Line;
import org.apprenda.common.Utils;

public class Polygon {
    
    private ArrayList<Coordinate2D> coordinates;
    
    public Polygon(){
        
    }
    
    public Polygon(ArrayList<Coordinate2D> points){
        this.coordinates=points;
    }
    
    
    public ArrayList<Coordinate2D> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinate2D> coordinates){
        this.coordinates = coordinates;
    }

    
    /*
     * Returns the smallest polygon that can be created connecting all the peripheral points , in a counterclockwise sweep.
     * 
     */
    public final Polygon getConvexHull(ArrayList<Coordinate2D> points2d){
        
        points2d.sort(new Comparator<Coordinate2D>() {
             @Override
             public int compare(Coordinate2D arg0, Coordinate2D arg1) {
                 if(arg0.getY()==arg1.getY()){
                     return (int)(arg1.getX()-arg0.getX());
                 }else{
                     return (int)(arg0.getY()-arg1.getY());
                 }
             }

         });
         Coordinate2D p = points2d.remove(0);
         points2d.sort(new Comparator<Coordinate2D>() {
             @Override
             public int compare(Coordinate2D arg0, Coordinate2D arg1) {
                 double area1=Utils.polarAngle(p, arg0);
                 double area2=Utils.polarAngle(p, arg1);
                 if(area1==area2)
                     return 0;
                 else if(area1>area2)
                     return 1;
                 else
                     return -1;                        
             }
         });

        //back tracking algorithm to calculate the convex hull (Graham Scan)
        Stack<Coordinate2D> hull = new Stack<Coordinate2D>();
        
        hull.push(p);
        hull.push(points2d.get(0));
        for(int i=1;i<points2d.size();i++){
            Coordinate2D last = hull.pop();
            while (Utils.getArea(hull.peek(), last, points2d.get(i))<0) {
                // if the points for a left turn instead of a right turn, delete the last two points as they cannot be part of the convex hull
                last = hull.pop();
            }
            hull.push(last);
            hull.push(points2d.get(i));
        }
        ArrayList<Coordinate2D> hullList= new ArrayList<Coordinate2D>();
        for(Coordinate2D point2d:hull){
            hullList.add(point2d);
        }
        return new Polygon(hullList);
     }
    /*
     * if the convex hull polygon of this Polygon instance is equals to the convex hull polygon of all the points of this polygon
     * and polygon b, then this polygon completely contains polygon b.
     * 
     */
    public final boolean contains(Polygon rect){
        if(rect==null){
            return false;
        }else if(this.equals(rect)){
            //they basically are the same polygons, 
            return false;
        }else{
            ArrayList<Coordinate2D> points2d= new ArrayList<Coordinate2D>();
            points2d.addAll(this.getCoordinates());
            Polygon polygon1=this.getConvexHull(points2d);
            points2d.clear();
            points2d.addAll(this.getCoordinates());
            points2d.addAll(rect.getCoordinates());
            Polygon polygon2=this.getConvexHull(points2d);
            return polygon1.equals(polygon2);
        }

    }
    
    /*
     * A quick utility function that will give you the line segments that form the edges of a polygon represented a list of coordinates.
     * 
     */
    
    private ArrayList<Line> getEdges(ArrayList<Coordinate2D> points2d){
        ArrayList<Line> edges = new ArrayList<Line>();
        Polygon polygon = getConvexHull(points2d);
        if(polygon==null){
            return edges;
        }
        for(int i=0;i<polygon.coordinates.size();i++){
            if(i<polygon.coordinates.size()-1)
                edges.add(new Line(polygon.coordinates.get(i),polygon.coordinates.get(i+1)));
            else
                edges.add(new Line(polygon.coordinates.get(i),polygon.coordinates.get(0)));
        }
        return edges;   
    }
    
    /*
     * If there are more that two points where an edge of this polygon, intersects with edge of polygon b
     * then this polygon intersects polygon b.
     * 
     */
    public final boolean isIntesecting(Polygon a){
        if(a==null)
            return false;
        //if this contains b, then cannot be intersecting
        if(this.contains(a) || a.contains(this))
            return false;
        //if this polygon is adjacent to polygon b, then they cannot be intersecting
        if(this.isAdjacent(a))
            return false;
        //returns the coordinates where the this polygons edges intersect edge of polygon b.
        if(this.findIntersects(a).size()<2)
            return false;
        else
            return true;
    }
    
    public final  ArrayList<Coordinate2D> findIntersects(Polygon a){
        //a list to hold all the intersection points of the polygon
        ArrayList<Coordinate2D> iPoints= new ArrayList<Coordinate2D>();
        if(a==null)
            return iPoints;
        
        ArrayList<Coordinate2D> pointsA= new ArrayList<Coordinate2D>();
        ArrayList<Coordinate2D> pointsB= new ArrayList<Coordinate2D>();
        //list of coordinates of this polygon
        pointsB.addAll(this.getCoordinates());
        //list of coordinates of  polygon a
        pointsA.addAll(a.getCoordinates());
      //list of edges of  this polygon
        ArrayList<Line> edgesB=this.getEdges(pointsB);
      //list of edges of  polygon a
        ArrayList<Line> edgesA=this.getEdges(pointsA);
        for(Line edgeA:edgesA){
            for(Line edgeB:edgesB){
               Coordinate2D point= edgeA.intersects(edgeB,false);
               //if edge A returns a intersection point with edgeB and the point does not already exists
               //then add it to list on intersection points.
               /*
                * this tries to eliminate a case where 
                *     -------
                *    |       |
                *    |       |
                *     -------         /here two lines of the rhombus below will intersect at the same point, so it can be counted twice, he eliminating duplicates.
                *        /\
                *       /  \
                *       \  /
                *        \/ 
                */
               if(point!=null && !iPoints.contains(point))
                   iPoints.add(point);
            }
        }
  
        return iPoints;

    }
    
    public final boolean isAdjacent(Polygon b){
        if(b==null)
            return false;
        if(this.contains(b) || b.contains(this))
            return false;
        ArrayList<Coordinate2D> iPoints= new ArrayList<Coordinate2D>();
        
        ArrayList<Coordinate2D> pointsA= new ArrayList<Coordinate2D>();
        ArrayList<Coordinate2D> pointsB= new ArrayList<Coordinate2D>();
        
        pointsA.addAll(this.getCoordinates());
        pointsB.addAll(b.getCoordinates());
        ArrayList<Line> edgesB=this.getEdges(pointsB);
        ArrayList<Line> edgesA=this.getEdges(pointsA);
        boolean adjacent=false;
        for(Line edgeA:edgesA){
            for(Line edgeB:edgesB){
               //System.out.println(edgeA+"--"+edgeB);
                //if edgeA is in alignment with edgeB and either starts or end in a point that is contained in edgeB, then edgeA and edgeB overlap
                
              /*
               * A--B-------B------A
               * <---edgeA----------> // this is collinear and overlapping
               *    <-edgeB->
               *    
               *    A--------A    B-------B //collinear but not overlapping
               *    
               *    c
               *    /\
               * A /  \ B    //Overlapping at point c, but not collinear, 
               *  /    \
               */
                
              if( edgeA.colinear(edgeB))
                  adjacent=true;
              else{
                  if(edgeA.intersects(edgeB,true)!=null)
                      adjacent=false;
              }
            }
        }
        return adjacent;
    }
    
    public boolean equals(Object a){
        if(a==null){
            return false;
        }else if(!Polygon.class.isAssignableFrom(a.getClass())){
            return false;
        }else{
            Polygon p =(Polygon)a;
            this.coordinates.sort(new Comparator<Coordinate2D>() {
                @Override
                public int compare(Coordinate2D arg0, Coordinate2D arg1) {
                    if(arg0.getY()==arg1.getY()){
                        return (int)(arg1.getX()-arg0.getX());
                    }else{
                        return (int)(arg0.getY()-arg1.getY());
                    }
                }

            });
            
            p.coordinates.sort(new Comparator<Coordinate2D>() {
                @Override
                public int compare(Coordinate2D arg0, Coordinate2D arg1) {
                    if(arg0.getY()==arg1.getY()){
                        return (int)(arg1.getX()-arg0.getX());
                    }else{
                        return (int)(arg0.getY()-arg1.getY());
                    }
                }

            });
            
            if(p.coordinates.size()==this.coordinates.size()){
                for(int i=0;i<this.coordinates.size();i++){
                    if(! this.coordinates.get(i).equals(p.coordinates.get(i)))
                        return false;
                }
                return true;
                
            }else{
                return false;
            }
        }
    }
    
    public String toString(){
        String pStr="[";
        for(Coordinate2D points:coordinates){
            pStr+=points.toString();
        }
        return pStr+"]";
    }
    
    
}
