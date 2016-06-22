package org.apprenda.common;

import org.apprenda.aligned.Coordinate2D;

public class Utils {
    
    public static double getArea(Coordinate2D a,Coordinate2D b, Coordinate2D c){
        //System.out.println(a+"--"+b+"--"+c);
        return  ((b.getX() - a.getX()) * (c.getY() - a.getY())) -
                ((b.getY() - a.getY()) * (c.getX() - a.getX()));
    }
    
    public static double polarAngle(Coordinate2D a,Coordinate2D b){
        return Math.atan2(a.getY()-b.getY(), a.getX()-b.getX());
    }
    

}
