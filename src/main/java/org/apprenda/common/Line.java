package org.apprenda.common;

import java.util.Arrays;
import java.lang.Math;

public class Line {
    
    private Coordinate2D start;
    private Coordinate2D end;
    
    public Line(Coordinate2D start,Coordinate2D end){
        if(start.compareTo(end) >-1){
        this.start=start;
        this.end=end;
        }else{
            this.end=start;
            this.start=end;
        }
    }
    
    public Coordinate2D intersects(Line l){
        if(l==null)
            return null;
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
         * 
         */
        /*
         * Utils.isCCW(this.start,l.start,l.end) != Utils.isCCW(this.end,l.start,l.end) 
         *      && Utils.isCCW(l.start,this.start,this.end) != Utils.isCCW(l.end,this.start,this.end);
         * 
         */
        /*if( Utils.getArea(this.start,l.start,l.end)==0 
                ||Utils.getArea(this.end,l.start,l.end)==0
                ||Utils.getArea(l.start,this.start,this.end)==0
                ||Utils.getArea(l.end,this.start,this.end)==0){
            return null;
        }else{*/
            return this.getPointOfInterSection(l);
        //}
    }
    
    public boolean contains(Coordinate2D point){
        double[] x= {this.end.getX(),this.start.getX()};
        double[] y= {this.end.getY(),this.start.getY()};
        Arrays.sort(x);
        Arrays.sort(y);
        return (x[0] <= point.getX() && 
                x[1]>= point.getX() &&
                y[0] <= point.getY() &&
                y[1]>=point.getY());
    }
    
    public Coordinate2D getPointOfInterSection(Line l){
        double a1= this.end.getY()-this.start.getY();
        double a2= l.end.getY()-l.start.getY();
        double b1= this.start.getX()-this.end.getX();
        double b2=l.start.getX()-l.end.getX();
        double c1=a1*this.start.getX()+b1*this.start.getY();
        double c2= a2*l.start.getX()+b2*l.start.getY();
        
        double delta = a1*b2 - b1*a2;
        if(delta==0){
            //parallel
            return null;
        }else{
            double x = (b2*c1 - b1*c2)/delta;
            double y = (a1*c2 - a2*c1)/delta;
            Coordinate2D point = new Coordinate2D(x,y);
            if(this.contains(point) && l.contains(point))
                return point;
            else
                return null;
        }
    }
    
    public boolean colinear(Line l){
        if(l==null)
            return false;
        if(this.equals(l))
            return true;
        if(this.start.equals(l.start)||this.start.equals(l.end)
                ||this.end.equals(l.start)||this.end.equals(l.end))
            return false;
        double area=Utils.getArea(l.start,this.start,this.end);
        double area2=Utils.getArea(l.end,this.start,this.end);
        return area==0.0 && area2==0.0 && (this.contains(l.start)|| this.contains(l.end)||l.contains(this.start)||l.contains(this.end));
    }
    
    public double length(){
        double x2=(this.start.getX()-this.end.getX());
        double y2=(this.start.getY()-this.end.getY());
        return Math.sqrt(x2*x2+y2*y2);
    }
    
    public int hashCode(){
        return 1;
    }
    
    public String toString(){
        String pStr="[";
        pStr+=start.toString();
        pStr+=end.toString();
        return pStr+"]";
    }
    
    public boolean equals(Line l){
        if(l==null)
            return false;
        return (this.start.equals(l.start) && this.end.equals(l.end));
    }

    public static void main(String[] args){
        Line l1 = new Line(new Coordinate2D(3, 6),new Coordinate2D(6, 6));
        Line l2 = new Line(new Coordinate2D(5, 5),new Coordinate2D(5, 1));
        System.out.println(l2.getPointOfInterSection(l1));
        //System.out.println(l1);
        //System.out.println(l2);
        
    }
}
