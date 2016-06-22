package org.apprenda.common;

public class Utils {
    
    public static double getArea(Coordinate2D a,Coordinate2D b, Coordinate2D c){
        //System.out.println(a+"--"+b+"--"+c);
        return  ((b.getX() - a.getX()) * (c.getY() - a.getY())) -
                ((b.getY() - a.getY()) * (c.getX() - a.getX()));
    }
    
    public static boolean isCCW(Coordinate2D a,Coordinate2D b, Coordinate2D c){
        /*

         if (getArea(a, b, c) >0){
           return 1;
          }else if(getArea(a, b, c) <0){
            return -1;
          }else{
             return 0;
          }
         */
        return getArea(a, b, c) >0;
    }
    
    public static double polarAngle(Coordinate2D a,Coordinate2D b){
        return Math.atan2(a.getY()-b.getY(), a.getX()-b.getX());
    }
    

}
