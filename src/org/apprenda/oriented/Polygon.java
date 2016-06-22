package org.apprenda.oriented;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

import org.apprenda.aligned.Coordinate2D;
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



    public void setCoordinates(ArrayList<Coordinate2D> coordinates) {
        this.coordinates = coordinates;
    }



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
         
         /*for(Coordinate2D point2d:points2d){
             System.out.println(point2d);
         }*/

        Stack<Coordinate2D> hull = new Stack<Coordinate2D>();
        
        hull.push(p);
        hull.push(points2d.get(0));
        for(int i=1;i<points2d.size();i++){
            Coordinate2D last = hull.pop();
            while (Utils.getArea(hull.peek(), last, points2d.get(i))<=0) {
                //System.out.println(hull.peek()+":"+last+":"+points2d.get(i)+":"+Utils.getArea(hull.peek(), last, points2d.get(i)));
                last = hull.pop();
            }
            hull.push(last);
            hull.push(points2d.get(i));
        }
        ArrayList<Coordinate2D> hullList= new ArrayList<Coordinate2D>();
        for(Coordinate2D point2d:hull){
            hullList.add(point2d);
        }
        for(Coordinate2D point2d:hullList){
            System.out.println(point2d);
        }
        return new Polygon(hullList);
     }
    
    public final boolean contains(Polygon rect){
        if(rect==null){
            return false;
        }else{
            ArrayList<Coordinate2D> points2d= new ArrayList<Coordinate2D>();
            points2d.addAll(this.getCoordinates());
            Polygon polygon1=this.getConvexHull(points2d);
            System.out.println(polygon1);
            points2d.clear();
            points2d.addAll(this.getCoordinates());
            points2d.addAll(rect.getCoordinates());
            Polygon polygon2=this.getConvexHull(points2d);
            System.out.println(polygon2);
            return polygon1.equals(polygon2);
        }

    }
    
    public boolean equals(Polygon a){
        if(a==null){
            return false;
        }else{
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
            
            a.coordinates.sort(new Comparator<Coordinate2D>() {
                @Override
                public int compare(Coordinate2D arg0, Coordinate2D arg1) {
                    if(arg0.getY()==arg1.getY()){
                        return (int)(arg1.getX()-arg0.getX());
                    }else{
                        return (int)(arg0.getY()-arg1.getY());
                    }
                }

            });
            
            if(a.coordinates.size()==this.coordinates.size()){
                for(int i=0;i<this.coordinates.size();i++){
                    if(! this.coordinates.get(i).equals(a.coordinates.get(i)))
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
