package org.apprenda.oriented;

import java.util.ArrayList;

import org.apprenda.common.Coordinate2D;

public class Rectangle extends Polygon {

    
    public Rectangle(Coordinate2D origin, double xlength,double ylength){
        super();
        ArrayList<Coordinate2D> coordinates= new ArrayList<Coordinate2D>();
        coordinates.add(origin);
        coordinates.add(new Coordinate2D(origin.getX()+xlength,origin.getY()));
        coordinates.add(new Coordinate2D(origin.getX()+xlength,origin.getY()+ylength));
        coordinates.add(new Coordinate2D(origin.getX(),origin.getY()+ylength));
        this.setCoordinates(coordinates);
    }
    
    public Rectangle(Coordinate2D a,Coordinate2D b,Coordinate2D c,Coordinate2D d){
        super();
        ArrayList<Coordinate2D> coordinates= new ArrayList<Coordinate2D>();
        coordinates.add(a);
        coordinates.add(b);
        coordinates.add(c);
        coordinates.add(d);
        this.setCoordinates(coordinates);
    }
    
    public Rectangle(ArrayList<Coordinate2D> coordinates){
        super(coordinates);
    }
    
    public static void main(String[] args){

        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(3, 2),new Coordinate2D(4,2)
                        ,new Coordinate2D(3, 1),new Coordinate2D(4,1));
        //Rectangle r2 = new Rectangle(new Coordinate2D(1, 2),new Coordinate2D(2, 3)
        //        ,new Coordinate2D(2, 0),new Coordinate2D(3,1));
        //r1.intersects(r2);
        if(r1.isAdjacent(r2))
            System.out.println("r1 is adjacent to r2");
        else
            System.out.println("r1 is not adjacent to r2");
        if(r2.isAdjacent(r1))
            System.out.println("r2 is adjacent to r1");
        else
            System.out.println("r2 is not adjacent to r1");
        if(r1.isIntesecting(r2))
            System.out.println("r1 intersects r2");
        else
            System.out.println("r1 does not intersect r2");
        if(r1.contains(r2))
            System.out.println("r1 contains r2");
        else
            System.out.println("r1 does not contain r2");

    }
}
